package com.example.subwaycustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SubwayCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubwayCustomerApplication.class, args);
    }

}
