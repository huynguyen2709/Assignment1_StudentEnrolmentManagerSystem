package com.company;

public class StudentEnrolment {
    private Student student;
    private Course course;
    private String semester;

    public StudentEnrolment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    public String toString(int count) {
        return "Student Enrolment " + count + " {" +
                "\n\tStudent ID = " + student.getId() +
                "\n\tStudent Name= " + student.getName() +
                "\n\tCourse ID=" + course.getId() +
                "\n\tCourse Name=" + course.getName() +
                "\n\tSemester Enrol='" + semester + '\'' +
                "\n}";
    }
}
