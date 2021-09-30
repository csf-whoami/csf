package com.csf.base.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

import lombok.Getter;

/**
 * GenerateKeys class
 */
public class GenerateKeys {

    private KeyPairGenerator keyGen;
    private KeyPair pair;
    @Getter
    private PrivateKey privateKey;
    @Getter
    private PublicKey publicKey;

    /**
     * @param keylength
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @desctiption Constructor
     */
    public GenerateKeys(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keylength);
    }

    /**
     * @desctiption create keys
     */
    public void createKeys() {
        this.pair = this.keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    /**
     * @param path
     * @param key
     * @throws IOException
     * @desctiption  write to file
     */
    public void writeToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
    }

    /**
     * @param rootDir
     * @desctiption Genereate private and public key
     */
    public static void generatePrivatePublicKey(String rootDir) {
        GenerateKeys gk;
        try {
            gk = new GenerateKeys(1024);
            gk.createKeys();
            gk.writeToFile(rootDir + "/publicKey", gk.getPublicKey().getEncoded());
            gk.writeToFile(rootDir + "/privateKey", gk.getPrivateKey().getEncoded());
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
