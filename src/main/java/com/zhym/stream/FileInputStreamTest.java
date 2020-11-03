package com.zhym.stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * @description:
 * @author: zhym
 * @time: 2020/10/28 0028 0:34
 */
public class FileInputStreamTest {

    public static void main(String[] args) {
        /*try(FileInputStream fileInputStream = new FileInputStream("E:\\study\\classloader\\stream\\a.txt");
            ) {
            int data;
            byte[] buffer = new byte[10];
            while ((data = fileInputStream.read(buffer)) != -1) {
                //fileOutputStream.write(data);
                //fileOutputStream.write(buffer, 0, data);
                //System.out.println(data);
                System.out.println(new String(buffer, 0, data, "gbk"));
            }
            //fileOutputStream.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }*/
        try (FileOutputStream fileOutputStream = new FileOutputStream("E:\\study\\classloader\\stream\\b.txt", true)){
            String str = "gasdgasdgasdgt";
            fileOutputStream.write(str.getBytes());
        }catch (IOException e) {
            e.printStackTrace();
        }
        /*try (FileReader fileReader = new FileReader("E:\\study\\classloader\\stream\\a.txt")){
            int data;
            while ((data = fileReader.read()) != -1) {
                System.out.println(data);
            }


        }catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}
