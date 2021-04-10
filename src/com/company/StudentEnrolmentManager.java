package com.company;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

interface StudentEnrolmentManager {
    ArrayList<StudentEnrolment> studentEnrolmentList = new ArrayList<>();
    ArrayList<Student> studentArrayList = new ArrayList<>();
    ArrayList<Course> coursesArrayList = new ArrayList<>();
    ArrayList<String> semArrayList = new ArrayList<>();

    void addEnrolment(ArrayList<Student> studentArrayList, ArrayList<Course> courseArrayList) throws FileNotFoundException;

    void updateEnrolment() throws FileNotFoundException;

    void deleteEnrolment() throws FileNotFoundException;

    void getOne(String sid, String cid, String semester) throws FileNotFoundException;

    void getAll() throws FileNotFoundException;

}
