package org.drone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(WebApplicationMain.class, args);
    }
}
