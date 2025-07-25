package com.website.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class User { //User 테이블
    @Id //PK설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto Increment 설정
    @Column(name = "user_code") //컬럼 이름 설정
    private Long userCode;  //이 프로젝트에서 user_code 컬럼을 쓸 이름 결정

    @Column(name = "user_id") //Unique Key 설정
    private String userId;

    @Column //없다면 필드 이름 그대로 사용
    private String password;

    @Column
    private String role;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    @Column(name = "name")
    private String name;

    //회원 가입용 생성자
    public User(String userId, String password, String role, LocalDateTime createAt) {
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.createAt = createAt;
    }

    public User(String userId, String password, String role, LocalDateTime createAt, String name) {
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.createAt = createAt;
        this.name = name;
    }
}
