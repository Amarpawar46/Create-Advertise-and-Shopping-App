package com.example.pragatienterprise;


public class User {


public  String name;
    public String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User(String name, String content) {
        this.name = name;
        this.content = content;
    }

    User() {
    }



   }