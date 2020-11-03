package com.zhym.stream;

import java.io.*;

/**
 * @description:
 * @author: zhym
 * @time: 2020/10/28 0028 23:57
 */
public class BufferedReaderTest {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\study\\classloader\\stream\\a.txt"), "gbk"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("E:\\study\\classloader\\stream\\b.txt"))){

            /*int data;
            while ((data = reader.read()) != -1) {
                System.out.println((char) data);
            }*/
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                writer.newLine(); //window \r\n ---- linux \n
                writer.write(line);
            }
            writer.flush();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
