package com.bawp.charismaticprimaryschool.model;

public class Login {
     private int studentId;
     private String firstName;
     private String surname;
     private String emailAddress;
     private String username;
     private String password;

    public Login(int studentId, String firstName, String surname, String emailAddress, String username, String password) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
