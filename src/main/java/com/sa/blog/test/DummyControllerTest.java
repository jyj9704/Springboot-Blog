package com.sa.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			return "삭제 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		return "삭제되었습니다.id :"+id;
	}
	
	//save 함수는 아이디를 전달하지 않으면 insert, save 함수는 아이디를 전달하면 해당 id에 대한 데이터가 있으면 update, 아이디를 전달하면 해당 id에 대한 데이터가 없으면 insert
	// email, password
	// RequestBody -> 제이슨 데이터 받기 위해 작성
	@Transactional // 함수 종료 -> 자동 commit
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id : "+id);
		System.out.println("password : "+requestUser.getPassword());
		System.out.println("email : "+requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("수정에 실패하였습니다.");			
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

		// userRepository.save(user);
		
		//*더티 체킹* -> 모아뒀다가 한번에 체킹하는것(db에서)
		return user;
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
				return new IllegalArgumentException("해당 사용자가 없습니다.");
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
		System.out.println("id : "+user.getId());
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate : "+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
		
	}

}
