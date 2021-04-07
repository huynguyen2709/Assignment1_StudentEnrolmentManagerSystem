package com.company;

import java.util.ArrayList;

interface StudentEnrolmentManager {
    ArrayList<StudentEnrolment> studentEnrolmentList = new ArrayList<>();
    void addStudent();

    void updateStudent();

    void deleteStudent();

    void getOne();

    void getAll();

    void enrollStudent() ;
}
