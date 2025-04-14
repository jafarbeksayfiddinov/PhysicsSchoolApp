package com.example.physicsschoolapp.singleton;

import com.example.physicsschoolapp.datas.User;

public class CurrentUser {
    private static User user;


    private CurrentUser(){}
    public static void setUser(User u){
        user=u;
    }
    public static User getUser(){
        return user;
    }
    private static void clear(){
        user=null;
    }
}
