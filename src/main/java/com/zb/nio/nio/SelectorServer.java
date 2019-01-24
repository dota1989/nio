package com.zb.nio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhangbin on 2019/1/23.
 */
public class SelectorServer {
    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress("localhost", 8080));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true){
                int n = selector.select();
                if(n <= 0){
                    continue;
                }
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if(key.isAcceptable()){
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if(key.isReadable()){
                        SocketChannel socketChannel = (SocketChannel)key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int num = socketChannel.read(byteBuffer);
                        if(num > 0){
                            System.out.println("收到數據：" + new String(byteBuffer.array()));
                            socketChannel.register(selector, SelectionKey.OP_WRITE);
                        } else if(num == -1){
                            socketChannel.close();
                        }

                    } else if(key.isWritable()){

                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.wrap("返回给客户端的数据...".getBytes());
                        socketChannel.write(buffer);

                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
