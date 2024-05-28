package com.in28minutes.springboot.learnjpaandhibernate.course.jpa;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
@Repository
@Transactional
public class CourseJpaRepository {
    // JPA를 통해 DB에 연결하려면 EntityManager가 필요하다.
    // @PersistenceContext는 컨테이너 관리형 EntityManager에 연결된 종속성을 연결한다.
    // 영속성을 관리해주는 컨텍스트가 존재한다. 엔티티를 해당 컨텍스트에서 관리한다.
    // 그리고 멀티 쓰레드간의 동시성을 보장해주는 것 같다.
    @PersistenceContext
    private EntityManager entityManager;
    // 객체를 그대로 row로 insert하는 매서드, 그럼 Course 멤버의 @id, @column 등이 알아서 매핑된다.
    public void insert(Course course){
        entityManager.merge(course);
    }

    public Course findById(long id){
        return entityManager.find(Course.class, id);
    }
    public void deleteById(long id){
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }
}
