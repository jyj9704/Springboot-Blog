package com.sa.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.blog.model.RoleType;
import com.sa.blog.model.User;
import com.sa.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired // 같이 메모리에 뜨도록 // 의존성 주입(DI)
	private UserRepository userRepository;
	
	// http://localhost:8000/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) { // key = value (규칙)
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
		
	}

}
