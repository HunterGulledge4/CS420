package com.dashboard.groupfiveproject;

import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

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
