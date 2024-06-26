package edu.ph.myschoolportal.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "spring.mail")
@Configuration
public class EmailConfigProperties {

    private String username;

}
