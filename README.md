# Automated Test Suite Generation

This project deals with utility of the nature based algorithms namely **Genetic Algorithm (GA)**, **Ant Colony Optimization (ACO)** algorithm, and **Artificial Bee Colony (ABC)** algorithm in *automatic generation of optimized test suite* for a given set of programs. The performance of algorithms is evaluated using various factors such as number of paths  covered, number of iterations, number of test cases produced and time taken for generation of test suite.


## Code Organization

Within the download you'll find the following directories and files. You'll see something like this:

```
src/main/java/de/chandanand
├── algorithm/
│   └── ABC.java
├── common/
│   ├── module/
│   │   ├── Graph.java
│   │   ├── Node.java
│   │   ├── Path.java
│   │   ├── Paths.java
│   │   ├── TestCase.java
│   │   └── TestSuite.java
│   └── program/
│       ├── EvenOdd.java
│       ├── LeapYear.java
│       ├── Marks.java
│       ├── MaximumOfThree.java
│       ├── PointCircle.java
│       ├── Program.java
│       ├── ProgramStrategy.java
│       ├── Quadrant.java
│       ├── QuadraticEquation.java
│       ├── Remainder.java
│       └── TriangleClassifier.java
└── ABCRunner.java
```


## Running the Project

```
$ git clone https://github.com/chandanand/automated-test-suite.git
$ cd automated-test-suite
$ mvn clean install
$ java -jar target/automated-test-suite-1.0-SNAPSHOT.jar
```


## Creators

**Chand Anand**

* <https://twitter.com/chandanand>
* <https://github.com/chandanand>

## References

[Comparison of Search based Techniques for Automated Test Data Generation](http://www.ijcaonline.org/archives/volume95/number23/16732-6881), International Journal of Computer Applications (IJCA). Ruchika Malhotra, Chand Anand, Nikita Jain, Apoorva Mittal.


## Copyright and license

Code released under [the MIT license](https://github.com/chandanand/ttp_with_gwo/blob/master/LICENSE).
