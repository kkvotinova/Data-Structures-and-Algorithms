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

        // answer
        System.out.println(bestDistance);
        for (int i = 0; i < numberOfCities; i++) {
            System.out.print(city[i].getId() + " ");
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
        return bestDistance;
    }
}
