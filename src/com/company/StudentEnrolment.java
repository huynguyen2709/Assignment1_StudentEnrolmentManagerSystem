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
        return "StudentEnrolment " + count + " {" +
                "student=" + student.getName() +
                ", course=" + course.getName() +
                ", semester='" + semester + '\'' +
                '}';
    }
}
