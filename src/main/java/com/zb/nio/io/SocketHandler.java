package com.zb.nio.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by zhangbin on 2019/1/23.
 */
public class SocketHandler implements Runnable {

    private SocketChannel socketChannel;

    public SocketHandler(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int c = 0;
            while ((c = socketChannel.read(byteBuffer)) > 0){
                byteBuffer.flip();
                byte[] data = new byte[c];
                byteBuffer.get(data);
                String receive = new String(data, "UTF-8");
                System.out.println("收到消息：" + receive);

                ByteBuffer writebuffer = ByteBuffer.wrap(("我已經收到消息，它是：" + receive).getBytes());
                socketChannel.write(writebuffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
