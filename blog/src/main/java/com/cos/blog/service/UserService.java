package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.FreeBoard;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌. IoC를 해준다
@Service
public class UserService {

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}

	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword); // 해쉬
		
		user.setPassword(encPassword);
		
		if(user.getUsername().equals("admin")) {
			user.setRole(RoleType.ADMIN);
		}
		else {
			user.setRole(RoleType.USER);
		}
		
		userRepository.save(user);
	}

	@Transactional
	public void 회원수정(User user) {
		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화 시키고 , 영속화된 User 오브젝트를 수정
		// select를 해서 User오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서!
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update 문을 날려주거든요.
		
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		}) ;
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		// 회원수정 함수 종료시 = 서비스 종료 = 트랙젝션 종료 = commit이 자동으로 됩니다.
		// 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌
		
	}
	
	// 회원 삭제
	public void userDelete(User user) {
			User userInfo = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		}) ;
		userRepository.delete(userInfo);
	}

	// 회원 목록 출력 메서드
	@Transactional
	public Page<User> userList(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	// 회원 이름 검색
	@Transactional(readOnly = true)
	public Page<User> UsernameSearch(String searchText, Pageable pageable) {
		Page<User> list = userRepository.findByUsernameContaining(searchText, pageable);
        
        return list;
	}
	
	// 이메일 검색
	@Transactional(readOnly = true)
	public Page<User> EmailSearch(String searchText, Pageable pageable) {
		Page<User> list = userRepository.findByEmailContaining(searchText, pageable);
        
        return list;
	}



	


//	@Transactional(readOnly = true) // Select 할 때 트렉젝션 시작, 서비스 종료시에 트랙젝션 종료 ( 정합성 )
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
//
//	}
}

