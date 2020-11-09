package com.el.returnmsg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ReturnmsgApplication {
    protected static final Logger logger = LoggerFactory.getLogger(ReturnmsgApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ReturnmsgApplication.class, args);
    }

}
