package com.mackwu.component.jbase.bean;

import java.io.Serializable;

/**
 * @author MackWu
 * @since 2022/8/3 16:03
 */
public class Person implements Serializable {

    private String name;
    private int age;

    public Person(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
