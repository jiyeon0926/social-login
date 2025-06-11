package com.example.todo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Todo> todos; // 할 일이 중복없이 저장되게 하기 위해 List가 아닌 Set 사용

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
