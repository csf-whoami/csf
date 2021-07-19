package com.csf.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.security.entity.Oauth2UserEntity;

public interface Oauth2UserRepository extends JpaRepository<Oauth2UserEntity, String> {

	Oauth2UserEntity findByUserName(String username);

}
