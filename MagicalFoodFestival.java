/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.magicalfoodfestival;

/**
 *
 * @author Admin
 */
import java.util.*;

public class MagicalFoodFestival {

    public static void main(String[] args) {

        ArrayList<String> stalls = new ArrayList<>();
        stalls.add("Pizza Stall");
        stalls.add("Dosa Stall");

        stalls.add("Ice Cream Stall");

        System.out.println("Food Stalls: " + stalls);

        Vector<String> dishes = new Vector<>();
        dishes.add("Pizza");
        dishes.add("Masala Dosa");
        dishes.add("Chocolate Ice Cream");

        System.out.println("Updated Dishes: " + dishes);

        Queue<String> customers = new LinkedList<>();
        customers.add("Arun");
        customers.add("Meena");
        customers.add("Rahul");

        System.out.println("\nServing Customers:");
        while (!customers.isEmpty()) {
            System.out.println("Serving: " + customers.poll());
        }

        Stack<String> lastVisited = new Stack<>();
        lastVisited.push("Pizza Stall");
        lastVisited.push("Dosa Stall");
        lastVisited.push("Ice Cream Stall");

        System.out.println("\nLast Visited Stall: " + lastVisited.pop());

        LinkedList<String> history = new LinkedList<>();
        history.add("Arun visited Pizza Stall");
        history.add("Meena visited Dosa Stall");
        history.add("Rahul visited Ice Cream Stall");

        System.out.println("\nDaily Festival History:");
        for (String record : history) {
            System.out.println(record);
        }
    }
}

        
    

