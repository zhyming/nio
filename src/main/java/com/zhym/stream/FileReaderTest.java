package com.zhym.stream;
import java.io.*;

/**
 * @description:
 * @author: zhym
 * @time: 2020/10/28 0028 23:24
 */
public class FileReaderTest {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("E:\\study\\classloader\\stream\\r28.txt");
            FileWriter writer = new FileWriter("E:\\study\\classloader\\stream\\w28.txt")){
            int len;
            char[] data = new char[32];
            while ((len = reader.read(data)) != -1) {
                System.out.println(new String(data, 0, len));
                writer.write(data, 0, len);
            }
            writer.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
