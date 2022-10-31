package com.example.application2;


import android.app.Person;

import java.util.List;

//签到群
public class group {

    private String name;//群名
    private int number;//群号
    private List<Person> groupPerson;//群成员

    //构造函数
    public group(String name,int number)
    {
        this.name = name;
        this.number = number;
    }
    public group()
    {

    }
    public String getName()
    {
        //返回群名
        return this.name;
    }
    public int getNumber()
    {
        //返回群号
        return this.number;
    }
    //设置群名
    public void setName(String name)
    {
        this.name = name;
    }
    //设置群号
    public void setNumber(int number)
    {
        this.number = number;
    }

    //设置群成员
    public  void setGroupPerson(List<Person>groupPerson)
    {
        this.groupPerson = groupPerson;
    }
    //获取群成员
    public List<Person> getGroupPerson()
    {
        return this.groupPerson;
    }
}
