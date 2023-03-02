package com.mackwu.component.func.mqtt;

import android.content.Context;
import android.text.TextUtils;

/**
 * ===================================================
 * Created by MackWu on 2022/4/1 11:11
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MqttClient {

    public static class Builder {

        Context context;
        // 协议
        String protocol;
        // 地址
        String host;
        // 端口号
        String port;
        // uri
        String serverURI;
        // 用户id
        String clientId;
        // 证书
        String caCert;
        // 私钥
        String privateKey;

        public Builder(Context context) {
            this.context = context;
            this.protocol = "ssl";
            this.host = "appfy7lzpaxap-ats.iot.us-east-1.amazonaws.com";
            this.port = "8883";
        }

        public MqttCall build() {
            return new MqttRealCall(this);
        }

        String getServerURI() {
            if (TextUtils.isEmpty(serverURI)) {
                serverURI = protocol + "://" + host + ":" + port;
            }
            return serverURI;
        }
    }

}
