package com.in28minutes.rest.webservice.restfulwebservices.user;

import com.in28minutes.rest.webservice.restfulwebservices.jpa.PostRepository;
import com.in28minutes.rest.webservice.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {
    private UserRepository userRepository;

    private PostRepository postRepository;
    public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }
   //Get /users
   @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("id:" + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());
        // 이 클래스의 AllUsers 매서드에 대한 링크 생성
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-user"));

        return entityModel;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostForUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("id:" + id);
        }
        return user.get().getPosts();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}"). //추가할 경로
                buildAndExpand(savedUser.getId()) //추가할 변수
                .toUri();
        // 응답 상태를 201로 전달
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("id:" + id);
        }
        post.setUser(user.get());
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}"). //추가할 경로
                        buildAndExpand(savedPost.getId()) //추가할 변수
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
