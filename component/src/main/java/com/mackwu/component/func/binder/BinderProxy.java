package com.mackwu.component.func.binder;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.mackwu.base.util.Logger;

/**
 * @author MackWu
 * @since 2023/1/17 16:02
 */
public class BinderProxy implements IGradeInterface{

    IBinder binder;

    public BinderProxy(IBinder binder) {
        this.binder = binder;
    }

    @Override
    public int getStudentGrade(String name) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        int grade = 0;
        data.writeString(name);
        try {
            if (binder == null) {
                throw new IllegalStateException("Need Bind Remote Server...");
            }
            binder.transact(0x01, data, reply, 0);
            grade = reply.readInt();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return grade;
    }

    public static IGradeInterface asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        if (iBinder instanceof IGradeInterface) {
            Logger.d("当前进程");
            // 如果是同一个进程的请求，则直接返回Binder
            return (IGradeInterface) iBinder;
        } else {
            Logger.d("远程进程");
            // 如果是跨进程查询则返回Binder的代理对象
            return new BinderProxy(iBinder);
        }
    }

}
