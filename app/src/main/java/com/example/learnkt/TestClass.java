package com.example.learnkt;

public class TestClass {

    String name;
    int age;

    public TestClass(String name) {
        this.name = name;
    }

    public TestClass(){
        this("xxx");
    }


    public TestClass(String name, int age) {
       this(name);
    }
}
