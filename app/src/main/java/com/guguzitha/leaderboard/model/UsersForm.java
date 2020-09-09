package com.guguzitha.leaderboard.model;

import android.provider.ContactsContract;

import java.util.jar.Attributes;

public class UsersForm {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String projectLink;

    public UsersForm(String firstName, String lastName, String emailAddress, String projectLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.projectLink = projectLink;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }
}