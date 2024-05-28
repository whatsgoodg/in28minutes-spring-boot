package com.in28example.learnspringframework.examples.f1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
class SomeClass{
    private SomeDependency someDependency;

    public SomeClass(SomeDependency someDependency){
        super();
        this.someDependency = someDependency;
        System.out.println("All Depedencies are ready!");
    }
    @PostConstruct
    public void initialize(){
        someDependency.getReady();
    }
    @PreDestroy
    public void cleanup(){
        System.out.println("CleanUP");
    }
}

@Component
class SomeDependency{
    public void getReady(){
        System.out.println("Some logic using SomeDependency");
    }
}
// 아래와 같이 configuration을 사용할 class에 같이 선언할 수 있음.
@Configuration
@ComponentScan // 알아서 현재 패키지에서 scan을 수행함.
public class PrePostLauncherApplication {
    
    public static void main(String[] args) {

        try(var context =
                    new AnnotationConfigApplicationContext
                            (PrePostLauncherApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        }
    }
}
