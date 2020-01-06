

打开Android SDK => SDK Tool 查看是否安装了CMake，没有就安装
新建项目，向下拉，新建C++项目

## 资料
查看error: https://stackoverflow.com/questions/55476461/error-computing-cmake-server-result-in-android-studio
Android JNI学习：https://www.jianshu.com/p/b4431ac22ec2
cmake中文手册：https://www.zybuluo.com/khan-lau/note/254724
https://blog.csdn.net/qq_23062979/article/details/81294552

## NDK

CMake Error at CMakeLists.txt:61 (target_link_libraries): Cannot specify link libraries for target "curl" which is not built by this project


missing and no known rule to make it
gradle中的ndk要和cpp库中支持平台的库的类型要一致
```
        ndk{
            abiFilters "armeabi-v7a"
        }
```