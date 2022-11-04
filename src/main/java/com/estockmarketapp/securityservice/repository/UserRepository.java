package com.estockmarketapp.securityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estockmarketapp.securityservice.model.User;

public interface UserRepository extends  JpaRepository<User,Integer> {

	public User findByUserName(String username);

}