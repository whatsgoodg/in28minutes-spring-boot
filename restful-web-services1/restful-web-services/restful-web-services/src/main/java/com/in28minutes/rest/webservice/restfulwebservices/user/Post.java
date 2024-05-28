package com.in28minutes.rest.webservice.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min=10)
    private String description;
    // 얘는 post를 가져올 때, USER를 느리게 가져오는거 같음
    // eager를 사용하면 바로 가져오는 듯
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
    public Post(Integer id) {
        this.id = id;
    }

    public Post(){

    }
    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
