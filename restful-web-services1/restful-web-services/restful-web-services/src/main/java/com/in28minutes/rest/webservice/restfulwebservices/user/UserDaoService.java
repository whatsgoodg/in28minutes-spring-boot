package com.in28minutes.rest.webservice.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// 모든 사용자를 검색
// 특정 사용자의 상세 정보 저장
// 특정 사용자의 상세 정보를 반환
// JPA/Hibernate를 통해 DB와 연동함.
@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int userCount = 0;

    static{
        users.add(new User(++userCount, "Adam",
                LocalDate.now().minusYears(30)));
        users.add(new User(++userCount, "Eve",
                LocalDate.now().minusYears(25)));
        users.add(new User(++userCount, "Jim",
                LocalDate.now().minusYears(20)));
    }

    public List<User> findAll(){
        return users;
    }

    public User findOne(int id){
        Predicate<? super User> predicate = user -> user.getId() == id;
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id){
        Predicate<? super User> predicate = user -> user.getId() == id;
        users.removeIf(predicate);
    }

    public User save(User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }
}
