package com.baya.practice;

public class StringReverse {
    public static void main(String[] args) {
        String s = "Rajashree Mallik is a good student";

        printReverseLine(s);

        printStarts(5);
    }

    public static void printReverseLine(String s) {
        String[] arr = s.split(" ");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length(); j++) {
                System.out.print(arr[i].charAt(arr[i].length() - (j + 1)));
            }
            System.out.print(" ");
        }

        System.out.println("");
    }

    public static void printStarts(int n){
        for(int i=0;i<5;i++){
            for(int j=0;j<i+1;j++){
                System.out.print(" ");
                System.out.print("*");
            }
            System.out.println("\n");
        }
    }
}
