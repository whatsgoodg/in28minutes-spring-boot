package com.in28example.learnspringframework.examples.a0;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

// 아래와 같이 configuration을 사용할 class에 같이 선언할 수 있음.
@Configuration
@ComponentScan // 알아서 현재 패키지에서 scan을 수행함.
public class DepInjectionLauncherApplication {
    
    public static void main(String[] args) {

        try(var context =
                    new AnnotationConfigApplicationContext
                            (DepInjectionLauncherApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        }
    }
}
