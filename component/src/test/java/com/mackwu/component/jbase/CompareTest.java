package com.mackwu.component.jbase;

import com.mackwu.component.jbase.bean.Person;
import com.mackwu.component.jbase.compare.PersonComparator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author MackWu
 * @since 2023/6/16 14:57
 */
public class CompareTest {

    @Test
    public void a() {
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("John", 15),
                new Person("Sam", 25)
//                new Person("Will", 20),
//                new Person("Dan", 20),
//                new Person("Joe", 10)
        ));
        Collections.sort(persons, new PersonComparator());
        System.out.println(persons);
    }

}
