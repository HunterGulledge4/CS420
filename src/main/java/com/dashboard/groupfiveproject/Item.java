package com.dashboard.groupfiveproject;

import java.util.ArrayList;

public class Item extends FarmObject implements FarmInterface{
    protected String itemName;
    protected int itemPrice;
    protected int itemLocationX;
    protected int itemLocationY;
    protected int itemLength;
    protected int itemWidth;
    protected int itemHeight;
    public Item(String itemName, int itemPrice, int itemLocationX, int itemLocationY, int itemLength,
                int itemWidth, int itemHeight){
        /*Inherits the properties of the superclass and sets them as their own separate properties
        of each Item object*/
        super(itemName, itemPrice, itemLocationX, itemLocationY, itemLength, itemWidth, itemHeight);

        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemLocationX = itemLocationX;
        this.itemLocationY = itemLocationY;
        this.itemHeight = itemHeight;
        this.itemLength = itemLength;
        this.itemWidth = itemWidth;
    }

    @Override
    public void setObjName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public void setObjPrice(int itemPrice){
        this.itemPrice = itemPrice;
    }

    @Override
    public void setLocationX(int itemLocationX) {
        this.itemLocationX = itemLocationX;
    }

    @Override
    public void setLocationY(int itemLocationY) {
        this.itemLocationY = itemLocationY;
    }

    @Override
    public void setObjLength(int itemLength) {
        this.itemLength = itemLength;
    }

    @Override
    public void setObjWidth(int itemWidth) {
        this.itemWidth = itemWidth;
    }

    @Override
    public void setObjHeight(int itemHeight) {
        this.itemHeight = itemHeight;
    }


    @Override
    public String getName() {
        return itemName;
    }

    @Override
    public Integer getPrice() {
        return itemPrice;
    }

    @Override
    public Integer getLocationX() {
        return itemLocationX;
    }

    @Override
    public Integer getLocationY() {
        return itemLocationY;
    }

    @Override
    public Integer getLength() {
        return itemLength;
    }

    @Override
    public Integer getWidth() {
        return itemWidth;
    }

    @Override
    public Integer getHeight() {
        return itemHeight;
    }

    /*
    @Override
    public void getArrayList(ArrayList<Item> itemArrayList) {
        return itemArrayList;
    }

     */
}
