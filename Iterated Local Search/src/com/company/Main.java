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

        // create Cities
        int j = 0;
        City[] city = new City[numberOfCities];
        for (int i = 0; i < numbers.length; i += 3) {
            city[j] = new City(numbers[i], numbers[i + 1], numbers[i + 2]);
            city[j].setOpen(true);
            ++j;
        }

        // start ILS
        iteratedLocalSearch(city, numberOfCities);
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

    public static void iteratedLocalSearch(City[] city, int numberOfCities) {
        City[] newCity = new City[numberOfCities]; // new array == answer
        int newId = (int) (Math.random()*numberOfCities - 1); // 1st city
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

        // answer
        int bestDistance = newCity[0].getDistance(newCity, numberOfCities);
        System.out.println(bestDistance);
        for (int i = 0; i < numberOfCities; i++) {
            System.out.print(newCity[i].getId() + " ");
        }
    }
}
