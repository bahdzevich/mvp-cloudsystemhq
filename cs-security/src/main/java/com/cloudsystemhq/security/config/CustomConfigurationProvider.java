package com.cloudsystemhq.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:security.properties")
public class CustomConfigurationProvider {


    @Value("${security.bcrypt-workload}")
    private int BCRYPT_WORKLOAD;

    @Bean()
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCRYPT_WORKLOAD);
    }
}
