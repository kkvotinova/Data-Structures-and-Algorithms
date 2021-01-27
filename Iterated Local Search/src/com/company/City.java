//  Copyright © 2021 Ksuvot
package com.company;

public class City {
    private int id;
    private int x;
    private int y;
    private int status;
    private int previousCity;
    private int nextCity;

    public City() {}

    public City(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public double distanceToCity(City city, City cityNext) {
        int x = Math.abs(cityNext.getX() - city.getX());
        int y = Math.abs(cityNext.getY() - city.getY());
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public void swapCities(City city, City citySwap) {
        int swap = city.previousCity;
        if (Math.abs(city.id - citySwap.id) > 1) {
            city.previousCity = citySwap.previousCity;
            citySwap.previousCity = swap;
            swap = city.nextCity;
            city.nextCity = citySwap.nextCity;
            citySwap.nextCity = swap;
        } else {
            city.previousCity = citySwap.id;
            citySwap.previousCity = swap;
            city.nextCity = citySwap.nextCity;
            citySwap.nextCity = city.id;
        }
    }

    public void generateInitialTravel(City[] city, int numberOfCities) {
        city[0].nextCity = city[1].id;
        city[0].previousCity = city[numberOfCities - 1].id;
        city[numberOfCities - 1].nextCity = city[0].id;
        city[numberOfCities - 1].previousCity = city[numberOfCities - 2].id;
        for (int i = 1; i < numberOfCities - 1; i++) {
            city[i].nextCity = city[i + 1].id;
            city[i].previousCity = city[i - 1].id;
        }
    }

    public double getDistance(int numberOfCities, City[] city) {
        double distance = 0;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPreviousCity() {
        return previousCity;
    }

    public void setPreviousCity(int previousCity) {
        this.previousCity = previousCity;
    }

    public int getNextCity() {
        return nextCity;
    }

    public void setNextCity(int nextCity) {
        this.nextCity = nextCity;
    }
}
