package com.zemoso.springbootassignment.dao;

import com.zemoso.springbootassignment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
