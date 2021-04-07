package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class StudentEnrolmentManagerImp implements StudentEnrolmentManager{
    //sub function 1
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

    //sub function 2
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

    //sub function 3
    public String inputSemester(Scanner scanner){
        String input = null;
        while (true) {
            System.out.print("Enter Semester: ");
            input = scanner.nextLine();
            return input;
        }

    }

    //Check if duplicate
    public void checkDuplicate(){

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
        boolean check = true;

        //Create an enrollment
        StudentEnrolment studentEnrolment = new StudentEnrolment(
                inputStudentId(new Scanner(System.in), studentArrayList),
                inputCourseId(new Scanner(System.in), courseArrayList),
                inputSemester(new Scanner(System.in)));

        StudentEnrolment studentEnrolment2 = new StudentEnrolment(
                inputStudentId(new Scanner(System.in), studentArrayList),
                inputCourseId(new Scanner(System.in), courseArrayList),
                inputSemester(new Scanner(System.in)));


        //Notice user what they did
        System.out.print("Successfully enroll: ");

        //Add enrolment into the List of enrolment.
        StudentEnrolmentManager.studentEnrolmentList.add(studentEnrolment);
        StudentEnrolmentManager.studentEnrolmentList.add(studentEnrolment2);

        for (StudentEnrolment s: StudentEnrolmentManager.studentEnrolmentList) {
            System.out.println(s.toString());
        }

    }

//    public Course courseBySem(){
//        Scanner scanner = new Scanner(System.in);
//        String input = scanner
//    }


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
        System.out.println("----------------All Enrolment----------------");
        Iterator<StudentEnrolment> iterator = StudentEnrolmentManager.studentEnrolmentList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
    }

    @Override
    public void enrollStudent() {
    }
}
