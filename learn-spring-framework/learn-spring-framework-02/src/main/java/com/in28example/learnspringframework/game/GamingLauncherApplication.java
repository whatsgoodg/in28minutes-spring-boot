package com.in28example.learnspringframework.game;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;

// 아래와 같이 configuration을 사용할 class에 같이 선언할 수 있음.
@Configuration
@ComponentScan("com.in28example.learnspringframework.game")
public class GamingLauncherApplication {
    public static void main(String[] args) {

        try(var context =
                    new AnnotationConfigApplicationContext
                            (GamingLauncherApplication.class)) {
            context.getBean(GamingConsole.class).up();

            context.getBean(GameRunner.class).run();
        }
    }
}
