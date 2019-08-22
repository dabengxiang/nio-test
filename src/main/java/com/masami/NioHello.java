package com.masami;


import java.nio.ByteBuffer;

/**
 * @Author: gyc
 * @Date: 2019/8/22 17:29
 */
public class NioHello {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < 8; i++) {
            buffer.put((byte) i);
        }

//        buffer.position(4);

        System.out.println(buffer);
        while (buffer.hasRemaining()){
            int b = buffer.get();
            System.out.println(b);

        }

    }
}
