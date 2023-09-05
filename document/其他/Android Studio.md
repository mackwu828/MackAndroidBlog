
## Gradle下载地址
https://services.gradle.org/distributions/

放在对应目录
```
C:\Users\wumengjiao\.gradle\wrapper\dists\gradle-6.1.1-all\cfmwm155h49vnt3hynmlrsdst
```

## Studio无法运行JavaMain
在.idea/gradle.xml中的<GradleProjectSettings>标签下添加
```
<option name="delegatedBuild" value="false" />
```

如下：
```
<?xml version="1.0" encoding="UTF-8"?>
<project version="4">
  <component name="GradleMigrationSettings" migrationVersion="1" />
  <component name="GradleSettings">
    <option name="linkedExternalProjectsSettings">
      <GradleProjectSettings>
        <option name="delegatedBuild" value="false" />
        <option name="testRunner" value="PLATFORM" />
        <option name="distributionType" value="DEFAULT_WRAPPED" />
        <option name="externalProjectPath" value="$PROJECT_DIR$" />
        <option name="gradleJvm" value="1.8" />
        <option name="modules">
...
```

修改.idea下的workspace.xml
找到<component name="PropertiesComponent">标签节点并在之下添加一行
```
<property name="dynamic.classpath" value="true" />
```
