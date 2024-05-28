package com.in28minutes.rest.webservice.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService service;
    public UserResource(UserDaoService service) {
        this.service = service;
    }
   //Get /users
   @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if(user==null){
            throw new UserNotFoundException("id:" + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user);
        // 이 클래스의 AllUsers 매서드에 대한 링크 생성
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-user"));

        return entityModel;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}"). //추가할 경로
                buildAndExpand(savedUser.getId()) //추가할 변수
                .toUri();
        // 응답 상태를 201로 전달
        return ResponseEntity.created(location).build();
    }

}
