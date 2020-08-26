package com.design.factory2;

import com.design.factory.ShapeFactory;
import java.util.function.Supplier;

public class FactoryPatternDemo {

    public static void main(String[] args){
        Supplier<ShapeFactory> shapeFactory = ShapeFactory::new;
        shapeFactory.get().getShape("CIRCLE").draw();
        shapeFactory.get().getShape("RECTANGLE").draw();
    }

}