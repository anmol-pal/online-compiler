package com.knf.dev.demo.springbootdatajpagcpsqlmysqlcrud;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.user_id = ?1")
    List<User> findByUserId(String username);
}