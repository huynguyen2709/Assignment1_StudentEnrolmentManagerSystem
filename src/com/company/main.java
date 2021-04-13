package com.company;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static String fileName = "default.csv";
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to enter your file(yes/no): ");
        String answer = scanner.nextLine();
        if (answer.equals("yes")) {
            System.out.print("Enter Your file: ");
            fileName = scanner.nextLine();
            MainMenu.mainMenu();
        }else {
            MainMenu.mainMenu();
        }
    }
}


