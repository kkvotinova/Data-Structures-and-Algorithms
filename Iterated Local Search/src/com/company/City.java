//  Copyright © 2021 Ksuvot
package com.company;

public class City {
    private int id;
    private int x;
    private int y;
    private boolean isOpen;

    /* constructors */
    public City() {}

    public City(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    // swap the two cities
    public void swapCities(City[] city, int cityId, int citySwapId) {
        int id1 = city[cityId].getId();
        int x1 = city[cityId].getX();
        int y1 = city[cityId].getY();

        city[cityId].setId(city[citySwapId].getId());
        city[cityId].setX(city[citySwapId].getX());
        city[cityId].setY(city[citySwapId].getY());

        city[citySwapId].setId(id1);
        city[citySwapId].setX(x1);
        city[citySwapId].setY(y1);
    }
    // distance between two cities
    public int distanceToCity(City city, City cityNext) {
        int x = Math.abs(cityNext.getX() - city.getX());
        int y = Math.abs(cityNext.getY() - city.getY());
        return (int) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    // count the final path
    public int getDistance(City[] city, int numberOfCities) {
        int distance = 0;
        for (int i = 0; i < numberOfCities - 1; i++) {
           distance += city[i].distanceToCity(city[i], city[i + 1]);
        }
        distance += city[0].distanceToCity(city[numberOfCities - 1], city[0]);
        return distance;
    }

    /* getter and setter */
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

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
