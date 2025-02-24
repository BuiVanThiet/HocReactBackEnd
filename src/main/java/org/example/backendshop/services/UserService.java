package org.example.backendshop.services;

import lombok.RequiredArgsConstructor;
import org.example.backendshop.dto.CredentialsDTO;
import org.example.backendshop.dto.SigUpDTO;
import org.example.backendshop.dto.UserDTO;
import org.example.backendshop.entites.User;
import org.example.backendshop.exceptions.AppException;
import org.example.backendshop.mappers.UserMapper;
import org.example.backendshop.repositores.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    public UserDTO findByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        System.out.println("doi tuong(file user service):"+user.getFullName());
        return userMapper.toUserDto(user);
    }

    public UserDTO login(CredentialsDTO credentialsDTO) {
        System.out.println("dang nhap cua login: " + credentialsDTO.toString());
        User user = userRepository.findByLogin(credentialsDTO.getLogin()).orElseThrow(() -> new AppException("Khong thay",HttpStatus.NOT_FOUND));

        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.getPassword()), (user.getPassword()))) {
            return userMapper.toUserDto(user);
        }

        throw new AppException("sai mat khau",HttpStatus.BAD_REQUEST);
    }

    public UserDTO register(SigUpDTO sigUpDTO) {
        Optional<User> optionalUser = userRepository.findByLogin(sigUpDTO.getAccount());

        if(optionalUser.isPresent()) {
            throw new AppException("dang nhap lai",HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.sigUpToUser(sigUpDTO);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(sigUpDTO.getPassword())));


        User userSave = userRepository.save(user);
        return userMapper.toUserDto(user);
    }
}
