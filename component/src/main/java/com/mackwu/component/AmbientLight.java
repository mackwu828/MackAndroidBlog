package com.mackwu.component;

/**
 * @author MackWu
 * @since 2023/9/18 14:59
 */
public class AmbientLight {

    private static AmbientLight instance;

    private AmbientLight() {
        AmbientLight.getInstance().turnOff();
        AmbientLight.getInstance().setColor("#14CAFF");
        AmbientLight.getInstance().setLightSensitive();
        AmbientLight.getInstance().autoPlay();
    }

    public static AmbientLight getInstance() {
        if (instance == null) {
            instance = new AmbientLight();
        }
        return instance;
    }

    public void turnOn() {

    }

    public void turnOff() {

    }

    public void setColor(String color) {

    }

    public void setLightSensitive() {
    }

    public void autoPlay() {
    }
}
