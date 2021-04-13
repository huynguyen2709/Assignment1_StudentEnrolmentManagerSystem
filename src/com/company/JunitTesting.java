package com.company;

import org.junit.Test;
import static org.junit.Assert.*;


import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class JunitTesting {
    public  StudentEnrolmentManagerImp objectTesting= new StudentEnrolmentManagerImp();
    public static int count = 0;
    public static int countDelete = 0;

    @Test
    public void testUpdateChooseDelete() throws FileNotFoundException {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        String simulatedUserInput = "S101312" + System.getProperty("line.separator") + "delete"+ System.getProperty("line.separator") + "1";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        objectTesting.updateEnrolment();
        assertEquals("Checking size of List after deleting", 14, objectTesting.getNumOfStudentEnrollmentList());
        System.setIn(sysInBackup);
    }

    @Test
    public void testUpdateChooseAdd() throws FileNotFoundException {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        String simulatedUserInput = "S101312" + System.getProperty("line.separator") + "add"+ System.getProperty("line.separator") + "BUS2232"+ System.getProperty("line.separator") + "2021A";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        objectTesting.updateEnrolment();
        assertEquals("Checking size of List after adding", 16, objectTesting.getNumOfStudentEnrollmentList());
        System.setIn(sysInBackup);
    }

    @Test
    public void addEnrolment() throws FileNotFoundException{
        int numberAfterImpAdd = 16;
        InputStream sysInBackup = System.in;
        String input = "S102732" + "\n"+ "COSC4030" + "\n" + "2090A";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        objectTesting.addEnrolment();
        assertEquals(numberAfterImpAdd,objectTesting.getNumOfStudentEnrollmentList());
        System.setIn(sysInBackup);
    }

    @Test
    public void deleteEnrolment() throws FileNotFoundException {
        InputStream sysInBackup = System.in;
        String input = "S101312" + "\n" + "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        objectTesting.deleteEnrolment();
        assertEquals(14, countDelete);
        System.setIn(sysInBackup);
    }

    @Test
    public void getAll() throws FileNotFoundException {
        int numberPrintedEnrol = 15;
        objectTesting.getAll();
        assertEquals(numberPrintedEnrol, objectTesting.getNumOfStudentEnrollmentList());
    }

    @Test
    public void checkDuplicateForCourses() {
        Course course1 = new Course("COSC4030", "Theory of Computation", 5);
        Course course2 = new Course("BUS2232", "Business Law", 3);
        Course course1Duplicate = new Course("COSC4030", "Theory of Computation", 5);
        objectTesting.coursesArrayList.add(course1);
        objectTesting.coursesArrayList.add(course2);
        System.out.println("Test checkDuplicateForCourses()");
        assertEquals(false, objectTesting.checkDuplicateForCourses(course1Duplicate));
    }

    @Test
    public void checkDuplicateForStudent() {
        Student student1 = new Student("S103817", "Thuy Thu Nguyen", "3/4/2000");
        Student student2 = new Student("S101163", "Joseph Fergile", "10/29/1998");
        Student student1Duplicate = new Student("S103817", "Thuy Thu Nguyen", "3/4/2000");
        objectTesting.studentArrayList.add(student1);
        System.out.println("Testing checkDuplicateForStudent()");
        //check if duplicate
        assertEquals(false, objectTesting.checkDuplicateForStudent(student1Duplicate));
        //check if no duplicate
    }

    @Test
    public void getOne() throws FileNotFoundException {
        objectTesting.getOne("S101153", "COSC3321", "2021A");
        int numberOfEnrolment = 1;
        assertEquals(numberOfEnrolment, StudentEnrolmentManagerImp.countForGetOne);
    }

    Student student = new Student("S101312", "Alex Mike", "10/13/1998");
    Course course = new Course("COSC4030", "Theory of Computation", 5);

    @Test
    public void	testGetStudentID(){
        assertEquals("S101312",student.getId());
    }

    @Test
    public void	testSetStudentID(){
        student.setId("S102733");
        assertEquals("S102733",student.getId());
    }

    @Test
    public void	testGetName(){
        assertEquals("Alex Mike",student.getName());
    }

    @Test
    public void	testSetName(){
        student.setName("Quang Teo");
        assertEquals("Quang Teo",student.getName());
    }

    @Test
    public void	testGetStudentDob(){
        assertEquals("10/13/1998",student.getDob());
    }

    @Test
    public void	testSetsDOB(){
        student.setDob("02/01/2001");
        assertEquals("02/01/2001",student.getDob());
    }

    /**
     * Test class COURSE
     */
    // test getter setter of cID
    @Test
    public void	testGetCourseId(){
        assertEquals("COSC4030",course.getId());
    }

    @Test
    public void	testSetCourseId(){
        course.setId("AAA10101");
        assertEquals("AAA10101",course.getId());
    }

    // test getter, setter of cName
    @Test
    public void	testGetcName(){
        assertEquals("Theory of Computation",course.getName());
    }

    @Test
    public void	testSetcName(){
        course.setName("Intro to IT");
        assertEquals("Intro to IT",course.getName());
    }

    // test getter, setter of numOfCredits
    @Test
    public void	testGetNumOfCredits(){
        assertEquals(5,course.getNumberOfCredits());
    }

    @Test
    public void	testSetNumOfCredits(){
        course.setNumberOfCredits(5);
        assertEquals(5,course.getNumberOfCredits());
    }

    @Test
    public void testToStringCourse() {
        assertEquals("Course{id='COSC4030', name='Theory of Computation', numberOfCredits=5}", course.toString());
    }


    /**
     * Test Student Enrollment
     */
    private static Student objStudent = new Student("S102732", "Mark Duong", "8/28/2001");
    private static Course objCourse = new Course("COSC4030", "Theory of Computation", 12);
    private static StudentEnrolment enrolment = new StudentEnrolment(objStudent,objCourse,"2020C");

    // test getter and setter of students property
    @Test
    public void	testGetStudentInEnrolment(){
        assertEquals(objStudent.toString(),enrolment.getStudent().toString());
    }

    @Test
    public void	testSetCourseInEnrolment(){
        Course course = new Course("PHYS1230", "Introductory Human Physiology", 4);
        StudentEnrolment se = new StudentEnrolment();
        se.setCourse(course);
        assertEquals(course.toString(),se.getCourse().toString());
    }

    @Test
    public void	testGetCourseInEnrolment(){
        assertEquals(objCourse.toString(), enrolment.getCourse().toString());
    }

    @Test
    public void	testSetStudentInEnrolment(){
        Student student2 = new Student("S102732", "Mark Duong", "8/28/2001");
        StudentEnrolment se = new StudentEnrolment();
        se.setStudent(student2);
        assertEquals(student2.toString(),se.getStudent().toString());
    }

    @Test
    public void	testGetSemInEnrolment(){
        assertEquals("2020C", enrolment.getSemester());
    }

    @Test
    public void testPrintAllCourseInOneSem() throws FileNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream("Y".getBytes());
        System.setIn(in);
        objectTesting.printAllCoursesFor1StudentFor1Sem();
    }
    @Test
    public void testPrintAllStudentForOneCourseInASem() throws FileNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream("Y".getBytes());
        System.setIn(in);
        objectTesting.printAllStudentsFor1CourseFor1Sem();
    }

    @Test
    public void testPrintAllOfferedCourse() throws FileNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream("Y".getBytes());
        System.setIn(in);
        objectTesting.printAllCourseOfferedInSemester();
    }
}