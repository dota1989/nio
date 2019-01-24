package com.zb.nio.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by zhangbin on 2019/1/23.
 */
public class Server {

    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.socket().bind(new InetSocketAddress(8080));

            while (true){
                SocketChannel socketChannel = serverSocketChannel.accept();
                SocketHandler socketHandler = new SocketHandler(socketChannel);
                new Thread(socketHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
