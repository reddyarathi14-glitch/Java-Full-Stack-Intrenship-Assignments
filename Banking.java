/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bankingsystem;

/**
 *
 * @author Admin
 */
import java.util.Scanner;

class BankingSystem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double balance = 5000;   // Initial balance

        try {
            System.out.println("Enter amount to pay (Google Pay): ");
            double amount = sc.nextDouble();

            System.out.println("Is Network Available? (true/false): ");
            boolean network = sc.nextBoolean();

            if (!network) {
                throw new Exception("Network Issue! Try again.");
            }

            if (amount > balance) {
                throw new Exception("Insufficient Balance!");
            }

            balance -= amount;
            System.out.println("Payment Successful.");
            System.out.println("Remaining Balance: " + balance);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Thank you for using Banking System.");
        }

        sc.close();
    }
}

    

