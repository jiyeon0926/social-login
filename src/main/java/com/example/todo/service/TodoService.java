package com.example.todo.service;

import com.example.todo.entity.Todo;
import com.example.todo.entity.User;
import com.example.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public Todo addTodo(String title, String description, User user) {
        Todo todo = new Todo(title, description, user);

        return todoRepository.save(todo);
    }

    @Transactional(readOnly = true)
    public List<Todo> getTodosByUser(User user) {
        return todoRepository.findByUserId(user.getId());
    }

    @Transactional
    public void deleteTodoById(Long id, User user) {
        Todo todo = checkOwnershipOrThrow(id, user);
        todoRepository.delete(todo);
    }

    @Transactional(readOnly = true)
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    @Transactional
    public void updateTodo(Long id, String title, String description, User user) {
        Todo todo = checkOwnershipOrThrow(id, user);
        todo.updateTodo(title, description);
    }

    private Todo checkOwnershipOrThrow(Long id, User user) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found"));

        if (todo.isNotOwnedBy(user)) {
            throw new SecurityException("Unauthorized");
        }

        return todo;
    }
}
