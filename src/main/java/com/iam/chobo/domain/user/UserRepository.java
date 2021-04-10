package com.iam.chobo.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUserNameByPassword(String username, String password);
}
