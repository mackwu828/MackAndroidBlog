
## 资料
[Android JNI(一)——NDK与JNI基础](https://www.jianshu.com/p/87ce6f565d37)
[jni新手笔记](https://www.jianshu.com/p/8714b5c18508)


## JNI
JNI(Java Native Interface): Java本地接口，JNI就是Java调用C++的规范，通过JNI可以实现Java代码与C/C++代码的交互


## 在Java中声明native方法
```

```

## 
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

## 创建Java中的基本数据类型

## 创建Java中的String对象
```

```