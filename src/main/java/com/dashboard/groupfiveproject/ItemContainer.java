package com.dashboard.groupfiveproject;

import java.util.ArrayList;
import java.util.List;

public class ItemContainer extends FarmObject {

    private List<FarmObject> farmObjects;

    public ItemContainer(String name, float price, int locationX, int locationY,
            int length, int width, int height, String rectangle) {
        super(name, price, locationX, locationY, length, width, height, rectangle);
        farmObjects = new ArrayList<>();
    }

    public void addFarmObject(FarmObject farmObject) {
        farmObjects.add(farmObject);
    }

    public void removeFarmObject(FarmObject farmObject) {
        farmObjects.remove(farmObject);
    }

    public List<FarmObject> getFarmObjects() {
        return farmObjects;
    }

}
