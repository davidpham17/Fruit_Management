package main;

import controller.FruitManager;
import model.Fruit;

public class Main {
    
    public static void main(String[] args) {
        Fruit f = new Fruit();
        
        new FruitManager(f).run();
    }
    
}
