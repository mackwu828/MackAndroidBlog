package com.mackwu.base.wifi;

/**
 * @author MackWu
 * @since 2023/5/15 19:06
 */
public interface WifiConnector {

    /**
     * 连接
     * @param password 密码
     */
    void connect(String password);

    /**
     *
     */
    void connectNoPassword();
}
