package com.in28example.learnspringframework.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
public class GameRunner {
    GamingConsole game;
    public GameRunner( GamingConsole game){
        this.game = game;
    }
    public void run(){
        System.out.println("Running game: " + game);
        game.up();
        game.down();
        game.left();
        game.right();
    }
}
