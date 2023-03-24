package org.example.models;

public class ModelUsers {
    private int id;
    private String username;
    private String contact;
    private String email;

    public ModelUsers(int id, String username, String contact, String email) {
        this.id = id;
        this.username = username;
        this.contact = contact;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
