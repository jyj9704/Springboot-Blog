package com.sa.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data // getter, setter 동시에 만드는 어노테이션
@NoArgsConstructor
public class Member {
	private int id; // final = 데이터 불변성 설정
	private String username;
	private String password;
	private String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		//@AllArgsConstructor // 모든 필드를 사용하는 생성자
	}
	
	
}