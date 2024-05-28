package com.in28minutes.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    private static int todosCount = 0;
    static{
        todos.add(new Todo(++todosCount, "in28minutes", "Learn AWS",
                LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "in28minutes", "Learn DevOps",
                LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todosCount, "in28minutes", "Learn Full Stack",
                LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUsername(String username){
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description,
                        LocalDate targetDate, Boolean done){
        Todo todo = new Todo(++todosCount, username,description, targetDate, done);
        todos.add(todo);
    }
    public void deleteById(int id){
        // 람다 함수, todo -> todo.getId() 를 모든 todo에 실행하게 됨.
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        return todos.stream().filter(predicate).findFirst().get();
    }

    public void updateTodo(@Valid Todo todo){
        deleteById(todo.getId());
        todos.add(todo);
    }
}
