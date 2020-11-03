package com.zhym.stream;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: zhym
 * @time: 2020/10/29 0029 0:27
 */
public class FileTest {

    public static void domain(String[] args) throws IOException {
        //File file = new File("E:\\study\\classloader\\stream\\a.txt");

        System.out.println("名称分隔符 -》 " + File.separator);
        System.out.println("路径分隔符 -》 " + File.pathSeparator);
        //1.创建文件-对象
        File file = new File("E:\\study\\classloader\\stream\\a11Q.txt");
        //new File("a.txt") -- 如果不写绝对路径，会创建在当前项目根路径下
        //2.创建文件 -- 创建成功返回true，文件已存在（创建失败）返回false
        final boolean b = file.createNewFile();
        System.out.println(b);
        //3.删除文件
        /*if (file.exists()) {
            //直接删除
            file.delete();
            //使用jvm退出时删除--虚拟机退出时删除，即程序运行结束时
            file.deleteOnExit();
        }*/
        //4.获取文件信息
        //获取绝对路径
        System.out.println(file.getAbsoluteFile());
        //获取名称
        System.out.println(file.getName());
        //获取父目录
        System.out.println(file.getParent());
        //获取文件大小
        System.out.println(file.length());
        //获取文件创建时间
        System.out.println(file.lastModified());
        //判断是否文件
        System.out.println(file.isFile());


    }

    public static void main(String[] args) {
        //创建文件夹
        File file2 = new File("E:\\study\\classloader\\stream\\a11Q");
        final boolean b = file2.mkdir();
        System.out.println(b);
        System.out.println(file2.length());
        //文件夹删除只能删除空目录，所以必须先清空文件夹下所有东西
        //final boolean delete = file2.delete();
        //System.out.println(delete);
        //获取文件夹下内容
        //返回字符串数组
        //file2.list();
        //返回文件数组
        //file2.listFiles();
    }
}
