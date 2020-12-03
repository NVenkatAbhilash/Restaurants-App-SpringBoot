package com.zemoso.springbootassignment.dao;

import com.zemoso.springbootassignment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    public Users findByUsername(String username);
}
