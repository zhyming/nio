package com.zhym.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @description:
 * @author: zhym
 * @time: 2020/11/3 0003 1:14
 */
public class DatagramChannelTest {

    @Test
    public void client() {

        try (DatagramChannel datagramChannel = DatagramChannel.open()){
            datagramChannel.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String text = scanner.next();
                buffer.put((LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + ":\n" + text).getBytes());
                buffer.flip();
                datagramChannel.send(buffer, new InetSocketAddress("127.0.0.1", 9090));
                buffer.clear();
            }
        }catch (IOException e) {

            e.printStackTrace();
        }
    }

    @Test
    public void server() {
        try (DatagramChannel datagramChannel = DatagramChannel.open()) {
            datagramChannel.configureBlocking(false);
            datagramChannel.bind(new InetSocketAddress(9090));
            Selector selector = Selector.open();
            datagramChannel.register(selector, SelectionKey.OP_READ);
            //大于0，说明有准备就绪的
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                if (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                       //取出读模式的数据
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        //获取读模式就绪通道
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        int len;
                        while ((len = socketChannel.read(buffer)) != -1) {
                            buffer.flip();
                            System.out.println(new String(buffer.array(), 0, len));
                            buffer.clear();
                        }

                    }
                }

            }


        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
