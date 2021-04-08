package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class StudentEnrolmentManagerImp implements StudentEnrolmentManager{

    //sub function 1
    public Student inputStudentId(Scanner scanner, ArrayList<Student> studentArrayList){
        String input = null;
        Student chosenStudent = null;
        while (true){
            System.out.print("Enter Student ID: ");
            input = scanner.nextLine();
            for (Student student: studentArrayList) {
                if (student.getId().equals(input)) {
                    chosenStudent = student;
                    return chosenStudent;
                }
            }
        }
    }

    //sub function 2
    public Course inputCourseId(Scanner scanner, ArrayList<Course> courseArrayList){
        String input = null;
        Course chosenCourse = null;
        while (true){
            System.out.print("Enter Course ID: ");
            input = scanner.nextLine();
            for (Course course: courseArrayList) {
                if (course.getId().equals(input)) {
                    chosenCourse = course;
                    return chosenCourse;
                }
            }
        }
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

    //Read file data from CSV file and create Enrolments
    public void readFileCsv() throws FileNotFoundException {
        String filename = "default.csv";
        File file = new File(filename);
        Scanner inputStream = new Scanner(file);
        int count = 0;
        while (inputStream.hasNext()){
            String data = inputStream.nextLine();
            String[] dataArray = data.split(",");

            dataArray[0] = dataArray[0].replaceAll("[^a-zA-Z0-9]", "");
            Student student = new Student(dataArray[0], dataArray[1], dataArray[2]);
            //add students to ArrayList
            boolean checkStudent = checkDuplicateForStudent(student);
            if (checkStudent == true){
                StudentEnrolmentManager.studentArrayList.add(student);
            }
            Course course = new Course(dataArray[3],dataArray[4],Integer.parseInt(dataArray[5]));
            //add courses to ArrayList
            boolean checkCourses = checkDuplicateForCourses(course);
            if (checkCourses == true) {
                StudentEnrolmentManager.coursesArrayList.add(course);
            }
            StudentEnrolment studentEnrolment = new StudentEnrolment(student, course, dataArray[6]);
            StudentEnrolmentManager.studentEnrolmentList.add(studentEnrolment);
        }
    }

    public boolean checkDuplicateForCourses(Course inputCourse){
        if (StudentEnrolmentManager.coursesArrayList == null) {
            return true;
        } else {
            for (Course course: StudentEnrolmentManager.coursesArrayList){
                if (course.getId().equals(inputCourse.getId())) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkDuplicateForStudent(Student inputStudent){
        if (StudentEnrolmentManager.studentArrayList == null) {
            return true;
        } else {
            for (Student student: StudentEnrolmentManager.studentArrayList){
                if (student.getId().equals(inputStudent.getId())) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void addEnrolment(ArrayList<Student> studentArrayList, ArrayList<Course> courseArrayList) throws FileNotFoundException {
        System.out.println("Available Course: ");
        int countCourse = 1;
        for (Course course: courseArrayList) {
            System.out.println(course.toString(countCourse));
            countCourse++;
        }
        System.out.println("Available Student: ");
        int countStudent = 1;
        for (Student student: studentArrayList){
            System.out.println(student.toString(countStudent));
            countStudent++;
        }
        boolean check = true;

        //Create an enrollment
        StudentEnrolment studentEnrolment = new StudentEnrolment(
                inputStudentId(new Scanner(System.in), studentArrayList),
                inputCourseId(new Scanner(System.in), courseArrayList),
                inputSemester(new Scanner(System.in)));


        //Notice user what they did
        System.out.println("Successfully enroll: ");

        //Add enrolment into the List of enrolment.
        StudentEnrolmentManager.studentEnrolmentList.add(studentEnrolment);
        System.out.println("Total enrolment:");
        for (StudentEnrolment s: StudentEnrolmentManager.studentEnrolmentList) {
            System.out.println(s.toString());
        }

    }


    @Override
    public void updateEnrolment() {
        //list all
        for (StudentEnrolment studentEnrolment: StudentEnrolmentManager.studentEnrolmentList){
            System.out.println(studentEnrolment.toString());
        }
        // choose to modify
        boolean check = true;
        while (check){
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            for (StudentEnrolment studentEnrolment: StudentEnrolmentManager.studentEnrolmentList){
                if (!studentEnrolment.getStudent().getName().equals(name)) {
                    System.out.println("This student dont have any enrolments.");
                }else {
                    System.out.println("This are " + studentEnrolment.getStudent().getName().equals(name) + "'s enrolments: ");
                    check = false;
                }
            }
        }
    }

    @Override
    public void deleteEnrolment() {
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
