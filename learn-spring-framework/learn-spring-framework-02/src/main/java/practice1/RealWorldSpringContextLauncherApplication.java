package practice1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan // 알아서 현재 패키지에서 scan을 수행함.
public class RealWorldSpringContextLauncherApplication {

    public static void main(String[] args) {

        try(var context =
                    new AnnotationConfigApplicationContext
                            (RealWorldSpringContextLauncherApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

            System.out.println(context.getBean(BusinessCalculationService.class).findMax());
        }
    }
}
