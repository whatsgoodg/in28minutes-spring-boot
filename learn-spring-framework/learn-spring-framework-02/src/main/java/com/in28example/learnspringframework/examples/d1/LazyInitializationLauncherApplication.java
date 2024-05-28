package com.in28example.learnspringframework.examples.d1;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
class ClassA{

}

@Component
@Lazy
class ClassB{
    private ClassA classA;
    public ClassB(ClassA classA) {
        System.out.println("Some Initialization logic");
        this.classA = classA;
    }

    public void do_something() {
        System.out.println("Do something after Initialization.");
    }
}
// 아래와 같이 configuration을 사용할 class에 같이 선언할 수 있음.
@Configuration
@ComponentScan // 알아서 현재 패키지에서 scan을 수행함.
public class LazyInitializationLauncherApplication {
    
    public static void main(String[] args) {

        try(var context =
                    new AnnotationConfigApplicationContext
                            (LazyInitializationLauncherApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
            context.getBean(ClassB.class).do_something();
        }
    }
}
