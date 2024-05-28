package com.in28minutes.springboot.learnjpaandhibernate.course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// JPA를 사용하기 위한 Entity, Id, Column
@Entity
public class Course {
    @Id
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="author")
    private String author;
    //constructor

    public Course(){
    }
    //getters
    //toString

    public Course(long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
