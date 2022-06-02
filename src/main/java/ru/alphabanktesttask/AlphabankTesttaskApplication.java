package ru.alphabanktesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AlphabankTesttaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlphabankTesttaskApplication.class, args);
    }
}
