package com.in28minutes.springboot.learnjpaandhibernate.course.jdbc;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository // Database와 통신하는 Component
public class CourseJdbcRepository {
    @Autowired
    private JdbcTemplate springJdbcTemplate;
    private static String INSERT_QUERY =
            """
                insert into course (id, name, author) 
                values (?, ?, ?);
            """;
    private static String DELETE_QUERY =
            """
                delete from course where id=?;
            """;
    private static String SELECT_QUERY =
            """
                select * from course
                where id=?
            """;
    public void insert(Course course) {
        springJdbcTemplate.update(INSERT_QUERY,
                course.getId(), course.getName(), course.getAuthor());
    }

    public void deleteById(long id) {
        springJdbcTemplate.update(DELETE_QUERY,
                id);
    }
    public Course findById(long id) {
        // query로 인한 결과가 하나의 row라면 queryForObject를 사용할 수 있다.
        return springJdbcTemplate.queryForObject(SELECT_QUERY,
                new BeanPropertyRowMapper<>(Course.class), id);
        // ResultSet, 즉 테이블을 Bean으로 매핑할 수 있는데, 이를 Row Mapper를 사용할 수 있다.
        // Row Mapper는 ResultSet의 각 row를 Bean으로 매핑해준다.
    }
}
