package com.zhym.po;

import java.io.Serializable;

/**
 * @description:
 * @author: zhym
 * @time: 2020/10/28 0028 1:37
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 8059834427160703139L;
    private String name;

    private int age;

    private String address;

    public Student(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
