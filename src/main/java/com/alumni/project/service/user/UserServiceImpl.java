package com.alumni.project.service.user;

import com.alumni.project.dal.entity.User;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.user.GetUserDto;
import com.alumni.project.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public GetUserDto save(UserDto userDto) {
        var user = new User(
                userDto.getName(),
                userDto.getSurname(),
                userDto.getEmail(),
                userDto.getBirthDate(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getGender()
        );
        return map(userRepository.save(user));
    }

    @Override
    public List<GetUserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public GetUserDto findById(UUID id) {
        var optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return map(optional.get());
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public GetUserDto update(UUID id, UserDto userDto) {
        var user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setBirthDate(userDto.getBirthDate());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setGender(userDto.getGender());

        return map(userRepository.save(user));

    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    private GetUserDto map(User user) {
        var dto = new GetUserDto();
        dto.setUserId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setGender(user.getGender());
        dto.setEmail(user.getEmail());
        dto.setBirthDate(user.getBirthDate());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
