package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
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

    private boolean checkDuplicateForEnrolment(StudentEnrolment inputStudentEnrolment){
        if (StudentEnrolmentManager.studentEnrolmentList != null) {
            for (StudentEnrolment studentEnrolment: StudentEnrolmentManager.studentEnrolmentList) {
                if (studentEnrolment.getStudent().getId().equals(inputStudentEnrolment.getStudent().getId())
                && studentEnrolment.getCourse().getId().equals(inputStudentEnrolment.getCourse().getId())
                && studentEnrolment.getSemester().equals(inputStudentEnrolment.getSemester())){
                    return false;
                }
            }
        }
        return true;
    };

    private void displayAvailableCouAndStu(ArrayList<Student> studentArrayList, ArrayList<Course> courseArrayList) {
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
    }

    @Override
    public void addEnrolment(ArrayList<Student> studentArrayList, ArrayList<Course> courseArrayList) {
        displayAvailableCouAndStu(studentArrayList, courseArrayList);

        //Create an enrollment
        StudentEnrolment studentEnrolment = new StudentEnrolment(
                inputStudentId(new Scanner(System.in), studentArrayList),
                inputCourseId(new Scanner(System.in), courseArrayList),
                inputSemester(new Scanner(System.in)));

        //Add enrolment into the List of enrolment.
        if (checkDuplicateForEnrolment(studentEnrolment) == true) {
            StudentEnrolmentManager.studentEnrolmentList.add(studentEnrolment);
            System.out.println("----------------Successfully add new enrolment----------------");
        }else {
            System.out.println("This enrolment already existed");
        }
    }

    public void miniAddInUpdate(String id){
        Scanner scanner = new Scanner(System.in);
        //Create an student
        Student chosenStudentUpdate = null;
        for (Student student: StudentEnrolmentManager.studentArrayList) {
           if (student.getId().equals(id)) {
                chosenStudentUpdate = student;
            }
        }


        System.out.println("************List of course************");
        for (Course course: StudentEnrolmentManager.coursesArrayList){
            System.out.println(course.toString());
        }

        //Create an course
        String input = null;
        Course chosenCourse = null;
        boolean check = true;
        while (check == true){
            System.out.print("Enter Course ID: ");
            input = scanner.nextLine();
            for (Course course: StudentEnrolmentManager.coursesArrayList) {
                if (course.getId().equals(input)) {
                    chosenCourse = course;
                    check = false;
                }
            }
        }

        //Create an course
        String inputSemester = null;
        System.out.print("Enter Semester: ");
        inputSemester = scanner.nextLine();


        //check duplicate for enrolment
        StudentEnrolment enrolmentForSpecificStudent = new StudentEnrolment(
                chosenStudentUpdate,
                chosenCourse,
                inputSemester);
        boolean display = true;
        if (checkDuplicateForEnrolment(enrolmentForSpecificStudent) == true){
            StudentEnrolmentManager.studentEnrolmentList.add(enrolmentForSpecificStudent);
            System.out.println("Successfully Add enrolment for student " + id);
        }else {
            System.out.println(id + " already enroll this course");
        }
    }

    @Override
    public void updateEnrolment() {
        //list all
        for (StudentEnrolment studentEnrolment: StudentEnrolmentManager.studentEnrolmentList){
            System.out.println(studentEnrolment.getStudent().getId() + "\t||\t" +
                    studentEnrolment.getStudent().getName() + "\t||\t" +
                    studentEnrolment.getStudent().getDob());
        }
        // choose to modify
        ArrayList<StudentEnrolment> newStudentEnrolmentArrayList = new ArrayList<>();
        String name = "";
        boolean check = true;
        while (check){
            System.out.println("****************************************");
            System.out.print("Enter ID of student you want to update: ");
            Scanner scanner = new Scanner(System.in);
            name = scanner.nextLine();
            boolean miniCheck = true;
            int count = 1;
            for (StudentEnrolment studentEnrolment: StudentEnrolmentManager.studentEnrolmentList){
                if (!studentEnrolment.getStudent().getId().equals(name)) {
                    miniCheck = false;
                }else {
                    System.out.println("This is " + studentEnrolment.getStudent().getName() + "'s enrolments number " + count + " " + ": ");
                    System.out.println(studentEnrolment.getCourse().toString(count));
                    newStudentEnrolmentArrayList.add(studentEnrolment);
                    count++;
                    check = false;
                    miniCheck = true;
                }
            }

            if (miniCheck == false){
                System.out.println("This student dont have any enrolments");
            }
        }

        boolean check2 = true;
        boolean runNext = true;
        while (check2 == true) {
            System.out.print("Do you want to add or delete(add/delete): ");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();
            if (option.equals("add")) {
                miniAddInUpdate(name);
                check2 = false;
                runNext = false;
            }else if (option.equals("delete")){
                //to print all course of that student
                int count = 1;
                System.out.println("************All course of " + name +"************");
                for (StudentEnrolment studentEnrolment: newStudentEnrolmentArrayList) {
                    System.out.println(count +". " + studentEnrolment.toString());
                    count++;
                }
                check2 = false;
            }
        }
        while (runNext == true){
            System.out.println("Choose number of enrolment you want to delete: ");
            Scanner scanner = new Scanner(System.in);
            int inputNum = scanner.nextInt();
            if (0 < inputNum && inputNum <= newStudentEnrolmentArrayList.size()) {
                StudentEnrolmentManager.studentEnrolmentList.remove(newStudentEnrolmentArrayList.get(inputNum - 1));

                getAll();
                break;
            }
        }
    }

    @Override
    public void deleteEnrolment() {
        //list all enrolments for Users to observe
        for (StudentEnrolment studentEnrolment: StudentEnrolmentManager.studentEnrolmentList){
            System.out.println(studentEnrolment.getStudent().getId() + "\t||\t" +
                    studentEnrolment.getStudent().getName() + "\t||\t" +
                    studentEnrolment.getStudent().getDob());
        }
        // choose to modify
        ArrayList<StudentEnrolment> newStudentEnrolmentArrayList = new ArrayList<>();
        String name = "";
        boolean check = true;
        while (check){
            System.out.println("****************************************");
            System.out.print("Enter ID of student you want to delete: ");
            Scanner scanner = new Scanner(System.in);
            name = scanner.nextLine();
            boolean miniCheck = true;
            for (StudentEnrolment studentEnrolment: StudentEnrolmentManager.studentEnrolmentList){
                if (!studentEnrolment.getStudent().getId().equals(name)) {
                    miniCheck = false;
                }else {
                    newStudentEnrolmentArrayList.add(studentEnrolment);
                    check = false;
                    miniCheck = true;
                }
            }

            if (miniCheck == false){
                System.out.println("This student dont have any enrolments");
            }
        }
        int count = 1;
        System.out.println("************All course of " + name +"************");
        for (StudentEnrolment studentEnrolment: newStudentEnrolmentArrayList) {
            System.out.println(count +". " + studentEnrolment.getCourse().toString(count));
            count++;
        }

        boolean runNext = true;
        while (runNext == true){
            System.out.println("Choose number of enrolment you want to delete: ");
            Scanner scanner = new Scanner(System.in);
            int inputNum = scanner.nextInt();
            if (0 < inputNum && inputNum <= newStudentEnrolmentArrayList.size()) {
                StudentEnrolmentManager.studentEnrolmentList.remove(newStudentEnrolmentArrayList.get(inputNum - 1));
                getAll();
                break;
            }
        }

    }

    @Override
    public void getOne() {

    }

    @Override
    public void getAll() {
        System.out.println("----------------All Enrolment----------------");
        Iterator<StudentEnrolment> iterator = StudentEnrolmentManager.studentEnrolmentList.iterator();
        int count = 1;
        while (iterator.hasNext()){
            System.out.println(iterator.next().toString(count));
            count++;
        }
    }

}
