//  Copyright © 2021 Ksuvot
package com.company;

public class City {
    private int id;
    private int x;
    private int y;
    private int nextCity;

    public City() {}

    public City(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    // distance between two cities
    public int distanceToCity(City city, City cityNext) {
        int x = Math.abs(cityNext.getX() - city.getX());
        int y = Math.abs(cityNext.getY() - city.getY());
        return (int) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    // swap the two cities
    public void swapCities(City[] city, int cityId, int citySwapId) {
        if (cityId == 0 && citySwapId == 1) { // 1 and 2
            city[0].setNextCity(city[2].id);
            city[1].setNextCity(city[0].id);
            city[city.length - 1].setNextCity(city[1].id);
        } else if (cityId == 0 && citySwapId == city.length - 1) { // 1 and end
            city[0].setNextCity(city[city.length - 1].id);
            city[city.length - 1].setNextCity(city[1].id);
            city[city.length - 2].setNextCity(city[0].id);
        } else if (cityId == city.length - 2 && citySwapId == city.length - 1) { // end-1 and end
            city[city.length - 2].setNextCity(city[0].id);
            city[city.length - 1].setNextCity(city[city.length - 2].id);
            city[city.length - 3].setNextCity(city[city.length - 1].id);
        } else if (citySwapId - cityId == 1) { // num and num + 1
            city[cityId - 1].setNextCity(city[citySwapId].id);
            city[cityId].setNextCity(city[citySwapId + 1].id);
            city[citySwapId].setNextCity(city[cityId].id);
        } else if (citySwapId - cityId > 1) { // other
            city[cityId - 1].setNextCity(city[citySwapId].id);
            city[cityId].setNextCity(city[citySwapId + 1].id);
            city[citySwapId - 1].setNextCity(city[cityId].id);
            city[citySwapId].setNextCity(city[cityId + 1].id);
        } else {
            System.out.println("WRONG!");
            System.exit(-1);
        }
    }

    // return swap the two cities
    /*public void returnSwapCities(City city, City citySwap) {
        int swap = city.previousCity;
        if (Math.abs(city.id - citySwap.id) > 1) {
            city.previousCity = citySwap.previousCity;
            citySwap.previousCity = swap;
            swap = city.nextCity;
            city.nextCity = citySwap.nextCity;
            citySwap.nextCity = swap;
        } else {
            swap = citySwap.previousCity;
            citySwap.previousCity = city.id;
            city.previousCity = swap;
            citySwap.nextCity = city.nextCity;
            city.nextCity = citySwap.id;
        }
    }*/

    // generating the starting path
    public void generateInitialTravel(City[] city, int numberOfCities) {
        city[0].nextCity = city[1].id;
       // city[0].previousCity = city[numberOfCities - 1].id;
        city[numberOfCities - 1].nextCity = city[0].id;
       // city[numberOfCities - 1].previousCity = city[numberOfCities - 2].id;
        for (int i = 1; i < numberOfCities - 1; i++) {
            city[i].nextCity = city[i + 1].id;
         //   city[i].previousCity = city[i - 1].id;
        }
    }

    // count the final path
    public int getDistance(int numberOfCities, City[] city) {
        int distance = 0;
        for (int i = 0; i < numberOfCities; i++) {
           distance += city[i].distanceToCity(city[i], city[city[i].nextCity - 1]);
        }
        return distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNextCity() {
        return nextCity;
    }

    public void setNextCity(int nextCity) {
        this.nextCity = nextCity;
    }
}
