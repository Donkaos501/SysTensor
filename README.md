# __SysTensor__  [![](https://jitpack.io/v/Donkaos501/SysTensor.svg)](https://jitpack.io/#Donkaos501/SysTensor)

SysTensor is an opensource Java dependency that helps you in your projects with logging and debugging.

# Install
Maven:
``` xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
```
``` xml
    <dependencies>
	<dependency>
	    <groupId>com.github.Donkaos501</groupId>
	    <artifactId>SysTensor</artifactId>
	    <version>Tag</version>
	</dependency>
    </dependencies>
```
Gradle
``` 
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
``` 
	dependencies {
	        implementation 'com.github.Donkaos501:SysTensor:Tag'
	}
```

Last Version [here](https://jitpack.io/#Donkaos501/SysTensor)



# Features
- [Logging](#Logging)
- [Debugging](#Debugging)
- [Wait/Timers](#Wait/Timers)



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
