package com.example.chatMulticat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.chatMulticat.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

}
