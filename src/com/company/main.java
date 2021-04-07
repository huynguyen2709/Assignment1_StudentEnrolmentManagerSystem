package com.company;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        //Student populated
        Student student1 = new Student("s3825059", "John", "02/01/2001");
        Student student2 = new Student("s12345678", "Nathan", "27/09/2000");
        Student student3 = new Student("s38113850", "Nickel", "04/01/1998");
        ArrayList<Student> studentArrayList = new ArrayList<>();
        studentArrayList.add(student1);
        studentArrayList.add(student2);
        studentArrayList.add(student3);

        //Course populated
        Course course1 = new Course("COSC111","UCD",12);
        Course course2 = new Course("COSC222","Programming 1",12);
        Course course3 = new Course("COSC333","Intro to IT",12);
        Course course4 = new Course("COCY704","Software Architecture",12);
        Course course5 = new Course("YIUS182","Design",12);
        Course course6 = new Course("COUIE123","Software Engineering",12);
        ArrayList<Course> coursesArrayList = new ArrayList<>();
        coursesArrayList.add(course1);coursesArrayList.add(course2);
        coursesArrayList.add(course3);coursesArrayList.add(course4);
        coursesArrayList.add(course5);coursesArrayList.add(course6);

        StudentEnrolmentManagerImp imp = new StudentEnrolmentManagerImp();
        imp.addStudent(studentArrayList, coursesArrayList);
        imp.getAll();
    }
}

