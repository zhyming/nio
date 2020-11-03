package com.zhym.stream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description:
 * @author: zhym
 * @time: 2020/10/29 0029 0:09
 */
public class PrintWriteTest {

    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("E:\\study\\classloader\\stream\\bb.txt"))){
            writer.write("注意缺陷多动障碍与神经发育有关，是多种因素综合作用的结果。其中，最主要的原因是遗传基因。多动症的发生，首先会受到母孕期、怀孕期等先天因素的影响。在这个基础上，母体的压力大小、饮食结构变化、创伤感染等多种因素都有可能对儿童神经系统造成损害，导致儿童多动症的发生。除此之外，孩子的成长环境、生活环境也会起到一定的影响。孩子成长期间，父母的教育方式、性格特点都可能对孩子造成潜在的影响，导致孩子出现说话不经大脑、行为冲动这样的行为表现。总之，“多动症”的发生，受到遗传、环境和社会心理多种因素的影响");
            writer.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
