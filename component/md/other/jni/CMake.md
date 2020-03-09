


###
```
CMake Error at CMakeLists.txt:61 (target_link_libraries): Cannot specify link libraries for target "curl" which is not built by this project
missing and no known rule to make it
```
gradle中的ndk要和cpp库中支持平台的库的类型要一致
```
        ndk{
            abiFilters "armeabi-v7a"
        }
```
