package com.design.factory2;

import java.util.function.Supplier;

public class ShapeFactory {

    public Shape getShape(String shapeType){
        switch (shapeType){
            case "CIRCLE":
                Supplier<Shape> shape1 = Circle::new;
                return shape1.get();
            case "RECTANGLE":
                Supplier<Shape> shape2 = Rectangle::new;
                return shape2.get();
            default:
                break;
        }
        return null;
    }

}
