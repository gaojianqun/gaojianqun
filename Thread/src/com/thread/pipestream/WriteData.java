package com.thread.pipestream;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * Created by gaojianqun on 2018/10/17.
 */
public class WriteData extends Thread{

    public void writeMethod(PipedOutputStream out){
        System.out.println("write:");
        try {
            for(int i = 0; i<300;i++){
                String outData = "" + (i+1);
                out.write(outData.getBytes());
                System.out.println(outData);
            }
            System.out.println();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
