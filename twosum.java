import java.util.Scanner;

public class twosum {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int a = Math.max(Math.min(1000, scanner.nextInt()),0);
        int b = Math.max(Math.min(1000, scanner.nextInt()),0);

        System.out.println(a+b);
    }
}
