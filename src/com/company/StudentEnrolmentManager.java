package com.company;

import java.util.ArrayList;

interface StudentEnrolmentManager {
    ArrayList<StudentEnrolment> studentEnrolmentList = new ArrayList<>();

    void addStudent(ArrayList<Student> studentArrayList, ArrayList<Course> courseArrayList);

    void updateStudent();

    void deleteStudent();

    void getOne();

    void getAll();

    void enrollStudent() ;
}
