
ASM? Java字节码操作框架。可以生成字节码文件，可以动态修改字节码。 https://juejin.cn/post/6877925000045658119
ClassVisitor: 访问一个类
MethodVisitor: 访问一个方法
ClassReader？读取class文件
局部变量表和操作数栈



ASM GitHub example:
https://github.com/dengshiwei/asm-example
https://github.com/Gavin-ZYX/asmDemo

ASM ArrayIndexOutOfBoundsException？https://github.com/didi/booster/issues/20
ASM 在已有的try catch代码块中插入代码？https://stackoverflow.com/questions/36125031/how-to-modify-catch-block-code-using-asm-bytecode-framework


## ClassReader


## MethodVisitor
- visitTypeInsn: 插入对象
```
    // 插入对象，操作码是NEW，类型是StringWriter。
    // 反编译后对应Java代码: new StringWriter;
    mv.visitTypeInsn(NEW, "java/io/StringWriter"); 
```

- visitMethodInsn: 调用方法
```
    // 插入方法调用，调用StringWriter的构造方法。
    // 在visitTypeInsn后执行。反编译后对应Java代码: new StringWriter();
    mv.visitMethodInsn(INVOKESPECIAL, "java/io/StringWriter", "<init>", "()V", false); 
```

- visitVarInsn: 存储和加载变量
```
    Opcodes.ISTORE: 存储基本类型数据到i中。如boolean、int、byte、short、char
    Opcodes.ASTORE: 存储引用类型数据到i中。
    
    Opcodes.ILOAD: 从i中加载基本类型数据。
    Opcodes.ALOAD: 从i中加载引用类型数据。
    
    methodVisitor.visitVarInsn(Opcodes.ASTORE, 1);
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
```







- visitInsn: 访问无操作数
```
    Opcodes.ICONST_M1、Opcodes.ICONST_0 ~ Opcodes.ICONST_5: 对应int值：-1、0~5
    Opcodes.DUP: 复制栈里元素(对象地址)，再次压入
    
    methodVisitor.visitIntInsn(Opcodes.ICONST_0)
```

- visitIntInsn: 生成基本数据类型
```
    Opcodes.BIPUSH: -128~127。注：-1~5使用visitInsn访问
    Opcodes.SIPUSH: -32768~32767
    Opcodes.LDC: -2147483648~2147483647
    
    methodVisitor.visitIntInsn(Opcodes.BIPUSH, 12)
```





- visitCode: 开始访问方法
- visitTryCatchBlock: 访问 try..catch 


## MethodVisitor例子
- 插入一个对象
```
        // 反编译后对应Java代码: StringWriter var1 = new StringWriter();
        mv.visitTypeInsn(NEW, "java/io/StringWriter");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "java/io/StringWriter", "<init>", "()V", false);
        mv.visitVarInsn(Opcodes.ASTORE, 1);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
```


- 在方法执行前插入一段代码
```
    @Override
    public void visitCode() {
        super.visitCode();
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
    }
```

- 在方法结束前插入一段代码


- 在方法中的try..catch的catch中插入一段代码