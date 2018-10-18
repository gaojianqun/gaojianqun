package com.java.base.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by gaojianqun on 2018/6/13.
 */
public class StreamTest6 {

    public static void main(String [] args){
        long uniqueWords = 0;
        try(Stream<String> lines =
                    Files.lines(Paths.get("C://Users//Administrator//Desktop//data.txt"), Charset.defaultCharset())){
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
            System.out.println(uniqueWords);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
