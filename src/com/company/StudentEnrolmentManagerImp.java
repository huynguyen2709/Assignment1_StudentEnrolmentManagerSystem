package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class StudentEnrolmentManagerImp implements StudentEnrolmentManager{
    public static int countForGetOne = 0;
     ArrayList<StudentEnrolment> studentEnrolmentList = new ArrayList<>();
     ArrayList<Student> studentArrayList = new ArrayList<>();
     ArrayList<Course> coursesArrayList = new ArrayList<>();

    //take out the Student student which match the user's input
    private Student inputStudentId(Scanner scanner,ArrayList<Student> studentArrayList){
        String input = null;
        Student chosenStudent = null;
        while (true){
            System.out.print("Enter Student ID: ");
            input = scanner.nextLine();
            //check match
            for (Student student: studentArrayList) {
                if (student.getId().equals(input)) {
                    chosenStudent = student;
                    return chosenStudent;
                }
            }
        }
    }

    //take out the Course course which match the user's input
    private Course inputCourseId(Scanner scanner,ArrayList<Course> courseArrayList){
        String input = null;
        Course chosenCourse = null;
        while (true){
            System.out.print("Enter Course ID: ");
            input = scanner.nextLine();
            for (Course course: courseArrayList) {
                //check match
                if (course.getId().equals(input)) {
                    chosenCourse = course;
                    return chosenCourse;
                }
            }
        }
    }

    //take out the String semester from user's input
    private String inputSemester(Scanner scanner){
        String input = null;
        while (true) {
            System.out.print("Enter Semester: ");
            input = scanner.nextLine();
            return input;
        }
    }

    //Read file data from CSV file and create Enrolments
    private void readFileCsv() throws FileNotFoundException {
        String filename = main.fileName;
        //read file
        File file = new File(filename);
        Scanner inputStream = new Scanner(file);
        while (inputStream.hasNext()){
            String data = inputStream.nextLine();
            String[] dataArray = data.split(",");
            //remove special characters
            dataArray[0] = dataArray[0].replaceAll("[^a-zA-Z0-9]", "");
            Student student = new Student(dataArray[0], dataArray[1], dataArray[2]);
            //add students to ArrayList
            boolean checkStudent = checkDuplicateForStudent(student);
            if (checkStudent == true){
                studentArrayList.add(student);
            }
            Course course = new Course(dataArray[3],dataArray[4],Integer.parseInt(dataArray[5]));
            //add courses to ArrayList
            boolean checkCourses = checkDuplicateForCourses(course);
            if (checkCourses == true) {
                coursesArrayList.add(course);
            }
            StudentEnrolment studentEnrolment = new StudentEnrolment(student, course, dataArray[6]);
            studentEnrolmentList.add(studentEnrolment);
        }
    }

    //Take added semester
    private ArrayList<String> readFileTakeSemList() throws FileNotFoundException {
        Iterator<StudentEnrolment> iterator = studentEnrolmentList.iterator();
        ArrayList<String> holdAddedSem = new ArrayList<>();
        while (iterator.hasNext()){
            holdAddedSem.add(iterator.next().getSemester());
        }
        return holdAddedSem;
    }

    public boolean checkDuplicateForCourses(Course inputCourse){
        if (coursesArrayList.isEmpty()) {
            return true;
        } else {
            for (Course course: coursesArrayList){
                if (course.getId().equals(inputCourse.getId())) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkDuplicateForStudent(Student inputStudent){
        if (studentArrayList == null) {
            return true;
        } else {
            for (Student student: studentArrayList){
                if (student.getId().equals(inputStudent.getId())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkDuplicateForEnrolment(StudentEnrolment inputStudentEnrolment){
        if (studentEnrolmentList != null) {
            for (StudentEnrolment studentEnrolment: studentEnrolmentList) {
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
    public void addEnrolment() throws FileNotFoundException {
        //check if enrolment is null
        Scanner scanner = new Scanner(System.in);
        if (studentEnrolmentList.isEmpty()){
            readFileCsv();
        }
        displayAvailableCouAndStu(studentArrayList, coursesArrayList);

        //Create an enrollment
        StudentEnrolment studentEnrolment = new StudentEnrolment(
                inputStudentId(scanner,studentArrayList),
                inputCourseId(scanner,coursesArrayList),
                inputSemester(scanner));

        //Add enrolment into the List of enrolment.
        if (checkDuplicateForEnrolment(studentEnrolment) == true) {
            studentEnrolmentList.add(studentEnrolment);
            System.out.println("----------------Successfully add new enrolment----------------");
        }else {
            System.out.println("This enrolment already existed");
        }
    }

    public int getNumOfStudentEnrollmentList(){
        return studentEnrolmentList.size();
    }

    private void miniAddInUpdate(Scanner scanner, String id){

        //Create an student
        Student chosenStudentUpdate = null;
        for (Student student: studentArrayList) {
           if (student.getId().equals(id)) {
                chosenStudentUpdate = student;
            }
        }


        System.out.println("************List of course************");
        for (Course course: coursesArrayList){
            System.out.println(course.toString());
        }

        //Create an course
        String input = null;
        Course chosenCourse = null;
        boolean check = true;
        while (check == true){
            System.out.print("Enter Course ID: ");
            input = scanner.nextLine();
            for (Course course: coursesArrayList) {
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
            studentEnrolmentList.add(enrolmentForSpecificStudent);
            System.out.println("Successfully Add enrolment for student " + id);
        }else {
            System.out.println(id + " already enroll this course");
        }
    }

    @Override
    public void updateEnrolment() throws FileNotFoundException {
        //check if the enrolment is null
        if (studentEnrolmentList.isEmpty()){
            readFileCsv();
        }
        Scanner scanner = new Scanner(System.in);
        //list all
        for (StudentEnrolment studentEnrolment: studentEnrolmentList){
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

            name = scanner.nextLine();
            boolean miniCheck = true;
            int count = 1;
            for (StudentEnrolment studentEnrolment: studentEnrolmentList){
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

            String option = scanner.nextLine();
            if (option.equals("add")) {
                miniAddInUpdate(scanner,name);
                check2 = false;
                runNext = false;
            }else if (option.equals("delete")){
                //to print all course of that student
                int count = 1;
                System.out.println("************All course of " + name +"************");
                for (StudentEnrolment studentEnrolment: newStudentEnrolmentArrayList) {
                    System.out.println(count +". " + studentEnrolment.getCourse().toString());
                    count++;
                }
                check2 = false;
            }
        }

        while (runNext == true){
            try {
                System.out.print("Choose number of enrolment you want to delete: ");
                String inputNum = scanner.nextLine();
                int newInputNum = Integer.parseInt(inputNum);
                if (0 < newInputNum && newInputNum <= newStudentEnrolmentArrayList.size()) {
                    studentEnrolmentList.remove(newStudentEnrolmentArrayList.get(newInputNum - 1));

                    getAll();
                    break;
                }
            }catch (Exception e){
                System.out.println("Please enter the right input");
            }
        }
    }

    @Override
    public void deleteEnrolment() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //check if enrolment is null
        if (studentEnrolmentList.isEmpty()){
            readFileCsv();
        }
        //list all enrolments for Users to observe
        for (StudentEnrolment studentEnrolment: studentEnrolmentList){
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
            name = scanner.nextLine();
            boolean miniCheck = true;
            for (StudentEnrolment studentEnrolment: studentEnrolmentList){
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
        int count = 0;
        System.out.println("************All course of " + name +"************");
        for (StudentEnrolment studentEnrolment: newStudentEnrolmentArrayList) {
            count++;
            System.out.println(count +". " + studentEnrolment.getCourse().toString(count));

        }

        boolean runNext = true;
        while (runNext == true){
            try {
                System.out.println("Choose number of enrolment you want to delete: ");
                String inputNum = scanner.nextLine();
                int newInputNum = Integer.parseInt(inputNum);
                if (0 < newInputNum && newInputNum <= newStudentEnrolmentArrayList.size()) {
                    studentEnrolmentList.remove(newStudentEnrolmentArrayList.get(newInputNum - 1));

                    getAll();
                    break;
                }
            }catch (Exception e){
                System.out.println("PLEASE ENTER THE RIGHT INPUT");
            }
        }

        for (StudentEnrolment s: studentEnrolmentList){
            JunitTesting.countDelete++;
        }

    }

    @Override
    public void getOne(String sid, String cid, String semester) throws FileNotFoundException {
        //checking if enrolment existed
        int count = 0;
        countForGetOne = 0;
        if (studentEnrolmentList.isEmpty()) {
            readFileCsv();
        }
        boolean print = true;
        for (StudentEnrolment studentEnrolment: studentEnrolmentList) {
            if (studentEnrolment.getStudent().getId().equals(sid)
                    && studentEnrolment.getCourse().getId().equals(cid)
                    && studentEnrolment.getSemester().equals(semester)) {
                count++;
                countForGetOne++;
                System.out.println(studentEnrolment.toString(count));
                print = true;
                break;
            }else {
                print = false;
            }
        }
        if (print == false) {
            System.out.println("Cannot show since the enrolment does not exist");
        }
    }

    @Override
    public void getAll() throws FileNotFoundException {
        //check if enrolment is null
        if (studentEnrolmentList.isEmpty()){
            readFileCsv();
        }
        int countInsideGetAll = 0;
        System.out.println("----------------All Enrolment----------------");
        Iterator<StudentEnrolment> iterator = studentEnrolmentList.iterator();
        while (iterator.hasNext()){
            JunitTesting.count++;
            countInsideGetAll++;
            System.out.println(iterator.next().toString(countInsideGetAll));
        }
    }

    //Print and save to csv function
    private static void writeToFile(String fileName, String line, boolean append) {
        PrintWriter output = null;
        try {
            output = new PrintWriter(new FileWriter(fileName, append));
            output.println(line);
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        finally {
            if (output != null)
                output.close();
        }
    }

    private void askSaveReport(String fileName, String record){
        System.out.print("Do you want to save the report into CSV file (Y/N) ");
        while(true) {
            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();
            if(answer.equals("Y")|| answer.equals("y")){
                writeToFile(fileName,record,false);
                break;
            }else if(answer.equals("N") || answer.equals("n")){
                break;
            }else{
                System.out.println("Wrong choice");
            }
        }
    }

    public void printAllCoursesFor1StudentFor1Sem() throws FileNotFoundException {
        //check if enrolment is null
        if (studentEnrolmentList.isEmpty()){
            readFileCsv();
        }
        String record="";
        for (String sem : readFileTakeSemList()){
            record+= "========"+sem+"==========\n";
            for(Student student : studentArrayList){
                boolean foundCourse = false;
                ArrayList<StudentEnrolment> allCourse= new ArrayList<>();
                for( StudentEnrolment studentEnrollment : studentEnrolmentList){
                    if(studentEnrollment.getSemester().equals(sem) && studentEnrollment.getStudent().getId().equals(student.getId())){
                        allCourse.add(studentEnrollment);
                        foundCourse = true;
                    }
                }
                if(foundCourse == true ){
                    record+="Student: "+student.getId()+" | "+student.getName()+"\n";
                    for (StudentEnrolment  studentEnrollment : allCourse){
                        record+="    "+studentEnrollment.getCourse().getId()+" "+studentEnrollment.getCourse().getName()+"\n";
                    }
                }
            }
        }
        System.out.println(record);
        askSaveReport("Report1.csv",record);
    }

    public void printAllStudentsFor1CourseFor1Sem() throws FileNotFoundException {
        //check if enrolment is null
        if (studentEnrolmentList.isEmpty()){
            readFileCsv();
        }
        String record ="";
        for (String sem : readFileTakeSemList()){
            record+="========"+sem+"==========\n";
            for(Course course: coursesArrayList){
                boolean foundStudent = false;
                ArrayList<StudentEnrolment> allStudent= new ArrayList<>();
                for( StudentEnrolment studentEnrollment : studentEnrolmentList){
                    if(studentEnrollment.getSemester().equals(sem) && studentEnrollment.getCourse().getId().equals(course.getId())){
                        allStudent.add(studentEnrollment);
                        foundStudent = true;
                    }
                }
                if(foundStudent == true ){
                    record+="Course: "+course.getId()+" | "+course.getName()+"\n";
                    for (StudentEnrolment  studentEnrollment : allStudent){
                        record+="    "+studentEnrollment.getStudent().getId()+" "+studentEnrollment.getStudent().getName()+"\n";
                    }
                }
            }
        }
        System.out.println(record);
        askSaveReport("Report2.csv",record);

    }

    public void printAllCourseOfferedInSemester() throws FileNotFoundException {
        //check if enrolment is null
        if (studentEnrolmentList.isEmpty()){
            readFileCsv();
        }

        String record="";
        for(String sem: readFileTakeSemList()){
            record+="========"+sem+"==========\n";
            for(Course course: coursesArrayList){
                boolean check = false;
                for(StudentEnrolment studentEnrollment : studentEnrolmentList){
                    if(studentEnrollment.getSemester().equals(sem) && studentEnrollment.getCourse().getId().equals(course.getId())){
                        check = true;
                    }
                }
                if(check) {
                    record+=course.getId()+" "+course.getName()+"\n";
                }
            }
        }
        System.out.println(record);
        askSaveReport("Report3.csv",record);
    }
}
