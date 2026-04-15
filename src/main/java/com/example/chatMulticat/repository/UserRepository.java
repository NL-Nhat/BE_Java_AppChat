package com.example.chatMulticat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chatMulticat.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

    public User findByUserName(String userName);

    public boolean existsByUserName(String userName);
}
