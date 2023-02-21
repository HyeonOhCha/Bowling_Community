package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.cos.blog.model.User;


//DAO
//자동으로 bean 등록이 된다.
// @Repository  어노테이션 생략
public interface UserRepository extends JpaRepository<User, Integer>{
	// SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);
	
	// 회원 이름 검색 
	Page<User> findByUsernameContaining(String searchText, Pageable pageable);

	// 이메일 검색
	Page<User> findByEmailContaining(String searchText, Pageable pageable);

}

// JPA Naming 전략
// SELECT * FROM user WHERE username = ? AND password = ?;     
//User findByUsernameAndPassword(String username, String password);


//	@Query(value="SELECT * FROM user WHERE username = ? AND password = ?",nativeQuery = true)
//	User login(String username, String password);
