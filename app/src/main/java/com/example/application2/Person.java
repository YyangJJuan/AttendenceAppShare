package com.example.application2;



//群成员
public class Person {
    private String name;
    private String age;
    private String major;//群成员的专业

    //构造函数
    public Person(String name ,String age,String major)
    {
        this.age = age;
        this.name = name;
        this.major = major;
    }

    public String getName() {return this.name;}
    public String getAge(){return  this.age;}
    public String getMajor(){return this.major;}

    public void setName(String name) {this.name = name;}
    public void setAge(String age){this.age = age;}
    public void setMajor(String major){this.major = major;}

}
