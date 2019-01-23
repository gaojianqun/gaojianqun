package com.jvm.test2;

import com.sun.javafx.util.Logging;
import sun.rmi.runtime.Log;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class Test2 {

    public static List<Double> list = new ArrayList<>();

    public void populateList(){
        for(int i = 0;i < 1000000;i++){
            list.add(Math.random());
        }
        LOGGER.info("Debug Point 2");
    }

    public static void main(String [] args){
        LOGGER.info("Debug Point 1");
        new Test2().populateList();
        LOGGER.info("Debug Point 3");
    }

}
