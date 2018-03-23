package com.packt.thimblerig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.packt.thimblerig")
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
