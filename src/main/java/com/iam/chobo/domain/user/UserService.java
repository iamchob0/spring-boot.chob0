package com.iam.chobo.domain.user;

import java.util.List;

import com.iam.chobo.domain.user.dto.Container;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
   private final UserRepository userRepository;

    public User login(Container login) {
        return userRepository.findByUserNameByPassword(login.username, login.password);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void create(User user) {
        userRepository.save(user);
    }

}