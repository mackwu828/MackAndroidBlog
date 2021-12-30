// IMyAidlInterface.aidl
package com.mackwu.component;

// Declare any non-default types here with import statements

interface IWebInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    void show();

    void hide();

    void loadUrl();
}