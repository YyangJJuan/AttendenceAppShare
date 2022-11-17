package com.example.application2;

public class User {
    private  String username;
    private  String password;

    public User()
    {

    }
    public User(String name,String password)
    {
        this.username = name;
        this.password = password;
    }

    public void setUsername(String name)
    {
        this.username = name;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
    public String getUsername()
    {
        return this.username;
    }
    public String getPassword()
    {
        return  this.password;
    }
}
