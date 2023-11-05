package com.dashboard.groupfiveproject;

import java.util.ArrayList;

public class ItemContainer extends FarmObject {
    private ArrayList<FarmObject> children;

    public ItemContainer(String name, float price, int locationX, int locationY,
                         int length, int width, int height) {
        super(name, price, locationX, locationY, length, width, height);
        this.children = new ArrayList<>();
    }


    public void addChild (FarmObject child) {
        children.add(child);
    }

    public void removeChild (FarmObject child) {
        children.remove(child);
    }

    public ArrayList<FarmObject> getChildren() {
        return children;
    }
}
