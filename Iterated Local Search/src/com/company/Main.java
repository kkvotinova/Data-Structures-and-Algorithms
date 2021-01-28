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
            ++j;
        }

        // start ILS
        int bestDistance = iteratedLocalSearch(city, numberOfCities);
        System.out.println(bestDistance);

        // answer
        System.out.print(city[0].getId() + "->");
        for (int i = 0; i < numberOfCities; i++) {
            System.out.print(city[i].getNextCity() + "->");
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

    public static int iteratedLocalSearch(City[] city, int numberOfCities) {
        // generate initial travel
        city[0].generateInitialTravel(city, numberOfCities);
        int bestDistance = city[1].getDistance(numberOfCities, city);

        for (int i = 0; i < numberOfCities * 1000; i++) { // TODO: number of cities
            int initial = (int) (Math.random()*numberOfCities - 1);
            int swap = (int) (Math.random()*numberOfCities - 1);
            if (initial == swap) {
                swap = (int) (Math.random()*(numberOfCities - 2));
            }

            if (initial > swap) {
                int x = swap;
                swap = initial;
                initial = x;
            }
            city[0].swapCities(city, initial, swap);

            int currentDistance = city[0].getDistance(numberOfCities, city);
            if (currentDistance < bestDistance) {
                bestDistance = currentDistance;
            } /*else {
                city[0].returnSwapCities(city[initial], city[swap]);
            }*/
        }
        return bestDistance;
    }
}
