package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentEnrolmentManagerImp implements StudentEnrolmentManager{


    public Student inputStudentId(Scanner scanner, ArrayList<Student> studentArrayList){
        String input = null;
        boolean check = true;
        Student chosenStudent = null;
        while (check){
            System.out.print("Enter Student ID: ");
            input = scanner.nextLine();
            for (Student student: studentArrayList) {
                if (student.getId().equals(input)) {
                    chosenStudent = student;
                    return chosenStudent;
                }
            }
        }
        return chosenStudent;
    }

    public Course inputCourseId(Scanner scanner, ArrayList<Course> courseArrayList){
        String input = null;
        boolean check = true;
        Course chosenCourse = null;
        while (check){
            System.out.print("Enter Course ID: ");
            input = scanner.nextLine();
            for (Course course: courseArrayList) {
                if (course.getId().equals(input)) {
                    chosenCourse = course;
                    return chosenCourse;
                }
            }
        }
        return chosenCourse;
    }

    public String inputSemester(Scanner scanner){
        String input = null;
        while (true) {
            System.out.print("Enter Semester: ");
            input = scanner.nextLine();
            return input;
        }

    }

    @Override
    public void addStudent(ArrayList<Student> studentArrayList, ArrayList<Course> courseArrayList) {
        System.out.println("Available Course: ");
        for (Course course: courseArrayList) {
            System.out.println(course.toString());
        }
        System.out.println("Available Student: ");
        for (Student student: studentArrayList){
            System.out.println(student.toString());
        }

        //Create an enrollment
        StudentEnrolment studentEnrolment = new StudentEnrolment(
                        inputStudentId(new Scanner(System.in), studentArrayList),
                        inputCourseId(new Scanner(System.in), courseArrayList),
                        inputSemester(new Scanner(System.in)));

        //Notice user what they did
        System.out.print("Successfully enter: ");
        System.out.println(studentEnrolment.toString());

        //Add enrolment into the List of enrolment.
        StudentEnrolmentManager.studentEnrolmentList.add(studentEnrolment);


    }

    @Override
    public void updateStudent() {

    }

    @Override
    public void deleteStudent() {

    }

    @Override
    public void getOne() {

    }

    @Override
    public void getAll() {

    }

    @Override
    public void enrollStudent() {

    }
}
