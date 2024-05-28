package com.in28minutes.rest.webservices.restfulwebservices.todo;

import com.in28minutes.rest.webservices.restfulwebservices.todo.todo.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoJpaResource {
    private TodoService todoService;
    private TodoRepository todoRepository;
    public TodoJpaResource(TodoService todoService, TodoRepository todoRepository){
        this.todoRepository = todoRepository;
        this.todoService = todoService;
    }
    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username) {
        return todoRepository.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username,
                                   @PathVariable int id) {
        //return todoService.findById(id);
        return todoRepository.findById(id).get();
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username,
                                     @PathVariable int id) {
        //todoService.deleteById(id);
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}") // Todo 콘텐츠와 함께 반환함.
    public Todo updateTodo(@PathVariable String username,
                                           @PathVariable int id, @RequestBody Todo todo) {
        //todoService.updateTodo(todo);
        todoRepository.save(todo);
        return todo;
    }

    @PostMapping("/users/{username}/todos") // Todo 콘텐츠와 함께 반환함.
    public Todo createTodo(@PathVariable String username,
                           @RequestBody Todo todo) {
        todo.setUsername(username);
        todo.setId(null);
        return todoRepository.save(todo);
    }
}
