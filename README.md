# Buzz-For-Pc

![Java CI with Gradle](https://github.com/spyridon97/Buzz-For-Pc/workflows/Java%20CI%20with%20Gradle/badge.svg)

--------------------------------------------------------------------------------
## Description

Buzz-for-pc is the implementation of the famous quiz game [Buzz!](https://en.wikipedia.org/wiki/Buzz!) for PC.
You can play with it with 1 or 2 players. If you are 1 player, there are 3 modes: **Correct Answer**, **Stop Timer**, **Bet**.
If you are 2 players, then there are 2 extra modes (including the previous 3): **Fast Answer**, **Thermometer**.
While playing, you can choose a set of key bindings. After the end of every round, the statistics of the game are updated

--------------------------------------------------------------------------------
## Programming Language & Build System
* Java 1.8,
* Gradle 6.7,
* Marathon

--------------------------------------------------------------------------------
## Structure of repository
* .github/workflows/gradle.yml
    * This defines the Continuous Integration pipeline
* marathon
    * This directory includes the marathon Testing application.
* marathonTests
    * This directory includes the project for the marathon gui tests.
* src/main
    * This directory includes the implementation of the program.
* src/test
    * This directory includes the junit tests of the program.
* Statistics
  * This directory includes the statistic files for the program.
* .gitignore
    * This file includes what git should ignore.
* run_marathon.sh
    * This script executes the marathon gui tests.
* build.gradle
    * This file describes how to build the program using gradle.
* LICENCE
    * This file includes the licence of this repository.
* README.md
    * This file.

--------------------------------------------------------------------------------
## Installation & Compilation

To compile your code run:
```
gradle wrapper && chmod +x gradlew && ./gradlew build
```

To run the marathon tests you will need **xvfb** and **firefox** to be installed on your linux computer.

--------------------------------------------------------------------------------
## Contact Information

Author: Spiros Tsalikis

