
Java本地接口，通过JNI可以实现Java代码与C/C++代码的交互。JNI是Java调用C++的规范。

Java调用C++
C++调用Java

## 资料
[隔壁李老头JNI系列](https://www.jianshu.com/p/87ce6f565d37)

## JNI实现步骤
### 在Java中声明一个native方法
```
public class JniTest {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public static native String stringFromJNI();
}
```
## 实现在Java中声明的Native方法
```
extern "C"
JNIEXPORT jstring JNICALL
Java_com_mackwu_jnidemo_JniTest_stringFromJNI(JNIEnv *env, jclass clazz) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
```

- `JNIEXPORT` 和 `JNICALL` 是关键字不要修改
- `jstring` 返回类型
- `Java_com_mackwu_jnidemo` 包名
- `JniTest` 类名
- `stringFromJNI` 方法名


## 数据类型
```
jint 整型
jstring 字符串
jobject 对象引用
JNIEnv 接口指针
```

## JNIEnv
JNIEnv是一个线程相关的结构体，该结构体代表了Java在本线程的执行环境。
### JNIEnv的作用
- JNIEnv代表了Java执行环境，能够使用JNIEnv调用Java中的代码。
- Java对象传入JNI层就是jobject对象，需要使用JNIEnv来操作这个Java对象。

