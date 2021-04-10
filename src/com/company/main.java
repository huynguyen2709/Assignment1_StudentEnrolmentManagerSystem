package com.company;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

public class main {
    private static StudentEnrolmentManagerImp imp = new StudentEnrolmentManagerImp();
    public static void main(String[] args) throws IOException {
        //always read file first
        MainMenu.impReaFile();
        MainMenu.mainMenu();

    }
}


