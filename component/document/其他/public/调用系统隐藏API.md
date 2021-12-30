

## 一、lib中添加framework.jar

## 二、依赖framework
```

 // framework需要使用compileOnly，implementation会出现类冲突和65535异常，加了multidex还是会有65535
 compileOnly files('libs/framework.jar')
```

## 三、添加preBuild
```
preBuild {
    doLast {
        def imlFile = file(project.name + ".iml")
        println 'Change ' + project.name + '.iml order'
        try {
            def parsedXml = (new XmlParser()).parse(imlFile)
            def jdkNode = parsedXml.component[1].orderEntry.find { it.'@type' == 'jdk' }
            parsedXml.component[1].remove(jdkNode)
            def sdkString = "Android API " + android.compileSdkVersion.substring("android-".length()) + " Platform"
            new Node(parsedXml.component[1], 'orderEntry', ['type': 'jdk', 'jdkName': sdkString, 'jdkType': 'Android SDK'])
            groovy.xml.XmlUtil.serialize(parsedXml, new FileOutputStream(imlFile))
        } catch (FileNotFoundException ignored) {
            // nop, iml not found
        }
    }
}
```

## 四、添加projectsEvaluated
```
allprojects {
    gradle.projectsEvaluated {
        tasks.withType(JavaCompile) {
            options.compilerArgs.add('-Xbootclasspath/p:libs/framework.jar')
        }
    }
    repositories {
        google()
        jcenter()
    }
}
```

```
options.compilerArgs.add('-Xbootclasspath/p:module名称/libs/framework.jar')
```