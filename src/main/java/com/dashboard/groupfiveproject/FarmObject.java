package com.dashboard.groupfiveproject;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class FarmObject implements FarmInterface {
    protected String name;
    protected float price;
    protected int locationX;
    protected int locationY;
    protected int length;
    protected int width;
    protected int height;
    protected String shape;
    protected Rectangle rectangle;
    protected Circle circle;

    public FarmObject(String name, float price, int locationX, int locationY,
            int length, int width, int height, String shape) {
        this.name = name;
        this.price = price;
        this.locationX = locationX;
        this.locationY = locationY;
        this.height = height;
        this.length = length;
        this.width = width;
        this.shape = shape;
        this.rectangle = new Rectangle(locationX, locationY, width, height);
        this.circle = new Circle(locationX, locationY, height);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public Integer getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    @Override
    public Integer getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    @Override
    public Integer getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public Integer getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public Integer getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getShape() {
        return shape;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle, int locationX, int locationY, int width, int height) {
        this.rectangle = rectangle;
        this.locationX = locationX;
        this.locationY = locationY;
        this.width = width;
        this.height = height;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle, int locationX, int locationY, int height) {
        this.circle = circle;
        this.locationX = locationX;
        this.locationY = locationY;
        this.height = height;
    }

}
