package com.mackwu.component.jbase;

import com.mackwu.component.jbase.bean.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author MackWu
 * @since 2022/8/3 16:04
 */
public class ReflectTest {

    public static void main(String[] args) {
        clazz();
    }

    private static void clazz(){
        /*
         * 使用反射获取所有的内部类
         */
        try {
            Class<Person> personClass = Person.class;
            Person person = personClass.getDeclaredConstructor().newInstance();
            Class<?>[] declaredClasses = personClass.getDeclaredClasses();
            for (Class<?> declaredClass : declaredClasses) {
                Constructor<?> declaredConstructor = declaredClass.getDeclaredConstructor(personClass);
                declaredConstructor.setAccessible(true);
                Object o = declaredConstructor.newInstance(person);
                System.out.println(o);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void method(){
        /*
         * 使用反射获取所有的方法
         */
        Class<Person> personClass = Person.class;
        // 获取所有的方法，不包括父类中继承的方法
        Method[] declaredMethods = personClass.getDeclaredMethods();
        // 获取所有public的方法，包括从父类中继承的方法。
        Method[] methods = personClass.getMethods();

        /*
         * 使用反射调用私有方法
         */
        Person person = new Person("mack", 18);
        try {
            Method testReflect = personClass.getDeclaredMethod("testReflect");
            testReflect.setAccessible(true);
            testReflect.invoke(person);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void filed(){
        /*
         * 使用反射获取成员变量
         */
        Class<Person> personClass = Person.class;
        // 获取所有的成员变量。不包括父类的成员变量。
        Field[] declaredFields = personClass.getDeclaredFields();
        // 获取所有public的成员变量。不包括父类的成员变量。
        Field[] fields = personClass.getFields();


        /*
         * 使用反射获取指定的成员变量
         */
        // 获取指定的成员变量
        try {
            Field name = personClass.getDeclaredField("name");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        // 获取指定的public的成员变量。不能获取私有的，否则会抛出异常。
        try {
            Field name = personClass.getField("name");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        /*
         * 使用反射修改成员变量的值
         */
        // 如果成员变量是私有的，需要调用setAccessible(true)，否则会抛出异常。
        Person person = new Person("mack", 18);
        try {
            Field name = personClass.getDeclaredField("name");
            name.setAccessible(true);
            name.set(person, "jack");
            System.out.println(person); // Person{name='jack', age=18, sex='null'}
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private static void constructor(){
        /*
         * 使用反射获取所有的构造方法
         */
        Class<Person> personClass = Person.class;
        // 获取所有的构造方法，不包括父类的构造方法。
        Constructor<?>[] declaredConstructors = personClass.getDeclaredConstructors();
        // 获取所有public的构造方法，不包括父类的构造方法。
        Constructor<?>[] constructors = personClass.getConstructors();

        /*
         * 使用反射获取指定的构造方法
         */
        // 获取指定的构造方法。传入构造方法中的参数类型。
        try {
            Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor(String.class, int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        // 获取指定的构造方法，只能获取public的构造方法。传入构造方法中的参数类型。
        try {
            Constructor<Person> constructor = personClass.getConstructor(String.class, int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        /*
         * 使用反射实例化对象
         */
        // 通过Class的newInstance实例化对象。只能实例化无参的构造方法，且不能是私有的否则会抛出异常。不推荐使用。
        try {
            Person person = personClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        // 使用Constructor实例化对象。如果实例化的私有构造方法，需要调用setAccessible(true), 否则则会抛出IllegalAccessException异常。
        try {
            Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor(String.class, int.class);
            Person person = declaredConstructor.newInstance("mack", 18);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void a(){
        // 获取Class对象：
        // 通过类获取
        Class<Person> personClass = Person.class;
//        // 通过对象获取
//        Person person = new Person();
//        Class<? extends Person> personClass = person.getClass();
//        // 通过Class.forName("包名.类名")
//        try {
//            Class<Person> personClass = (Class<Person>) Class.forName("com.mackwu.component.jbase.bean.Person");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
