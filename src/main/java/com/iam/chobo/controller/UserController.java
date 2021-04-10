package com.iam.chobo.controller;

import java.util.List;

import javax.validation.Valid;

import com.iam.chobo.domain.user.User;
import com.iam.chobo.domain.user.UserMapper;
import com.iam.chobo.domain.user.UserRepository;
import com.iam.chobo.domain.user.UserService;
import com.iam.chobo.domain.user.dto.Container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserMapper userMapper;
    private UserService userService;

    @Autowired
    public UserController(
        UserMapper userMapper,
        UserRepository userRepository
    ) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(
                userService.findAll(),
                HttpStatus.OK
        );
    }

    @PostMapping()
    public ResponseEntity<Void> create(
            @Valid @RequestBody Container container
    ) {
        userService.create(userMapper.containertoUser(container));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<UserGetDto> getById(
    //         @PathVariable(value = "id") int id
    // ) {
    //     return new ResponseEntity<>(
    //             mapstructMapper.userToUserGetDto(
    //                     userRepository.findById(id).get()
    //             ),
    //             HttpStatus.OK
    //     );
    // }
}
