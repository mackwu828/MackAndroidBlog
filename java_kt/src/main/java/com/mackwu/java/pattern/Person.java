package com.mackwu.java.pattern;

/**
 * ===================================================
 * Created by MackWu on 2020/3/23 14:59
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class Person {
    private String name;
    private int age;

    // 3. 添加构造函数，将builder对象的属性赋值给person对象
    public Person(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    // 1. 创建内部类builder, 添加相同属性同上
    public static final class Builder {
        private String name;
        private int age;

        // 2. 为每个属性添加对应的方法
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        // 4. 将赋值后的builder对象传入，返回person的实例
        public Person build() {
            return new Person(this);
        }
    }

    public static void main(String[] args) {
        Person person = new Person.Builder()
                .name("mack")
                .age(12)
                .build();
    }
}
