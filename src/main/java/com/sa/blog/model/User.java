package com.sa.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//ORM -> Java(다른언어) object -> 테이블로 매핑해주는 기술

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴!!
@Entity // User 클래스를 통해 데이터를 읽어서 자동으로 MySql에 데이블이 생성
// @DynamicInsert // insert시 null인 필드 제외
public class User {
	
	@Id // primary key	
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // oracle = 시퀀스, mysql = auto_increment
	
	@Column(nullable = false, length = 30)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	// @ColumnDefault("user")
	//DB는 RoleType이 없음
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum // ADMIN, USER // RoleType = 타입이 두개만 넣을 수 있도록 강제로 설정
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;

}
