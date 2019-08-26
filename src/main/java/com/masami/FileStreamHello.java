package com.masami;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件传输
 * @Author: gyc
 * @Date: 2019/8/23 9:04
 */
public class FileStreamHello {


    @Test
    public void fileInputStrean() throws Exception {
        FileInputStream fin = new FileInputStream("E://test.txt");

        // 获取通道
        FileChannel fc = fin.getChannel();


        int count = 1024;

        long size = fc.size();

        int loop = (int) (size/count);
        if(size%count!=0){
            loop++;
        }


        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(count);


        for (int i = 0; i < loop; i++) {
            fc.read(buffer,i*count);
            buffer.flip();

            while (buffer.remaining() > 0) {
                byte b = buffer.get();
                System.out.print(((char)b));
            }

            buffer.flip();

        }


        fin.close();
    }
    static private final byte message[] = { 83, 111, 109, 101, 32, 98, 121, 116, 101, 115, 46 };


    @Test
    public void fileOutputStrean() throws Exception {
        FileOutputStream fout = new FileOutputStream( "E://test.txt" );

        FileChannel fc = fout.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate( 2048 );

        for (int i=0; i<1024; ++i) {
            buffer.put((byte) 99);
        }


        for (int i=0; i<1024; ++i) {
            buffer.put((byte) 97);
        }

        buffer.flip();

        fc.write( buffer );

        fout.close();
    }


}
