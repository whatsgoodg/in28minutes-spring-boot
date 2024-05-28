package com.in28example.learnspringframework.examples.a1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//YourBusinessClass: 생성자 주입, 수정자 주입, field 주입를 통해 아래의 클래스를 주입
@Component
class YourBusinessClass{
    @Autowired
    Dependency1 dependency1;
    @Autowired
    Dependency2 dependency2;

    @Autowired
    public YourBusinessClass(Dependency1 dependency1, Dependency2 dependency2){
        super();
        this.dependency1 = dependency1;
        this.dependency2 = dependency2;
    }
    public String toString(){
        //StringBuffer를 사용해봐라.
        return "Using " + dependency1 + " and " + dependency2;
    }
    @Autowired
    public void setDependency1(Dependency1 dependency1){
        this.dependency1 = dependency1;
    }
    @Autowired
    public void setDependency2(Dependency2 dependency2){
        this.dependency2 = dependency2;
    }
}
//Dependency1
@Component
class Dependency1{

}
//Dependency2:
@Component
class Dependency2{

}


// 아래와 같이 configuration을 사용할 class에 같이 선언할 수 있음.
@Configuration
@ComponentScan // 알아서 현재 패키지에서 scan을 수행함.
public class DepInjectionLauncherApplication {

    public static void main(String[] args) {

        try(var context =
                    new AnnotationConfigApplicationContext
                            (DepInjectionLauncherApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

            System.out.println(context.getBean(YourBusinessClass.class));
        }
    }
}
