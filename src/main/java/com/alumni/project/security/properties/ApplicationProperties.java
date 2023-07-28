package com.alumni.project.security.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@Getter
@Setter
public class ApplicationProperties {

    private final JwtProperties jwt = new JwtProperties();
}
