package com.alumni.project.security.services.user;

import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.security.exception.AuthServerException;
import com.alumni.project.security.model.AuthUserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DatabaseUserDetailsServiceImpl implements DatabaseUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadById(UUID uuid) {
        var user = userRepository.findById(uuid).orElseThrow(() -> new AuthServerException("User does not exist"));
        return AuthUserDetail.createUser(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        return AuthUserDetail.createUser(user);
    }
}
