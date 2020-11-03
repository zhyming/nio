package com.zhym.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 * @description:
 * @author: zhym
 * @time: 2020/11/3 0003 0:52
 */
public class NonBlockTest {
    private static volatile int index = 1;
    public static void main(String[] args) {
        {

            new Thread(() -> {
                while (true) {
                    if (index  == 2) {
                        //获取通道
                        try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9090));
                             FileChannel fileChannel = FileChannel.open(Paths.get("E:\\photo\\a.jpg"), StandardOpenOption.READ)) {

                            //切换非阻塞模式
                            socketChannel.configureBlocking(false);

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

                            //切换非阻塞模式
                            serverSocketChannelChannel.configureBlocking(false);
                            //绑定连接端口
                            serverSocketChannelChannel.bind(new InetSocketAddress(9090));

                            //获取选择器
                            Selector selector = Selector.open();

                            //将通道注册到选择器上,并且指定监听事件
                            serverSocketChannelChannel.register(selector, SelectionKey.OP_ACCEPT);

                            //轮询式的获取选择器上已经就绪的事件

                            while (selector.select() > 0) {
                                //获取已就绪监听事件
                                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                                while (keys.hasNext()) {
                                    //得到准备就绪事件
                                    SelectionKey selectionKey = keys.next();
                                    //判断具体是什么事件准备就绪
                                    if (selectionKey.isAcceptable()) {
                                        //若接收就绪，就拿到客户端连接
                                        SocketChannel socketChannel = serverSocketChannelChannel.accept();
                                        //切换非阻塞模式
                                        socketChannel.configureBlocking(false);
                                        //将通道注册到选择器上
                                        socketChannel.register(selector, SelectionKey.OP_READ);
                                    }else if(selectionKey.isReadable()) {
                                        //获取读就绪状态通道
                                        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                                        //读取通道数据
                                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                                        int len;
                                        while ((len = socketChannel.read(buffer)) != -1) {
                                            buffer.flip();
                                            System.out.println(new String(buffer.array(), 0, len));
                                            buffer.clear();
                                        }

                                    }

                                    //取消选择键 selectionKey
                                    keys.remove();
                                }
                            }

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
}
