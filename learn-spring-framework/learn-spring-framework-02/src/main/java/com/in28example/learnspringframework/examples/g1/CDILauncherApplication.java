package com.in28example.learnspringframework.examples.g1;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import practice1.DataService;

import java.util.Arrays;

//@Component
@Named
class BusinessService{
    Dataservice dataService;
    public Dataservice getDataservice(){
        return dataService;
    }
    //@Autowired
    @Inject
    public void setDataService(Dataservice dataService){
        System.out.println("Setter injection");
        this.dataService = dataService;
    }
}

@Named
class Dataservice {

}
// 아래와 같이 configuration을 사용할 class에 같이 선언할 수 있음.
@Configuration
@ComponentScan // 알아서 현재 패키지에서 scan을 수행함.
public class CDILauncherApplication {
    
    public static void main(String[] args) {

        try(var context =
                    new AnnotationConfigApplicationContext
                            (CDILauncherApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

            System.out.println(context.getBean(BusinessService.class).getDataservice());
        }
    }
}
