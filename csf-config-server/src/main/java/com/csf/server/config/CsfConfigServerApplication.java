package com.csf.server.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CsfConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CsfConfigServerApplication.class, args);
    }
}
