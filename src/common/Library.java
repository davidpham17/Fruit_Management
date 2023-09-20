package common;

import java.util.ArrayList;
import java.util.Scanner;
import model.Fruit;
import model.Order;

public class Library {
   
    Scanner sc = new Scanner(System.in);
    
    public int inputIntLimit(int min, int max) {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in rage [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }
    
    public String inputString() {
    String result;
    while (true) {
        result = sc.nextLine().trim();
        if (!result.isEmpty() || !result.isBlank()) {
            return result;
            }
        }
    }
    
    public double inputPrice(double min) {
        double number = 0;
        while (number <= min) {
            number = sc.nextDouble();
        }
        return number;
    }
    
    public int inputQuantity(int min) {
        int number = 0;
        while (number <= min) {
            number = sc.nextInt();
        }
        return number;
    }
    
    public String inputCourse() {
        while (true) {
            String result = inputString();
            //check input course in java/ .net/ c/c++
            if (result.equalsIgnoreCase("java")
                    || result.equalsIgnoreCase(".net")
                    || result.equalsIgnoreCase("c/c++")) {
                return result;
            }
            System.err.println("There are only three courses: Java, .Net, C/C++");
            System.out.print("Enter again: ");
        }
    }
    
    public boolean checkItemExist(ArrayList<Order> orders, String id) {
        for (Order order : orders) {
            if (order.getId().equalsIgnoreCase(id)) {
                return false;
            }
        }
        return true;
    }

    
    public boolean inputYesNo() {
        while (true) {
            String result = inputString().trim();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            } else if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }
    
    public boolean inputUpdateDelete() {
        while (true) {
            String result = inputString();
            if (result.equalsIgnoreCase("U")) {
                return true;
            }
            if (result.equalsIgnoreCase("D")) {
                return false;
            }
            System.err.println("Please input u/U or d/D.");
            System.out.print("Enter again: ");
        }
    }

    public boolean checkIdExist(ArrayList<Fruit> fruits, String id) {
        for (Fruit f : fruits) {
            if (f.getId().equalsIgnoreCase(id)) {
                System.err.println("Id exist!");
                return false;
            }
        }
        return true;
    }
  
}
