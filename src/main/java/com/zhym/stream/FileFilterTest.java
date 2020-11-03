package com.zhym.stream;

import java.io.File;

/**
 * @description:
 * @author: zhym
 * @time: 2020/10/29 0029 0:49
 */
public class FileFilterTest {

    /**
     * FileFilter 就是Predicate
     * @param args
     */
    public static void main(String[] args) {
        File file = new File("E:\\study\\classloader\\stream\\a11Q");
        //过滤掉文件夹，只返回文件
        file.listFiles(File::isFile);

    }
}
