package com.zhym.stream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Set;

/**
 * @description:
 * @author: zhym
 * @time: 2020/10/29 0029 1:04
 */
public class PropertiesTest {

    public static void main(String[] args) {
        //1.创建Properties集合
        Properties properties = new Properties();

        properties.setProperty("name", "张三");
        properties.setProperty("age", "34");
        System.out.println(properties);
        //
        final Set<String> names = properties.stringPropertyNames();
        System.out.println(names);

        //和流有关方法
        try (FileWriter fileWriter = new FileWriter("E:\\study\\classloader\\stream\\d29.txt");
             PrintWriter writer = new PrintWriter(fileWriter);
             FileReader reader = new FileReader("E:\\study\\classloader\\stream\\d29.txt")
             ){
            //properties.list(writer);
            Properties properties1 = new Properties();
            properties.store(writer, "练习");
            properties1.load(reader);
            System.out.println(properties1);
        }catch (IOException e) {

        }


    }
}
