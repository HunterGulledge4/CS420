package com.dashboard.groupfiveproject;

public class Item extends FarmObject {

    public Item(String name, float price, int locationX, int locationY,
            int length, int width, int height, String rectangle) {
        /*
         * Inherits the properties of the superclass and sets them as their own separate
         * properties
         * of each Item object
         */
        super(name, price, locationX, locationY, length, width, height, rectangle);

    }

}
