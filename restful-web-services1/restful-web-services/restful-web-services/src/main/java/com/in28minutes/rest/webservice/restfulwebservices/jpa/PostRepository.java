package com.in28minutes.rest.webservice.restfulwebservices.jpa;

import com.in28minutes.rest.webservice.restfulwebservices.user.Post;
import com.in28minutes.rest.webservice.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
