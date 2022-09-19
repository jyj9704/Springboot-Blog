package com.sa.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;


//ORM -> Java(다른언어) object -> 테이블로 매핑해주는 기술
@Entity // User 클래스를 통해 데이터를 읽어서 자동으로 MySql에 데이블이 생성
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
	
	@ColumnDefault("'user'")
	private String role; // Enum // admin, user, manager
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;

}
