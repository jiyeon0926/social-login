package com.example.todo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "todos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private boolean completed; // 완료 여부 (true: 완료됨, false: 미완료)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Todo(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.completed = false;
        this.user = user;
    }

    public boolean isOwnedBy(User user) {
        return this.user.getId().equals(user.getId());
    }

    public boolean isNotOwnedBy(User user) {
        return !isOwnedBy(user);
    }

    public void updateTodo(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
