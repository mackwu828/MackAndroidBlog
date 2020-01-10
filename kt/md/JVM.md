

## .java文件编译过程

.java文件 
=> 词法分析。生成符号表 
=> 语法分析 
=> 语义分析。类型检查，控制流检查，类型检查等等
=> 中间代码生成，优化以及生产对应的目标文件
=> .class文件


## 通过javac将.java源文件编译成.class字节码文件
```
javac xxx.java
```

## 运行
```
java JavaTest
```

错误1: 找不到或无法加载主类 JavaTest.class。.java文件不要加包名

## 查看
```
javap -verbose JavaTest.class
```