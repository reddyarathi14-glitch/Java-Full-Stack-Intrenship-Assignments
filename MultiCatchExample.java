/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.multicatchexample;

/**
 *
 * @author Admin
 */
public class MultiCatchExample {
    public static void main(String[] args) {

        try {
            int arr[] = new int[5];
            arr[10] = 50;   // ArrayIndexOutOfBoundsException
        }
        catch (ArithmeticException e) {
            System.out.println("Arithmetic Error");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Index Error");
        }

    }
}

    

