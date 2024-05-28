package com.in28minutes.springboot.learnjpaandhibernate.course;

import com.in28minutes.springboot.learnjpaandhibernate.course.jdbc.CourseJdbcRepository;
import com.in28minutes.springboot.learnjpaandhibernate.course.jpa.CourseJpaRepository;
import com.in28minutes.springboot.learnjpaandhibernate.course.springdatajpa.CourseSpringDataJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.in28minutes.springboot.learnjpaandhibernate.course.Course;

@Component
// CommandLine Runner를 상속받은 Class의 Bean은 SpringApplication에 포함되어 있을 때, 실행된다.
public class CourseCommandLineRunner implements CommandLineRunner {
    //@Autowired
    //private CourseJdbcRepository repository;
    //@Autowired
    //private CourseJpaRepository repository;
    @Autowired
    private CourseSpringDataJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
//        repository.insert(new Course(1, "Learn AWS", "in28minutes"));
//        repository.insert(new Course(2, "Learn Azure", "in28minutes"));
//        repository.insert(new Course(3, "Learn DevOps", "in28minutes"));
//        repository.deleteById(1);
//
//        System.out.println(repository.findById(2));
//        System.out.println(repository.findById(3));

        // Spring Data JPA는 save method를 사용한다.
        repository.save(new Course(1, "Learn AWS", "in28minutes"));
        repository.save(new Course(2, "Learn Azure", "in28minutes"));
        repository.save(new Course(3, "Learn DevOps", "in28minutes"));
        repository.deleteById(1l);

        System.out.println(repository.findById(2l));
        System.out.println(repository.findById(3l));

        // 테이블에 존재하는 모든 엔티티를 반환함.
        System.out.println(repository.findAll());
        // 테이블에 존재하는 모든 엔티티의 수를 반함.
        System.out.println(repository.count());

        System.out.println(repository.findByAuthor("in28minutes"));
        System.out.println(repository.findByAuthor(""));

        System.out.println(repository.findByName("Learn Azure"));
        System.out.println(repository.findByName(""));
    }
}
