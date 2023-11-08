package com.dashboard.groupfiveproject;

import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class ItemContainer extends FarmObject {
    private ArrayList<FarmObject> children;

    public ItemContainer(String name, float price, int locationX, int locationY,
                         int length, int width, int height, Rectangle rectangle) {
        super(name, price, locationX, locationY, length, width, height, rectangle);
        this.children = new ArrayList<>();
    }

}
