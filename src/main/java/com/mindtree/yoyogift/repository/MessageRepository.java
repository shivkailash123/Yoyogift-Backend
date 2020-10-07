package com.mindtree.yoyogift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.yoyogift.entity.Messages;

@Repository
public interface MessageRepository extends JpaRepository<Messages, Long> {

}
