package com.zb.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by zhangbin on 2019/1/23.
 */
public class SocketChannelTest {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8080));
        ByteBuffer buffer = ByteBuffer.wrap("1234567890".getBytes());
        socketChannel.write(buffer);

        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int n = 0;
        while ((n = socketChannel.read(readBuffer)) != -1){
            readBuffer.flip();

            byte[] data = new byte[n];
            readBuffer.get(data);
            String s = new String(data, "UTF-8");
            System.out.println(s);
        }

    }
}
