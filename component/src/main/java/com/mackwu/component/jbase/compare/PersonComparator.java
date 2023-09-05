package com.mackwu.component.jbase.compare;

import com.mackwu.component.jbase.bean.Person;

import java.util.Comparator;

/**
 * @author MackWu
 * @since 2023/6/16 14:51
 */
public class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getAge() - o2.getAge();
    }

}
