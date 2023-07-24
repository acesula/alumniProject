package com.alumni.project.security.services.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface DatabaseUserDetailsService extends UserDetailsService {

    UserDetails loadById(UUID uuid);

}
