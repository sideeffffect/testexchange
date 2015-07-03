# Grid tester

[![Build Status](https://travis-ci.org/sideeffffect/testexchange.svg)](https://travis-ci.org/sideeffffect/testexchange)

This simple Java program tests validity of colored structures in a grid.


## Classes overview

There are 5 most important classes:

 * `cz.sideeffect.testexchange.grid.Grid` represents the grid.

 * `cz.sideeffect.testexchange.grid.Structure` represents a structure in the grid.

 * `cz.sideeffect.testexchange.grid.Coord` represents a cell in the grid.

 * `cz.sideeffect.testexchange.Validator` validates the grid that is reads from input.

 * `cz.sideeffect.testexchange.Main` is used for executing the program from commandline.


## Usage

This project is managed by [gradle](https://gradle.org/).


### Running

You can run the program via gradle.
The instructions are read from `stdin`.
Result is written to `stdout`.

```
gradle -q run < src/test/resources/providedExampleInput
```


### Tests

The project is automatically tested at [Travis-CI](https://travis-ci.org/sideeffffect/testexchange).
You can run tests yourself via gradle:

```
gradle test
```


### Documentation

The documentation is located in `build/docs/javadoc`.
It is build via gradle:

```
gradle javadoc
```


## TODO

 * reading from file was underspecified, thus `stdin` is used instead
