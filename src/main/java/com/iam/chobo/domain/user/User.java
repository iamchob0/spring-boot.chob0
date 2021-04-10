package com.iam.chobo.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 500, nullable = false)
    public String username;

    @Column(length = 500, nullable = false)
    public String password;

    @Builder
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}