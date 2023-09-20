package controller;

import common.Library;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import model.Fruit;
import model.Order;
import view.Menu;

public class FruitManager extends Menu<String>{
    static String[] mc = {"Create Fruit", "View orders", "Shopping","Exit"};
    ArrayList<Fruit> fruits = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    
    protected Library library;
    Hashtable<String, ArrayList<Order>> ht = new Hashtable<>();
    
    public FruitManager(Fruit fruit) {
        super("FRUIT SHOP SYSTEM", mc);
        library = new Library();
    }

    public void createFruit(ArrayList<Fruit> fruits) {
        while (true) {
            System.out.println("Enter ID Fruit:");
            String id = library.inputString().trim();
            if (library.checkIdExist(fruits, id)){
            System.out.println("Enter name Fruit:");
            String name = library.inputString().trim();
            System.out.println("Enter price:");
            double price = library.inputPrice(0);
            System.out.println("Enter quantity:");
            int quantity = library.inputQuantity(0);
            System.out.println("Enter origin:");
            String origin = library.inputString().trim();
            Fruit f = new Fruit(id, name, price, quantity, origin);
            if (library.checkIdExist(fruits, id)) {
                fruits.add(f);
            System.err.println("Create success!");
            }
            System.out.print("Do you want to create more fruit (Y/N): ");
            if (!library.inputYesNo()) {
                return;
            }
            }
        }
    }
    
    public void printListFruit(ArrayList<Fruit> fruits){
        if (fruits == null) return; 
        if(fruits.isEmpty()) System.out.println("Empty list!");
        else {
            int countItem = 1;
            System.out.println("List of fruit:");
            System.out.printf("%-10s%-20s%-20s%-15s\n", "Item", "Fruit name", "Origin", "Price");
            System.out.println("--------------------------------------------------------------------------");
            for (Fruit f : fruits) {
                System.out.printf("%-10d%-20s%-20s%-15.0f\n", countItem++,
                        f.getName(), f.getOrigin(), f.getPrice());
            }
            System.out.println("Total: " + fruits.size() + " fruit.");
            System.out.println("--------------------------------------------------------------------------");
            }
    }
    
    public Fruit getFruitByItem(ArrayList<Fruit> fruits, int item) {
        int countItem = 1;
        for (Fruit fruit : fruits) {
            if (fruit.getQuantity() != 0) {
                countItem++;
            }
            if (countItem - 1 == item) {
                return fruit;
            }
        }
        return null;
    }
    
    public void viewOrder(Hashtable<String, ArrayList<Order>> ht){
        for (String name : ht.keySet()) {
            System.out.println("Customer: " + name);
            ArrayList<Order> orders = ht.get(name);
            displayListOrder(orders);
        }
    }
    
    public void displayListOrder(ArrayList<Order> lo) {
        double total = 0;
        System.out.printf("%15s %15s %15s %15s\n", "Product", "Quantity", "Price", "Amount");
        for (Order order : lo) {
            System.out.printf("%15s %15d %15.0f $%15.0f$\n", order.getName(),
                    order.getQuantity(), order.getPrice(),
                    order.getPrice() * order.getQuantity());
            total += order.getPrice() * order.getQuantity();
        }
        System.out.println("Total: " + total);
    }
    
    
    public void shopping(ArrayList<Fruit> fruits, Hashtable<String, ArrayList<Order>> ht) {
        if (fruits.isEmpty()) {
            System.err.println("No have item.");
            return;
        }
        ArrayList<Order> orders = new ArrayList<>();
        while (true) {
            printListFruit(fruits);
            System.out.print("Enter item: ");
            int item = library.inputIntLimit(1, fruits.size());
            Fruit f = getFruitByItem(fruits, item);
            System.out.print("Enter quantity: ");
            int quantity = library.inputQuantity(0);
            f.setQuantity(f.getQuantity() - quantity);

            if (!library.checkItemExist(orders, f.getId())) {
                updateOrder(orders, f.getId(), quantity);
            } else {
                orders.add(new Order(f.getId(), f.getName(),quantity, f.getPrice()));
            }
            System.out.print("Do you want to create more fruit (Y/N): ");
            if (!library.inputYesNo()) {
                break;
            }
        }
        displayListOrder(orders);
        System.out.print("Enter name: ");
        String name = library.inputString().trim();
        ht.put(name, orders);
        System.err.println("Add successfull");
    }
    
    public void updateOrder(ArrayList<Order> orders, String id, int quantity) {
        for (Order order : orders) {
            if (order.getId().equalsIgnoreCase(id)) {
                order.setQuantity(order.getQuantity() + quantity);
                return;
            }
        }
    }  

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                createFruit(fruits);
                printListFruit(fruits);
                break;
            case 2:
                viewOrder(ht);
                break;
            case 3:
                shopping(fruits, ht);
                break;
            case 4:
                System.out.println("Exit the program successfully!");
                System.exit(0);
                break;
        }
    }   
    

}