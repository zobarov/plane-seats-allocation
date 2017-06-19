# Airplane Seats Allocation

Test assessment project

## Problem

You run an Airline that has several planes that fly to different destinations around the world.
You pride yourself on having a high customer satisfaction with those that fly with you. This
you achieve by ensuring that:
● Groups of travellers are seated together on the same row.
● Providing travellers with a window seat if request.
To determine the best sitting arrangements on the flight create a program that takes an input
file as a command line argument and prints the results to standard out.

The program should aim to maximize customer satisfaction. The last line in the above output
indicates the percentage of customers that have had their preferences satisfied. If the plane
is over subscribed the program should aim to maximize customer satisfaction of those
customers waiting for the flight.


### Test Examples

An example input file
is:
4 4
1W 2 3
4 5 6 7
8
9 10 11W
12W
13 14
15 16
The first line specifies the dimensions of the plane. The first digit is the number of seats in a
row and the second digit is the number of rows on the plane.
Each subsequent line describes a group of travellers. For example, the first line of travelers
describes a group of three where the first traveller has a preference for a window seat. Each
number uniquely identifies the traveller on the flight.
The output for the above file should be:
1 2 3 8
4 5 6 7
11 9 10 12
13 14 15 16
100%

## Running and usage

### From IDE:
1. Import as Maven Project
2. Maven Install
3. Run HourglassTaskApplication

### From CommandLine:

Follow one of the following items:
http://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html

## Usage




## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring](https://spring.io/) - Spring Boot, Core, REST, Test 
* [JUnit](https://spring.io/) - Testing framework

## Authors

* **Alex Gerasimenko** - *Initial work* - [AlexGera](https://github.com/zobarov)
