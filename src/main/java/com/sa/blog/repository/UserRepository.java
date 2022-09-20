package com.sa.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.blog.model.User;


// JSP = DAO
// 자동으로 bean등록이 됌
// @Repository (따라서 생략이 가능함)
public interface UserRepository extends JpaRepository<User, Integer>{

}
