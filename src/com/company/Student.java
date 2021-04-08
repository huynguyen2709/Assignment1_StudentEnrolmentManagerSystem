package com.company;

public class Student{
    private String id;
    private String name;
    private String dob;

    public Student(String id, String name, String dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }


    public String toString(int count) {
        return "Student " + count + " {" +
                "ID = '" + id + '\'' +
                ", name = '" + name + '\'' +
                ", date of birth = '" + dob + '\'' +
                '}';
    }
}
