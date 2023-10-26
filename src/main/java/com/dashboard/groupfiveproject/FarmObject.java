package com.dashboard.groupfiveproject;

public class FarmObject {
    protected String objName;
    protected int objPrice;
    protected int locationX;
    protected int locationY;
    protected int objLength;
    protected int objWidth;
    protected int objHeight;
    public FarmObject (String objName, int objPrice, int locationX, int locationY,
                       int objLength, int objWidth, int objHeight){
        this.objName = objName;
        this.objPrice = objPrice;
        this.locationX = locationX;
        this.locationY = locationY;
        this.objHeight = objHeight;
        this.objLength = objLength;
        this.objWidth = objWidth;
    }

    public void setObjName(String objName){}
    public void setObjPrice(int objPrice){}
    public void setLocationX(int locationX){}
    public void setLocationY(int locationY){}
    public void setObjLength(int objLength){}
    public void setObjWidth(int objWidth){}
    public void setObjHeight(int objHeight){}

}
