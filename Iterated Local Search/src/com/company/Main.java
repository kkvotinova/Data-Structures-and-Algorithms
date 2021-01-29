//  Copyright © 2021 Ksuvot
package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // open file
        int[] numbers = openFile();
        int numberOfCities = 1000; // TODO: number of cities

        // main cycle
        for (int iteration = 549; iteration < 551; iteration++) {
            // create Cities
            int j = 0;
            City[] city = new City[numberOfCities];
            for (int i = 0; i < numbers.length; i += 3) {
                city[j] = new City(numbers[i], numbers[i + 1], numbers[i + 2]);
                city[j].setOpen(true);
                ++j;
            }

            // start ILS
            greedyAlgorithm(city, numberOfCities, iteration);
        }
    }

    public static int[] openFile() throws FileNotFoundException {
        File file = new File("C:\\Repositories\\kkvotinova\\Data-Structures-and-Algorithms" +
                "\\Iterated Local Search\\src\\mona_1000.txt");
        Scanner scanner = new Scanner(file);
        int counter = 0;
        int numberOfCity = 0;
        int[] numbers = new int[1000*3]; // TODO: number of cities
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] num = line.split(" ");
            for (String number : num) {
                numbers[counter] = Integer.parseInt(number);
                ++counter;
            }
            ++numberOfCity;
        }
        scanner.close();
        return numbers;
    }

    public static void greedyAlgorithm(City[] city, int numberOfCities, int newId) {
        City[] newCity = new City[numberOfCities]; // new array == answer
        newCity[0] = new City(city[newId].getId(), city[newId].getX(), city[newId].getY()); // create 1st city
        city[newId].setOpen(false); // close old city

        /* main cycle
        *  go all city {city} */
        for (int i = 1; i < numberOfCities; i++) { // TODO: number of cities
            int nextCity = 0;
            int goodDistance = numberOfCities * numberOfCities; // between two city
            int badDistance = 0;
            for (int j = 0; j < numberOfCities; j++) { // search new best city
                if ((newCity[i - 1].getId() != city[j].getId()) && (city[j].isOpen())) { // different cities && city isOpen
                    badDistance = city[0].distanceToCity(newCity[i - 1], city[j]);
                    if (badDistance < goodDistance) {
                        nextCity = j;
                        goodDistance = badDistance;
                    }
                }
            }
            newCity[i] = new City(city[nextCity].getId(), city[nextCity].getX(), city[nextCity].getY()); // create new city
            city[nextCity].setOpen(false); // close old city
        }

        //  print answer
        randomSearch(newCity, numberOfCities);
    }

    public static void randomSearch(City[] city, int numberOfCities) {
        int bestDistance = city[1].getDistance(city, numberOfCities);
        for (int i = 0; i < numberOfCities * 10000; i++) { // TODO: number of cities
            int initial = (int) (Math.random()*numberOfCities - 1);
            int swap = (int) (Math.random()*numberOfCities - 1);
            if (initial == swap) {
                swap = (int) (Math.random()*(numberOfCities - 1));
            }

            if (initial > swap) {
                int x = swap;
                swap = initial;
                initial = x;
            }
            city[0].swapCities(city, initial, swap);

            int currentDistance = city[0].getDistance(city, numberOfCities);
            if (currentDistance < bestDistance) {
                bestDistance = currentDistance;
            } else {
                city[0].swapCities(city, initial, swap);
            }
        }
        printAnswer(city, numberOfCities);
    }

    public static void printAnswer(City[] city, int numberOfCities) {
        int bestDistance = city[0].getDistance(city, numberOfCities);
        //if (bestDistance < 520000) {
            System.out.println(bestDistance);
            for (int i = 0; i < numberOfCities; i++) {
                System.out.print(city[i].getId() + " ");
            }
            System.out.println("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
       // }
    }
}
