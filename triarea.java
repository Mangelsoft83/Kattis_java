import java.util.Scanner;

public class triarea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double h = Math.max(Math.min(1000, scanner.nextDouble()),0);
        double b = Math.max(Math.min(1000, scanner.nextDouble()),0);

        System.out.println(0.5*h*b);
    }
}
