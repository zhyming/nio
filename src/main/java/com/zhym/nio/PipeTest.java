package com.zhym.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @description:
 * @author: zhym
 * @time: 2020/11/3 0003 1:34
 */
public class PipeTest {

    public static void main(String[] args) {


        try {
            //获取管道
            final Pipe pipe = Pipe.open();
            //获取缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            Pipe.SinkChannel sinkChannel = pipe.sink();

            buffer.put("通过单向管道发送数据".getBytes());
            buffer.flip();
            sinkChannel.write(buffer);


            //读取缓冲区数据
            Pipe.SourceChannel sourceChannel = pipe.source();
            buffer.flip();
            int len = sourceChannel.read(buffer);
            System.out.println(new String(buffer.array(), 0, len));

            sinkChannel.close();
            sourceChannel.close();

        }catch (IOException e) {

        }

    }

}
