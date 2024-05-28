package com.in28minutes.rest.webservice.restfulwebservices.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

//REST API을 노출함.
@RestController
public class HelloWorldController {
    // URL을 부여할 것임.
    // /hello-world
    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    @GetMapping(path="/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path="/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello world, %s", name));
    }

    @GetMapping(path="/hello-world-internationalized")
    public String helloWorldInternationalized(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(
                "good.morning.message", null,
                "Default Message", locale);
    }
}
