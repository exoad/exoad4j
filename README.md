# exoad4j
[![Gradle build](https://github.com/exoad/exoad4j/actions/workflows/gradle.yml/badge.svg)](https://github.com/exoad/exoad4j/actions/workflows/gradle.yml)

> exoad4j is a library for me to unify all the save locations for my projects

Copyright (C) Jack Meng (exoad) 2023

## Motive

**This project was primarily designed for managing multiple personal projects.**

Developing desktop applications often involves the need to save data. However, managing data becomes challenging when dealing with multiple projects, each requiring its own set of saved data. This library aims to address this issue.

The solution involves creating a centralized folder and establishing subfolders to represent individual projects. This approach streamlines the data-saving process for each project, reducing complexity.

## Usage

**Check on GitHub Packages:** https://github.com/exoad/exoad4j/packages/

### Gradle

```groovy
repositories {
    mavenCentral()
}
dependencies {
    implementation("pkg.exoad:exoad4j:1.1.2")
}
```

### Maven

```xml
 <dependency>
  <groupId>pkg.exoad</groupId>
  <artifactId>exoad4j</artifactId>
  <version>1.1.2</version>
</dependency> 
```

## Example

**Creating a registry for a new application**

```java
import pkg.exoad.distrib.DistribFolder;
import pkg.exoad.distrib.Exoad;

public class MyNewApplication
{
	public static void main(String... args)
	{
		Exoad.arm();
		Exoad.putRegistry(
				"MyNewApplication",
				Exoad.registerProgram(new DistribFolder("astralascent"))
		);
        }
}
```

**Writing to a file under that registry**

```java
Exoad.getRegistry("MyNewApplication").writeToFile("Hello World!", "test.txt");
```

