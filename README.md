# __SysTensor__   
![Jenkins](https://img.shields.io/jenkins/build?jobUrl=https%3A%2F%2Fjenkins.rgb-cloud.net%2Fjob%2FSysTensor%2F&style=flat-square)

SysTensor is an opensource Java dependency that helps you in your projects with logging and debugging.

## Install
Maven:
``` xml
    <repositories>
        <repository>
            <id>SysTensor</id>
            <url>https://repo.rgb-cloud.net/repository/SysTensor/</url>
        </repository>
    </repositories>
```
``` xml
    <dependencies>
        <dependency>
            <groupId>de.donkaos</groupId>
            <artifactId>SysTensor</artifactId>
            <version>1.3.1</version>
        </dependency>
    </dependencies>
```
# Features
- [Logging](#Logging)
- [Debugging](#Debugging)
- [Wait/Timers](#Wait/Timers)
- [Mysql](#Mysql)
- [Config](#Config)



# Logging
``` java
// Settings
Sys.setPrefix("SYS");   // Default is "SYS"
Sys.useColors(true);    // Default is true
// Logging
Sys.log("test");        // ==> "[09:31:53] [SYS] [LOG] Test"
Sys.success("Test");    // ==> "[09:34:14] [SYS] [SUCCESS] TEST"
Sys.info("Test");       // ==> "[09:34:14] [SYS] [INFO] Test"
Sys.init("Test");       // ==> "[09:34:14] [SYS] [INIT] Test"
Sys.warn("Test");       // ==> "[09:34:14] [SYS] [WARN] Test"
Sys.error("Test");      // ==> "[09:34:14] [SYS] [ERROR] Test"
```


# Debugging
``` java
// Settting
Sys.setDebugging(false);     // Default is false
// Usage
Sys.debug("Test");           // ==> "[09:38:24] [SYS] [DEBUG] Test"
```



# Wait/Timers
``` java
// Usage
Sys.wait.hours(1);
Sys.wait.seconds(1);
Sys.wait.milliSec(1);
Sys.wait.nanoSec(1);
```


# Mysql


# Config

