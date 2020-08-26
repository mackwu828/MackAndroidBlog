package com.mackwu.java;

import com.mackwu.java.bean.Person;

public class Generic extends BaseGeneric<Person> {

    public static void main(String[] args) {
        Generic generic = new Generic();
        System.out.println(generic.getTCls());
    }
}
