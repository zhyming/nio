package com.zhym.nio;

/**
 * @description:
 * @author: zhym
 * @time: 2020/10/30 0030 1:31
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.SortedMap;

/**
 *通道（channel）：用于源节点与目标节点连接，负责缓冲区中的数据传输，channel本身不存储数据，需要配合缓冲区进行数据传输
 *
 * 主要实现类
 * java.io.channels.Channel 接口：
 *      FileChannel
 *      SocketChannel
 *      ServerSocketChannel
 *      DatagramChannel
 *
 * 获取通道
 * 1.getChannel（）
 * 2.NIO.2 open()
 * 3.newByteChannel()
 *
 * 通道之间的数据传输
 * transferFrom
 * transferTo
 *
 * 分散（Scatter）与聚集（Gather）
 * 分散读取（Scattering Reads）:将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writer）：将多个缓冲区中的数据聚集到通道中
 *
 * 字符集
 * 编码：字符串 -》 字节数组
 * 解码：字节数组 -》字符串
 */
public class ChannelTest {

    public static void main(String[] args) {
        //字符集
        Map<String, Charset> charsetSortedMap = Charset.availableCharsets();

        //charsetSortedMap.forEach((k, v) -> System.out.println(k + " --> " + v));

        Charset gbk = Charset.forName("GBK");

        //获取编码器
        CharsetEncoder encoder = gbk.newEncoder();

        //获取解码器
        CharsetDecoder decoder = gbk.newDecoder();

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("注意缺陷多动障碍与神经发育有关");
        charBuffer.flip();
        ByteBuffer byteBuffer = null;
        //编码
        try {
            byteBuffer = encoder.encode(charBuffer);
            int data;
            while ((data = byteBuffer.get()) != -1) {
                System.out.println(data);
            }
        } catch (CharacterCodingException | BufferUnderflowException e) {
            e.printStackTrace();
        }
        System.out.println("------------------------");
        //解码
        try {
            byteBuffer.flip();
            CharBuffer charBuffer1 = decoder.decode(byteBuffer);
            System.out.println(charBuffer1.toString());
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
    }

        public static void main5(String[] args) {
        //分散和聚集
        try(RandomAccessFile accessFile = new RandomAccessFile("E:\\study\\classloader\\stream\\r28.txt", "rw");
            RandomAccessFile accessFile2 = new RandomAccessFile("E:\\study\\classloader\\stream\\r31.txt", "rw");
            //获取通道
            FileChannel channel = accessFile.getChannel();
            FileChannel channel2 = accessFile2.getChannel()) {

            //分配缓冲区
            ByteBuffer buffer1 = ByteBuffer.allocate(100);
            ByteBuffer buffer2 = ByteBuffer.allocate(1024);

            //分散读取
            ByteBuffer[] buffers = {buffer1, buffer2};

            channel.read(buffers);

            //切换缓冲区为读模式--把数据写入缓冲区后一定要切换为读模式，要不然读取不了里面的数据，写不进输出通道
            for (ByteBuffer buffer : buffers) {
                buffer.flip();
            }
            //聚合写入
            channel2.write(buffers);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main4(String[] args) {
        /**
         * 直接缓冲区方式
         */
        try (FileChannel inputChannel = FileChannel.open(Paths.get("E:\\photo\\c.jpg"), StandardOpenOption.READ);
             FileChannel outputChannel = FileChannel.open(Paths.get("E:\\photo\\d.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW)){

            //inputChannel.transferTo(0, inputChannel.size(), outputChannel);

            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main2(String[] args) {

        /**
         * 通道完成文件复制--非直接缓冲区
         *
         */

        try (FileInputStream fileInputStream = new FileInputStream("E:\\photo\\b.jpg");
             FileOutputStream fileOutputStream = new FileOutputStream("E:\\photo\\a.jpg");
             //获取通道--通道与流一样，使用完后都是要关闭的
             FileChannel inputChannel = fileInputStream.getChannel();
             FileChannel outputChannel = fileOutputStream.getChannel()){


            //通道要配合缓冲区来传输数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            //将输入通道数据存入缓冲区中
            while (inputChannel.read(buffer) != -1) {

                //将缓冲区数据传入输出通道
                buffer.flip();
                outputChannel.write(buffer);
                //缓冲区数据传完后需要重置缓冲区状态，清空缓冲区，以进行下一波数据传输
                buffer.clear();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main3(String[] args) {
        //通道完成文件复制--直接缓冲区（内存文件复制）-- 不需要使用通道来传输 -- 直接操作缓冲区进行存取
        try (FileChannel fileChannel = FileChannel.open(Paths.get("E:\\photo\\a.jpg"), StandardOpenOption.READ);
            FileChannel outputChannel = FileChannel.open(Paths.get("E:\\photo\\c.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW)){

            //内存映射文件--缓冲区已经在物理内存中--只有ByteBuffer支持
            MappedByteBuffer inputMapBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            MappedByteBuffer outputMapBuffer = outputChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());

            //直接对缓冲区进行数据操作
            byte[] data = new byte[inputMapBuffer.limit()];
            inputMapBuffer.get(data);
            outputMapBuffer.put(data);

        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
