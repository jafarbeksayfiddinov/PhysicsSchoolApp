package com.example.physicsschoolapp.datas;

import com.example.physicsschoolapp.datas.enums.ROLE_ENUM;

import java.util.UUID;

public class User {
    private String id = String.valueOf(UUID.randomUUID());
    private String name;
    private String surname;
    private String email;
    private String password;
    private ROLE_ENUM role;


    public User() {
    }

    public User(UUID id, String name, String surname, String email, String password, ROLE_ENUM role) {
        this.id = String.valueOf(id);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String name, String surname, String email, String password, ROLE_ENUM role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ROLE_ENUM getRole() {
        return role;
    }



}
