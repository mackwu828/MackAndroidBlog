package com.mackwu.component.func.mqtt;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

/**
 * ===================================================
 * Created by MackWu on 2022/4/1 14:51
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MqttRealCall implements MqttCall {

    private MqttAndroidClient client;
    private MqttConnectOptions options;

    public MqttRealCall(MqttClient.Builder builder) {
        client = new MqttAndroidClient(builder.context, builder.getServerURI(), builder.clientId);
        options = new MqttConnectOptions();
        options.setAutomaticReconnect(true); // 是否自动重连
        options.setCleanSession(false);
        options.setKeepAliveInterval(60);
        options.setSocketFactory(TLS.getSSLSocketFactory(builder.caCert, builder.privateKey));
    }

    @Override
    public void connect() {

    }
}
