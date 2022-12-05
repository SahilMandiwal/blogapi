package com.sm.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sm.demo.entites.User;

public interface UserRepo extends JpaRepository<User,Integer>{

}
