package com.mnutes.springboot.learnspringboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {
    @RequestMapping("/courses")
    public List<Course> retrieveAllCourses(){
        return Arrays.asList(
                new Course(1, "Learn AWS", "in28minutes"),
                new Course(1, "Learn DevOps", "in28minutes"),
                new Course(1, "Yo", "in28minutes"),
                new Course(1, "Fuck", "in28minutes"),
                new Course(1, "Fuck2", "in28minutes"),
                new Course(1, "Fuck2", "in28minutes"),
                new Course(1, "Fuck2", "in28minutes")
        );
    }

}
