package br.com.kenoby.poccliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PocApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PocApplication.class);
    }
}
