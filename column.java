/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.javaapplication2;

/**
 *
 * @author Admin
 */
import java.util.Scanner;
public class column {

    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int r=3,c=3;
        int arr[][]={
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };
        System.out.println("1st row and 6th column elements");
        for(int i=1;i<r;i++){
            for(int j=1;j<c;j++){
                if(i==1||j==1)
                    System.out.print(arr[i][j]+"");
                
            }
        }
    }
}
