package com.sa.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> (HTML파일)응답 = @Controller

// 사용자가 요청 -> (Data)응답 = @RestController

@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";
	
	// localhost:8000/blog/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("sa").password("1234").email("sa@naver.com").build();
		System.out.println(TAG+"getter : "+m.getUsername());
		m.setUsername("sa");
		System.out.println(TAG+"setter : "+m.getUsername());
		return "lombok test 완료";
		// 1. builder 순서를 지키지 않아도 되는 용이함이 있음
	}
	
	// 인터넷 브라우저 요청은 무조건 get요청만 가능함! 
	// http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) { // MessageConverter (스프링부트)
		return "get요청 : " +m.getId()+", " +m.getUsername()+", "+m.getPassword()+", "+ m.getEmail();
	}
	// get요청시 쿼리 스트링을 통해 데이터를 전송할 수 있음
	
	// http://localhost:8080/http/post (insert)
	@PostMapping("/http/post") //text/plain, application/json
	public String postTest(@RequestBody Member m) { // MessageConverter (스프링부트)
		return "post요청 : " +m.getId()+", " +m.getUsername()+", "+m.getPassword()+", "+ m.getEmail();
	// post는 데이터를 body에 담아서 보냄 따라서 RequestBody 어노테이션을 설정하여 받아야함
	}
	
	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest() {
		return "put요청";
	}
	
	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete요청";
	}

}
