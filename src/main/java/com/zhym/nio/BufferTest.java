package com.zhym.nio;

import java.nio.ByteBuffer;

/**
 * @description:
 * @author: zhym
 * @time: 2020/10/30 0030 0:30
 */
public class BufferTest {

    public static void main1(String[] args) {
        //获取缓冲区
        /**
         * 缓冲区存取：put
         *             get
         *
         * 缓冲区核心属性：
         * 1.capacity 容量
         * 2.limit 可操作数据大小
         * 3.position 位置，处于capacity和limit中
         *
         * mark：标记，记录当前position位置，可以通过reset（）恢复到mark位置
         *
         * 0 <= mark <= position <= limit <= capacity
         */
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        String str = "Hello World!";

        //默认为写模式--可写数据
        byteBuffer.put(str.getBytes());
        System.out.println("-----------------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        //切换为读数据模式
        byteBuffer.flip();
        System.out.println("-----------------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        //读取数据
        System.out.println("-----------------------");
        byte[] data = new byte[byteBuffer.limit()];
        byteBuffer.get(data);
        System.out.println(new String(data, 0, data.length));
        System.out.println("-----------------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println("----------rewind-------------");
        //重置position，使可重新读数据
        byteBuffer.rewind();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println("----------clear-------------");
        //清空缓冲区，但是原先的数据还存在，数据处于‘被遗忘’状态（position、limit、capacity值均被重置）
        byteBuffer.clear();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        //被清空后还是可以获取得到原先的数据
        System.out.println((char) byteBuffer.get());

    }

    public static void main2(String[] args) {
        String str = "System.out.println(byteBuffer.position());";

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put(str.getBytes());

        buffer.flip();

        byte[] data = new byte[buffer.limit()];
        buffer.get(data, 0, 5);

        System.out.println(new String(data, 0, 5));

        System.out.println(buffer.position());
        //mark标记
        buffer.mark();
        buffer.get(data, 5, 10);
        System.out.println(new String(data, 5, 10));
        System.out.println(buffer.position());
        System.out.println("----------------hasRemaining---------------");
        //判断缓冲区中是否还有剩余数据--其实就是limit - position
        if (buffer.hasRemaining()) {
            //获取缓冲区中可操作数据量
            System.out.println(buffer.remaining());
        }
        //reset 恢复到mark位置
        buffer.reset();
        System.out.println(buffer.position());
        System.out.println("----------------hasRemaining---------------");
        //判断缓冲区中是否还有剩余数据
        if (buffer.hasRemaining()) {
            //获取缓冲区中可操作数据量
            System.out.println(buffer.remaining());
        }
    }

    /**
     * 非直接缓冲区：通过allocate() 方法分配缓冲区，将缓冲区建立在JVM的内存中
     * 直接缓冲区：通过allocateDirect（）方法分配直接缓冲区，将缓冲区建立在物理内存中，可以提高效率
     *
     * @param args
     */
    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        System.out.println(buffer.isDirect());

    }
}
