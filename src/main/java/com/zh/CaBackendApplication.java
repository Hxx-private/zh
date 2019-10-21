package com.zh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author Hxx
 */
@CrossOrigin(origins = "*",allowCredentials = "true")
@SpringBootApplication
public class CaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaBackendApplication.class, args);
    }

}
