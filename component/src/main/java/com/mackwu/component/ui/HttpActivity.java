package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityHttpBinding;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author MackWu
 * @since 2023/5/29 11:13
 */
public class HttpActivity extends BaseActivity<BaseViewModel, ActivityHttpBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        new Thread(() -> {
            DatagramSocket socket = null;
            try {
                socket= new DatagramSocket();

            } catch (SocketException e) {
                throw new RuntimeException(e);
            }finally {
                if (socket != null) {
                    socket.close();
                }
            }
        }).start();
    }

    private void a() {
        try {
            InetAddress[] inetAddresses = InetAddress.getAllByName("www.baidu.com");
            for (InetAddress inetAddress : inetAddresses) {
                // 域名
                String hostName = inetAddress.getHostName();
                // ip地址
                String hostAddress = inetAddress.getHostAddress();
//                   hostName=www.baidu.com, hostAddress=14.119.104.189
//                   hostName=www.baidu.com, hostAddress=14.119.104.254
                Logger.d("hostName=" + hostName + ", hostAddress=" + hostAddress
                );
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

}
