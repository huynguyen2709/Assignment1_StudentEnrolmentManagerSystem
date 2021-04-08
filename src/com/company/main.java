package com.company;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {

        StudentEnrolmentManagerImp imp = new StudentEnrolmentManagerImp();
        imp.readFileCsv();
        imp.addEnrolment(StudentEnrolmentManager.studentArrayList, StudentEnrolmentManager.coursesArrayList);
    }
}


