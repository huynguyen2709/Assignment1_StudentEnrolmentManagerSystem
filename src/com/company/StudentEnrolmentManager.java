package com.company;

import java.util.ArrayList;
import java.util.HashSet;

interface StudentEnrolmentManager {
    ArrayList<StudentEnrolment> studentEnrolmentList = new ArrayList<>();
    HashSet<Student> setOfStudent = new HashSet<>();
    HashSet<Course> setOfCourse = new HashSet<>();
    HashSet<String> setOfSemester = new HashSet<>();


    void addStudent(ArrayList<Student> studentArrayList, ArrayList<Course> courseArrayList);

    void updateStudent();

    void deleteStudent();

    void getOne();

    void getAll();

    void enrollStudent() ;
}
