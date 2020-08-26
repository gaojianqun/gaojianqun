package com.design.factory;

public class ShapeFactory {

    public Shape getShape(String shapeType){
        switch (shapeType){
            case "CIRCLE":
                return new Circle();
            case "RECTANGLE":
                return new Rectangle();
            default:
                break;
        }
        return null;
    }

}
