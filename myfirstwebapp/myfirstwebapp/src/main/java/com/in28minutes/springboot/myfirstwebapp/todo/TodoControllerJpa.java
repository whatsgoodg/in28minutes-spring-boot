package com.in28minutes.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
    private TodoRepository todoRepository;
    public TodoControllerJpa(TodoRepository todoRepository) {
        super();
        this.todoRepository = todoRepository;
    }
    private String getloggedInUsername(ModelMap model){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
    // /list-todos
    @RequestMapping("/list-todos")
    public String listAllTodos(ModelMap model) {
        String username = getloggedInUsername(model);


        List<Todo> todos = todoRepository.
                findByUsername(username);
        model.addAttribute("todos", todos);
        return "listTodos";
    }
    @RequestMapping(value="/add-todo", method= RequestMethod.GET)
    public String showNewTodoPage(ModelMap model){
        Todo todo = new Todo(0, (String)model.get("name"), "",
                LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "todo";
    }
    @RequestMapping(value="/add-todo", method= RequestMethod.POST)
    public String addNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result){

        if(result.hasErrors()){
            return "todo";
        }
        String username = (String)model.get("name");
        todo.username = username;
        todoRepository.save(todo);
        // Redirect 하는 방법
        return "redirect:list-todos";
    }
    @RequestMapping("/delete-todo")
    public String deleteTodo(@RequestParam int id){
        //Delete todo
        todoRepository.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value="/update-todo", method= RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap model){
        Todo todo = todoRepository.findById(id).get();
        model.addAttribute("todo", todo);
        return "todo";
    }
    @RequestMapping(value="/update-todo", method= RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        String username = (String)model.get("name");
        todo.username = username;
        todoRepository.save(todo);
        return "redirect:list-todos";
    }
}
