package com.zhym.stream;

import com.zhym.po.Student;

import java.io.*;

/**
 * @description:
 * @author: zhym
 * @time: 2020/10/28 0028 1:40
 */
public class ObjectInputStreamTest {

    public static void main(String[] args) {
        /*try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("E:\\study\\classloader\\stream\\o.txt"))){
            Student student = (Student)objectInputStream.readObject();
            System.out.println(student);
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("E:\\study\\classloader\\stream\\o.txt"))){

            Student student = new Student("张三", 17, "火星");
            objectOutputStream.writeObject(student);
            objectOutputStream.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
