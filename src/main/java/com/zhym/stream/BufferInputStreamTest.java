package com.zhym.stream;

import java.io.*;

/**
 * @description:
 * @author: zhym
 * @time: 2020/10/28 0028 1:11
 */
public class BufferInputStreamTest {

    public static void main(String[] args) {
        /*try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("E:\\study\\classloader\\stream\\a.txt"))) {
            int len;
            byte[] data = new byte[64];
            while ((len = bufferedInputStream.read(data)) != -1) {
                System.out.println(new String(data, 0, len, "gbk"));
            }

        }catch (IOException e) {
            e.printStackTrace();
        }*/
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("E:\\study\\classloader\\stream\\b.txt"))) {
            String str = "D:\\Java\\jdk1.8.0_261\\bin\\java.exe \"-javaagent:D:\\JetBrains\\IntelliJ IDEA 2019.1.3\\lib\\idea_rt.jar=51735:D:\\JetBrains\\IntelliJ IDEA 2019.1.3\\bin\" -Dfile.encoding=UTF-8 -classpath D:\\Java\\jdk1.8.0_261\\jre\\lib\\charsets.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\deploy.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\ext\\access-bridge-64.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\ext\\cldrdata.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\ext\\dnsns.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\ext\\jaccess.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\ext\\jfxrt.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\ext\\localedata.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\ext\\nashorn.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\ext\\sunec.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\ext\\sunjce_provider.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\ext\\sunmscapi.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\ext\\sunpkcs11.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\ext\\zipfs.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\javaws.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\jce.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\jfr.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\jfxswt.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\jsse.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\management-agent.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\plugin.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\resources.jar;D:\\Java\\jdk1.8.0_261\\jre\\lib\\rt.jar;E:\\idea-workspace\\io\\target\\classes com.zhym.stream.BufferInputStreamTest\n" +
                    "注意缺陷多动障碍与神经发育有关，是多种因素综合作用的结果。其中，\n" +
                    "最主要的原因是遗传基因。多动症的发生，首先会受到母孕期、怀孕期等\n" +
                    "先天因素的影响。在这个基础上，母体的压力大小、饮食结构变化、创伤\n" +
                    "感染等多种因素都有可能对儿童神经系统造成损害，导致儿童多动症的发\n" +
                    "生。除此之外，孩子的成长环境、生活环境也会起到一定的影响。孩子成\n" +
                    "长期间，父母的教育方式、性格特点都可能对孩子造成潜在的影响，导致\n" +
                    "孩子出现说话不经大脑、行为冲动这样的行为表现。总之，“多动症”的\n" +
                    "发生，受到遗传、环境和社会心理多种因素的影响";

            bufferedOutputStream.write(str.getBytes());
            //bufferedOutputStream.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
