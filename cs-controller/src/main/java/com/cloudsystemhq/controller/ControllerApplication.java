package com.cloudsystemhq.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.cloudsystemhq.repository")
@EntityScan(basePackages = "com.cloudsystemhq.model")
@ComponentScan(basePackages = "com.cloudsystemhq.*")
public class ControllerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ControllerApplication.class, args);
  }

  @Bean
  public CommandLineRunner loadData() {
    return (args) -> {
    };
  }
}
