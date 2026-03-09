/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package addeventhread;

/**
 *
 * @author Admin
 */
class AddEven implements Runnable {
    public void run() {
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.println("Even: " + i);
            } else {
                System.out.println("Odd: " + i);
            }
        }
    }
}

public class AddEven {
    public static void main(String[] args) {
        Thread t = new Thread(new AddEven());
        t.start();
    }
}

