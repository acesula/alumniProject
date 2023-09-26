package com.alumni.project.security.properties;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtProperties {

    private String key = "a7cbDmCAUIi7o2lExJDd1JMKgOd7Mq/Pfhvq0CE3tPQ=";
    private int validityInMinutes = 60*3;
}
