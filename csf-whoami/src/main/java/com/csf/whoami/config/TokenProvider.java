package com.csf.whoami.config;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.csf.whoami.base.exception.CustomException;
import com.csf.whoami.base.util.AsymmetricCryptography;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenProvider implements Serializable {

    private static final long serialVersionUID = 1L;
    PrivateKey privateKey;
    PublicKey publicKey;
    @Value("${app.data-dir}")
    String rootDir;
    @Value("${jwt.access-token.expiration}")
    String ACCESS_TOKEN_VALIDITY_SECONDS;
    @Value("${jwt.refresh-token.expiration}")
    String REFRESH_TOKEN_VALIDITY_SECONDS;
    @Value("${jwt.header}")
    String HEADER_STRING;
    @Value("${jwt.token-prefix}")
    String TOKEN_PREFIX;
    @Value("${jwt.authority-key}")
    String AUTHORITIES_KEY;

    @Autowired
    @Qualifier("userService")
    private UserDetailsService userDetailsService;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                //                .setSigningKey(SIGNING_KEY)
                .setSigningKey(publicKey)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * @param authentication
     * @return
     * @throws Exception
     */
    public String generateToken(Authentication authentication) throws Exception {
        AsymmetricCryptography ac = new AsymmetricCryptography();
        privateKey = ac.getPrivate(rootDir + "/privateKey");
        publicKey = ac.getPublic(rootDir + "/publicKey");
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                //                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Integer.valueOf(ACCESS_TOKEN_VALIDITY_SECONDS)))
                .compact();
    }

    /**
     * @param authentication
     * @return
     * @throws Exception
     */
    public String generateAccessToken(Authentication authentication) throws Exception {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication
                .getPrincipal();
        final String authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                //                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Integer.valueOf(ACCESS_TOKEN_VALIDITY_SECONDS)))
                .compact();
    }
    
    /**
     * @param req
     * @return String
     * @description resolve token
     */
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(HEADER_STRING);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * @param token
     * @return String
     * @description validate token
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException("Expired or invalid JWT token", HttpStatus.UNAUTHORIZED);
        }
    }  
    
    /**
     * @param token
     * @return Authentication
     * @description get Authentication
     */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsernameFromToken(token));

        final JwtParser jwtParser = Jwts.parser().setSigningKey(publicKey);
        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        final Claims claims = claimsJws.getBody();
        final Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new CustomAuthenticationDomainToken(userDetails, "", authorities, "");
    }

    /**
     * @param authentication
     * @return String
     * @throws Exception
     * @description generate refresh token
     */
    public String generateRefeshToken(Authentication authentication) throws Exception{
        AsymmetricCryptography ac = new AsymmetricCryptography();
        privateKey = ac.getPrivate(rootDir + "/privateKey");
        publicKey = ac.getPublic(rootDir + "/publicKey");
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, "ROLE_REFRESH_TOKEN")
                //                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Integer.valueOf(REFRESH_TOKEN_VALIDITY_SECONDS)))
                .compact();
    }
}
