package com.mackwu.component.func.binder;

import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mackwu.base.util.Logger;

import java.util.HashMap;

/**
 * @author MackWu
 * @since 2023/1/17 15:57
 */
public class GradeBinder extends Binder implements IGradeInterface {

    private static final HashMap<String, Integer> hashMap = new HashMap<>();

    static {
        hashMap.put("Anna", 98);
        hashMap.put("Jack", 95);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public int getStudentGrade(String name) {
        return hashMap.get(name);
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        Logger.d("onTransact...  code=" + code);
        if (code == 0x01) {
            String name = data.readString();
            @SuppressWarnings("ConstantConditions")
            int grade = hashMap.get(name);
            if (reply != null) {
                reply.writeInt(grade);
            }
            Logger.d("onTransact...  name=" + name + ", grade=" + grade);
            return true;
        }
        return super.onTransact(code, data, reply, flags);
    }

}
