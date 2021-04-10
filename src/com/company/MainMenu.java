package com.company;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainMenu {
    private static StudentEnrolmentManagerImp imp = new StudentEnrolmentManagerImp();
    public static void impReaFile() throws FileNotFoundException {
        imp.readFileCsv();
    }
    public static void mainMenu() throws FileNotFoundException {
//        imp.readFileCsv();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("************************Student Enrolment Manager System************************");
            System.out.println("1. CRUD.");
            System.out.println("2. Print.");
            System.out.println("3. Exit.");
            System.out.print("Your command: ");
            String menuChoice = scanner.nextLine();
            if (menuChoice.equals("1")) {
                subMenu.subMenu();
                break;
            }else if (menuChoice.equals("2")){
                subMenu.menuPrint();
                break;
            }else if (menuChoice.equals("3")){
                break;
            }
        }
    }

    public static class subMenu {
        public static void subMenu() throws FileNotFoundException {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("************************CRUD Enrolment************************");
                System.out.println("1. Add Enrolment");
                System.out.println("2. Update Enrolment");
                System.out.println("3. Delete Enrolment");
                System.out.println("4. Get One");
                System.out.println("5. Get All");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                String menuChoice = scanner.nextLine();
                if (menuChoice.equals("1")) {
                    imp.addEnrolment(StudentEnrolmentManager.studentArrayList, StudentEnrolmentManager.coursesArrayList);
                    break;
                }else if (menuChoice.equals("2")){
                    imp.updateEnrolment();
                    break;
                }else if (menuChoice.equals("3")){
                    imp.deleteEnrolment();
                    break;
                }else if (menuChoice.equals("4")){
                    System.out.println("SO 4 NE");
                    break;
                }else if (menuChoice.equals("5")){
                    imp.getAll();
                    break;
                }else if (menuChoice.equals("6")){
                    MainMenu.mainMenu();
                    return;
                }
            }

            while (true) {
                Scanner scanner1 = new Scanner(System.in);
                System.out.print("Do you want to continue(yes/no): ");
                String yesOrNo = scanner1.nextLine();
                if (yesOrNo.equals("yes")) {
                    subMenu();
                }else if (yesOrNo.equals("no")) {
                    MainMenu.mainMenu();
                    break;
                }
            }
        }

        public static void menuPrint() throws FileNotFoundException {
            while (true) {
                System.out.println("************************Menu Print************************");
                System.out.println("1. Print All courses offered in Semester");
                //printAllCoursesFor1StudentFor1Sem
                System.out.println("2. Print All courses for 1 Student for 1 Semester");
                //printAllStudentsFor1CourseFor1Sem
                System.out.println("3. Print all Students for 1 Course for 1 Semester");
                System.out.println("4. Exit Menu Print");
                Scanner scanner = new Scanner(System.in);
                String userOption = scanner.nextLine();
                if (userOption.equals("1")) {
                    imp.printAllCourseOfferedInSemester();
                    break;
                } else if (userOption.equals("2")){
                    imp.printAllCoursesFor1StudentFor1Sem();
                    break;
                } else if (userOption.equals("3")){
                    imp.printAllStudentsFor1CourseFor1Sem();
                    break;
                } else if (userOption.equals("4")){
                    MainMenu.mainMenu();
                    break;
                }
            }
        }

    }


}