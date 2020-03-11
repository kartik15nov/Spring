package com.baya.practice;

public class Pyramid1 {
    public static void printTriangle(int n)
    {
        for(int i=0;i<n;i++){
            for(int j=0;j<n-i-1;j++){
                System.out.print(" ");
            }
            for(int k=0;k<i+1;k++){
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printTriangle(3);
    }
}
class Pyramid2{
    public static void main(String[] args) {
        for(int i=0;i<3;i++){
            for(int j=0;j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

