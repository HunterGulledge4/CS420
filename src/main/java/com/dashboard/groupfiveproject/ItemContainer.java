package com.dashboard.groupfiveproject;

import java.util.ArrayList;

public class ItemContainer extends FarmObject implements FarmInterface {
    public ArrayList<Item> itemArrayList;
    protected String itemContainerName;
    protected int itemContainerPrice;
    protected int itemContainerLocationX;
    protected int itemContainerLocationY;
    protected int itemContainerLength;
    protected int itemContainerWidth;
    protected int itemContainerHeight;
    public ItemContainer(String itemContainerName, int itemContainerPrice, int itemContainerLocationX, int itemContainerLocationY,
                         int itemContainerLength, int itemContainerWidth, int itemContainerHeight,
                         ArrayList<Item> itemArrayList){
        super(itemContainerName, itemContainerPrice, itemContainerLocationX, itemContainerLocationY, itemContainerLength,
                itemContainerWidth, itemContainerHeight);

        this.itemContainerName = itemContainerName;
        this.itemContainerPrice = itemContainerPrice;
        this.itemContainerLocationX = itemContainerLocationX;
        this.itemContainerLocationY = itemContainerLocationY;
        this.itemContainerHeight = itemContainerHeight;
        this.itemContainerLength = itemContainerLength;
        this.itemContainerWidth = itemContainerWidth;
    }

    @Override
    public void setObjName(String itemContainerName) {
        this.itemContainerName = itemContainerName;
    }

    @Override
    public void setObjPrice(int itemContainerPrice) {
        this.itemContainerPrice = itemContainerPrice;
    }

    @Override
    public void setLocationX(int itemContainerLocationX) {
        this.itemContainerLocationX = itemContainerLocationX;
    }

    @Override
    public void setLocationY(int itemContainerLocationY) {
        this.itemContainerLocationY = itemContainerLocationY;
    }

    @Override
    public void setObjLength(int itemContainerLength) {
        this.itemContainerLength = itemContainerLength;
    }

    @Override
    public void setObjWidth(int itemContainerWidth) {
        this.itemContainerWidth = itemContainerWidth;
    }

    @Override
    public void setObjHeight(int itemContainerHeight) {
        this.itemContainerHeight = itemContainerHeight;
    }


    @Override
    public String getName() {
        return itemContainerName;
    }

    @Override
    public Integer getPrice() {
        return itemContainerPrice;
    }

    @Override
    public Integer getLocationX() {
        return itemContainerLocationX;
    }

    @Override
    public Integer getLocationY() {
        return itemContainerLocationY;
    }

    @Override
    public Integer getLength() {
        return itemContainerLength;
    }

    @Override
    public Integer getWidth() {
        return itemContainerWidth;
    }

    @Override
    public Integer getHeight() {
        return itemContainerHeight;
    }
}
