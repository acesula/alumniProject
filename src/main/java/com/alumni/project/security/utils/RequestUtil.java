package com.alumni.project.security.utils;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import org.springframework.http.HttpHeaders;

@Component
public class RequestUtil {

    public String getJwtFromRequest(HttpServletRequest request) {
        String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (auth != null && !auth.isBlank()) {
            if (auth.contains("Bearer ")) {
                return auth.replaceAll("Bearer ", "");
            }
            return auth;
        }
        return null;
    }
}
