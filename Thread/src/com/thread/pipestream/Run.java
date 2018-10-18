package com.thread.pipestream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by gaojianqun on 2018/10/17.
 */
public class Run {

    public static void main(String [] args){
        try {
            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();

            PipedInputStream inputStream = new PipedInputStream();
            PipedOutputStream outputStream = new PipedOutputStream();

            outputStream.connect(inputStream);
            Thread threadRead = new Thread(readData);
            threadRead.start();

            Thread threadWrite = new Thread(writeData);
            threadWrite.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
