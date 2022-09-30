package com.sa.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sa.blog.model.RoleType;
import com.sa.blog.model.User;
import com.sa.blog.repository.UserRepository;

//data를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {
	
	@Autowired // 같이 메모리에 뜨도록 // 의존성 주입(DI)
	private UserRepository userRepository;
	
	// email, password
	// RequestBody -> 제이슨 데이터 받기 위해 작성
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id : "+id);
		System.out.println("password : "+requestUser.getPassword());
		System.out.println("email : "+requestUser.getEmail());
		
		return null;
	}
	
	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	//한 페이지당 2건에 데이터를 리턴받아
	@GetMapping("/dummy/user")
	public List <User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC)Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user객체가 null인지 아닌지 check해서 리턴
		// {id} 주소로 파라미터를 전달
		// http://localhost:8000/blog/dummy/user/3
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
			}
		});
		// user 객체 = 자바 오브젝트 
		// 웹 브라우저가 이해할 수 있는 데이터로 변환 시켜야함 -> json을 사용할것임
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 자바 오브젝트를 리턴하게 되면 MessageConverter가 jackson 라이브러리를 호출 -> json으로 변환해서 브라우저에 던짐
		return user;
	}
	
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
