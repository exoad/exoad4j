# exoad4j

> exoad4j is a library for me to unify all the save locations for my projects

Copyright (C) Jack Meng (exoad) 2023

## Motive

**This project was mostly designed for my own projects.**

Creating desktop applications most likely requires some kind of data to be saved. However, it gets very messy when 
you have multiple projects and you want to save data for each of them. This library is designed to solve this problem.

By creating a single folder and creating subfolders to represent other projects, this helps to simplify that process.

## Usage

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

