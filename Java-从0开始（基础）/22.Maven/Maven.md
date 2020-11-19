# Maven项目结构

Maven 生成的一个普通的项目结构如下

```cmd
a-maven-project
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   └── resources
│   └── test
│       ├── java
│       └── resources
└── target
```

-   pom.xml：项目描述文件
-   src/main/java：Java 源码目录
-   src/main/resources：Java 源码资源目录
-   src/test/java：Java 测试源码目录
-   src/test/resources：Java 测试资源目录
-   target：编译、打包生成的文件目录



`pow.xml` 文件中，groupId`，`artifactId`和`version 唯一确定了一个项目（此外也是依赖包的唯一标识）。

# 依赖管理

>   我们声明了自己的项目需要`abc`，Maven会自动导入`abc`的jar包，再判断出`abc`需要`xyz`，又会自动导入`xyz`的jar包，这样，最终我们的项目会依赖`abc`和`xyz`两个jar包



Q：有哪些依赖关系？

A：Maven定义了几种依赖关系，分别是`compile`、`test`、`runtime`和`provided`：

| scope    | 说明                                          | 示例            |
| :------- | :-------------------------------------------- | :-------------- |
| compile  | 编译时需要用到该jar包（默认）                 | commons-logging |
| test     | 编译Test时需要用到该jar包                     | junit           |
| runtime  | 编译时不需要，但运行时需要用到                | mysql           |
| provided | 编译时需要用到，但运行时由JDK或某个服务器提供 | servlet-api     |



默认的`compile`是最常用的，Maven会把这种类型的依赖直接放入classpath。

`test`依赖表示仅在测试时使用，正常运行时并不需要。最常用的`test`依赖就是JUnit：

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.3.2</version>
    <scope>test</scope>
</dependency>
```

`runtime`依赖表示编译时不需要，但运行时需要。最典型的`runtime`依赖是JDBC驱动，例如MySQL驱动：

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.48</version>
    <scope>runtime</scope>
</dependency>
```

`provided`依赖表示编译时需要，但运行时不需要。最典型的`provided`依赖是Servlet API，编译的时候需要，但是运行时，Servlet服务器内置了相关的jar，所以运行期不需要：

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.0</version>
    <scope>provided</scope>
</dependency>
```



---

Q：如何唯一标识依赖？

A：对于某个依赖，Maven 只需要3个变量即可唯一确定某个 jar 包：

-   groupId：属于组织的名称，类似Java的包名；
-   artifactId：该jar包自身的名称，类似Java的类名；
-   version：该jar包的版本。



实例：

```xml
<dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope> <!-- 表示依赖的类型 这里是测试(test)时依赖-->
    </dependency>
  </dependencies>
```



---

Q：Maven 如何下载依赖包？

A：默认使用中央仓库，但国内推荐使用阿里云镜像仓库加速包的下载。



---

Q：如何配置使用 Maven 镜像仓库？

A：在用户主目录下进入`.m2`目录，创建一个`settings.xml`配置文件，内容如下：

```xml
<settings>
    <mirrors>
        <mirror>
            <id>aliyun</id>
            <name>aliyun</name>
            <mirrorOf>central</mirrorOf>
            <!-- 国内推荐阿里云的Maven镜像 -->
            <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
        </mirror>
    </mirrors>
</settings>
```

最后效果如下：

![image-20201119153457915](https://gitee.com/llillz/images/raw/master/image-20201119153457915.png)



---

Q：如何查找到要引用的第三方组件的`groupId`、`artifactId`和`version`？

A：通过[search.maven.org](https://search.maven.org/)搜索关键字，找到对应的组件后，直接复制：

![copy-maven](https://gitee.com/llillz/images/raw/master/l)



# Maven 的构建流程

## Maven的生命周期（lifecycle）

-   Maven 的**生命周期（lifecycle）**由一系列的 **phase** 组成
-   Maven 有多种生命周期，默认的生命周期是 `default`，其次还有 `clean`、`site`
-   以 `default` 生命周期为例，它包含以下phase：
    -   validate
    -   initialize
    -   generate-sources
    -   process-sources
    -   generate-resources
    -   process-resources
    -   compile
    -   process-classes
    -   generate-test-sources
    -   process-test-sources
    -   generate-test-resources
    -   process-test-resources
    -   test-compile
    -   process-test-classes
    -   test
    -   prepare-package
    -   package
    -   pre-integration-test
    -   integration-test
    -   post-integration-test
    -   verify
    -   install
    -   deploy
-   运行命令 `mvn phase`，就会从第一个 validate 一直运行到 `phase` 。例如运行 `mvn package` 就会从 validate 运行到 package。
-   Maven 另一个常用的生命周期是 clean，它会执行3个 phase：
    -   pre-clean
    -   clean （注意这个clean不是lifecycle而是phase）
    -   post-clean
-   还可以把不同生命周期结合起来。例如运行 `mvn clean package`，Maven 将先执行整个 `clean` 周期，然后开始执行 `default` 周期，直到 package 这个 phase。



---

Q：**实际开发常用的 Maven 命令**

A：经常使用的命令有：

`mvn clean`：清理所有生成的class和jar；

`mvn clean compile`：先清理，再执行到`compile`；

`mvn clean test`：先清理，再执行到`test`，因为执行`test`前必须执行`compile`，所以这里不必指定`compile`

`mvn clean package`：先清理，再执行到`package`



大多数phase在执行过程中，因为我们通常没有在`pom.xml`中配置相关的设置，所以这些phase什么事情都不做。

经常用到的phase其实只有几个：

-   clean：清理
-   compile：编译
-   test：运行测试
-   package：打包



---

>   执行一个具体的 phase 会触发一个或多个 **goal**

例如：

| 执行的Phase | 对应执行的Goal                     |
| :---------- | :--------------------------------- |
| compile     | compiler:compile                   |
| test        | compiler:testCompile surefire:test |

-   goal的命名总是`abc:xyz`这种形式，`:`前面的表示插件，后面的就是具体目标动作



---

注：大多数情况，我们只要指定phase，就默认执行这些phase默认绑定的goal，只有**少数情况，我们可以直接指定运行一个goal**，**例如，启动Tomcat服务器**：

```cmd
mvn tomcat:run
```



---

 **package、class、method 类比 lifecycle、phase、goal**：

-   lifecycle相当于Java的package，它包含一个或多个phase；
-   phase相当于Java的class，它包含一个或多个goal；
-   goal相当于class的method，它其实才是真正干活的



# Maven 的插件

Q：Maven 插件有什么用？

A：

-   执行每个phase，都是通过某个插件（plugin）来执行的。
-   Maven本身其实并不知道如何执行`compile`，它只是负责找到对应的`compiler`插件，然后执行默认的`compiler:compile`这个goal来完成编译。
-   所以，使用 Maven，实际上就是配置好需要使用的插件，然后通过 phase 调用它们。



---

Q：Maven 的内置插件以及对应的 phase？

A：

| 插件名称 | 对应执行的phase |
| :------- | :-------------- |
| clean    | clean           |
| compiler | compile         |
| surefire | test            |
| jar      | package         |



---

Q：如何自定义和使用插件？

A：使用自定义插件时需要在 `pom.xml` 中声明。具体的配置和使用方法要见发布网站。



**例如**：使用 `maven-shade-plugin` 插件，用于创建一个可执行的 jar 包，需要在 `pom.xml` 中作如下声明

```xml
<project>
    ...
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.2.1</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration> <!-- aven-shade-plugin需要指定Java程序的入口 -->
                            <transformers>
                                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                    <mainClass>com.itranswarp.learnjava.Main</mainClass>
                                </transformer>
                            </transformers>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```



一些常用的插件：

-   maven-shade-plugin：打包所有依赖包并生成可执行jar；
-   cobertura-maven-plugin：生成单元测试覆盖率报告；
-   findbugs-maven-plugin：对Java源码进行静态分析以找出潜在问题。



# Maven Wrapper

Q：Maven Wrapper 是什么？

A：默认情况下，系统所有项目都会使用全局安装的这个Maven版本。但是，对于某些项目来说，它可能必须使用某个特定的Maven版本，这个时候，就可以**使用 Maven Wrapper，它负责给这个特定的项目安装指定版本的Maven，而其他项目不受影响。**



---

Q：如何安装 Maven Wrapper？

A：在项目的根目录使用命令行安装 Maven Wrapper，输入命令：

```cmd
mvn -N io.takari:maven:0.7.6:wrapper
```

-   该命令会自动生成最新版本的 Maven，这里的 `0.7.6` 是 Maven Wrapper 的版本

命令执行完后，在项目的根目录会多出 `mvnw`、`mvnw.cmd`和`.mvn` 三个目录。之后，只需要把`mvn`命令改成`mvnw`就可以使用跟项目关联的Maven。例如：

```cmd
mvnw clean package
```



# 发布到 Maven 仓库

略，以后需要再看。详见：https://www.liaoxuefeng.com/wiki/1252599548343744/1347981037010977

# 小结

-   Maven 项目结构
-   Maven 依赖管理
-   Maven 的构建流程
    -   核心概念：生命周期（lifecycle）、phase、goal
-   Maven Wrapper 是一个工具，可以为项目安装指定版本的 Maven。使用时只需要在项目文件下输入命令 `mvn -N io.takari:maven:0.7.6:wrapper` 即可，其中 `0.7.6` 是 Maven Wrapper 的版本，安装的 Maven 默认是最新版本。安装好之后，只需使用 `mvnw` 命令代替 `mvn` 命令，就可以使用该项目的 Maven了。

