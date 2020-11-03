package com.zhym.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @description:
 * @author: zhym
 * @time: 2020/11/2 0002 23:51
 */
public class BlockNIOTest {

    private static volatile int index = 1;

    public static void main(String[] args) {

        new Thread(() -> {
            while (true) {
                if (index  == 2) {
                    //获取通道
                    try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9090));
                         FileChannel fileChannel = FileChannel.open(Paths.get("E:\\photo\\a.jpg"), StandardOpenOption.READ)) {

                        //分配指定大小缓冲区
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                        //发送本地文件给服务器
                        while (fileChannel.read(byteBuffer) != -1) {
                            byteBuffer.flip();
                            socketChannel.write(byteBuffer);
                            byteBuffer.clear();
                        }

                        socketChannel.shutdownOutput();

                        //接收服务端的反馈
                        int len = 0;
                        while ((len = socketChannel.read(byteBuffer)) != -1) {
                            byteBuffer.flip();
                            System.out.println(new String(byteBuffer.array(), 0, len));
                            byteBuffer.clear();
                        }

                    }catch (IOException e) {
                        e.printStackTrace();
                    }

                    index ++;
                    return;
                }

            }


        }).start();

        new Thread(() -> {
            while (true) {
                if (index == 1) {
                    //获取通道
                    try (ServerSocketChannel serverSocketChannelChannel = ServerSocketChannel.open();
                         FileChannel fileChannel = FileChannel.open(Paths.get("E:\\photo\\receive\\g.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE)){
                        //绑定连接端口
                        serverSocketChannelChannel.bind(new InetSocketAddress(9090));

                        //获取客户端连接通道
                        SocketChannel socketChannel = serverSocketChannelChannel.accept();
                        //分配指定大小到缓冲区
                        ByteBuffer buffer = ByteBuffer.allocate(1024);

                        //接收客户端数据，并保存到本地
                        while (socketChannel.read(buffer) != -1) {
                            buffer.flip();
                            fileChannel.write(buffer);
                            buffer.clear();
                        }

                        buffer.put("服务端接收数据成功".getBytes());
                        buffer.flip();
                        socketChannel.write(buffer);

                        index ++;
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (index == 3) {
                    return;
                }
            }

        }).start();


    }
}
