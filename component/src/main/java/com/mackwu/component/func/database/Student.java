package com.mackwu.component.func.database;

import com.mackwu.component.util.DateUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * ===================================================
 * Created by MackWu on 2021/12/24 14:58
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
@Entity
public class Student {

    @Id(autoincrement = true)
    private Long id;
    // 学号
    @Unique
    private long studentId;
    private String name;
    private int age;
    private long timeStamp;
    private boolean visible;

    @Generated(hash = 1556870573)
    public Student() {
    }

    public Student(long studentId, String name, int age, long timeStamp) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.timeStamp = timeStamp;
    }

    public Student(long studentId, String name, int age, long timeStamp, boolean visible) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.timeStamp = timeStamp;
        this.visible = visible;
    }

    @Generated(hash = 1622072370)
    public Student(Long id, long studentId, String name, int age, long timeStamp,
            boolean visible) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.timeStamp = timeStamp;
        this.visible = visible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean getVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", timeStamp=" + DateUtil.stampToDateStr(timeStamp, "yyyy-MM-dd HH:mm:ss") +
                ", visible=" + visible +
                '}';
    }


}
