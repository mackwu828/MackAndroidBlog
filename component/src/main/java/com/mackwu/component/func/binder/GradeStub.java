package com.mackwu.component.func.binder;

import android.os.RemoteException;

import java.util.HashMap;

/**
 * @author MackWu
 * @since 2023/1/17 18:20
 */
public class GradeStub extends IGradeService.Stub{

    private static final HashMap<String, Integer> hashMap = new HashMap<>();

    static {
        hashMap.put("Anna", 98);
        hashMap.put("Jack", 95);
    }

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public int getStudentGrade(String name) throws RemoteException {
        return hashMap.get(name);
    }

}
