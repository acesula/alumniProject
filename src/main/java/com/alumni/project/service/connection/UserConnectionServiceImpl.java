package com.alumni.project.service.connection;

import com.alumni.project.dal.repository.UserConnectionRepository;
import com.alumni.project.dal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserConnectionServiceImpl implements UserConnectionService{

    private final UserConnectionRepository userConnectionRepository;
    private final UserRepository userRepository;
}
