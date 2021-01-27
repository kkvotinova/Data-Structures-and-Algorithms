//  Copyright © 2021 Ksuvot
package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // open file
        int[] numbers = openFile();
        int numberOfCities = numbers.length / 3;

        // create Cities
        int j = 0;
        City[] city = new City[numberOfCities];
        for (int i = 0; i < numbers.length; i += 3) {
            city[j] = new City(numbers[i], numbers[i + 1], numbers[i + 2]);
            ++j;
        }

        double bestDistance = iteratedLocalSearch(city, numberOfCities);
        System.out.format("%.2f", bestDistance);

        /*for (int i = 0; i < 8; ++i) {
            System.out.println(city[i].getId() + " " + city[i].getPreviousCity() + " " +city[i].getNextCity());
            System.out.println(city[2].getId() + " " + city[2].getX() + " " +city[2].getY());
        }*/
    }


    public static int[] openFile() throws FileNotFoundException {
        File file = new File("C:\\Repositories\\kkvotinova\\Data-Structures-and-Algorithms" +
                "\\Iterated Local Search\\src\\text.txt");
        Scanner scanner = new Scanner(file);
        int counter = 0;
        int numberOfCity = 0;
        int[] numbers = new int[8*3]; // TODO: number of cities
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

    public static double iteratedLocalSearch(City[] city, int numberOfCities) {
        // generate initial travel
        city[0].generateInitialTravel(city, numberOfCities);
        double bestDistance = city[1].getDistance(numberOfCities, city);

        return bestDistance;
    }
}
