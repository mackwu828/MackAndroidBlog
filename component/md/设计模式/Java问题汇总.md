

什么是Java字节码？
Java类加载过程？https://zhuanlan.zhihu.com/p/73078336
类加载器？

class文件的加载。

## 什么是Java字节码？
.java源码 => 编译器 => .class字节码 => JVM

.java文件转.class文件
```
javac Test.java
```

.class文件转十六进制文件
```
xxd Test.class
00000000: cafe babe 0000 0034 0022 0700 0201 0019  .......4."......
00000010: 636f 6d2f 636d 6f77 6572 2f6a 6176 615f  com/cmower/java_
00000020: 6465 6d6f 2f54 6573 7407 0004 0100 106a  demo/Test......j
00000030: 6176 612f 6c61 6e67 2f4f 626a 6563 7401  ava/lang/Object.
00000040: 0006 3c69 6e69 743e 0100 0328 2956 0100  ..<init>...()V..
00000050: 0443 6f64 650a 0003 0009 0c00 0500 0601  .Code...........
00000060: 000f 4c69 6e65 4e75 6d62 6572 5461 626c  ..LineNumberTabl
```

cafe babe被称为魔数。Java字节码以cafe babe魔数开头的十六进制数。


## Java类加载过程？
载入 => 验证 => 准备 => 校验 => 初始化