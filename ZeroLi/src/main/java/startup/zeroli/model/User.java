/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package startup.zeroli.model;

import java.time.LocalDate;
import startup.zeroli.common.Gender;
import startup.zeroli.common.Status;

/**
 *
 * @author Admin
 */
public class User {

    private int userId;
    private String fullName;
    private String biography;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private String role;
    private String userName;
    private String password;
    private Status status;

    public User() {
    }

    public User(int userId, String fullName, String biography, LocalDate dateOfBirth, String email, String phoneNumber, Gender gender, String role, String userName, String password, Status status) {
        this.userId = userId;
        this.fullName = fullName;
        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.role = role;
        this.userName = userName;
        this.password = password;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", fullName=" + fullName + ", biography=" + biography + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", phoneNumber=" + phoneNumber + ", gender=" + gender + ", role=" + role + ", userName=" + userName + ", password=" + password + ", status=" + status + '}';
    }
    
}
