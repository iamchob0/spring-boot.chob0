package com.iam.chobo.domain.user;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
   private final UserRepository userRepository;

    public User login(UserDto.Login login) {
        return userRepository.findByUserNameByPassword(login.username, login.password);
    }

}