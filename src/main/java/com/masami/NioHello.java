package com.masami;


import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: gyc
 * @Date: 2019/8/22 17:29
 */
public class NioHello {


    /**
     * 基本的操作
     */
    @Test
    public void  hello () {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            // 将给定整数写入此缓冲区的当前位置，当前位置递增
            buffer.put((byte) i);
        }
        buffer.flip();  // 重设此缓冲区，将限制设置为当前位置，然后将当前位置设置为 0
        System.out.println(buffer);
        while (buffer.hasRemaining()){
            int b = buffer.get();   //取缓冲区里的数据，当前位置递增
            System.out.println(b);

        }
    }


    /**
     * 缓冲区的分配
     */

    @Test
    public void test2(){
        ByteBuffer allocate = ByteBuffer.allocate(10);
        byte[] arr = new byte[allocate.capacity()];
        ByteBuffer wrap = ByteBuffer.wrap(arr);
        arr[0] = 1;
        System.out.println(wrap.get());
        wrap.put(1, (byte) 2);
        System.out.println(arr[1]);

    }


    /**
     * 缓冲区分片
     */

    @Test
    public void test3(){

        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 1; i <= buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        buffer.position(3);
        buffer.limit(7);


        ByteBuffer slice = buffer.slice();

        for (int i = 0; i < slice.capacity(); i++) {
            int b = slice.get(i);
            b = b*10;
            slice.put(i, (byte) b);
        }


//        System.out.println(buffer);


        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }

    }


    /**
     * 创建一个只读缓冲区
     */

    @Test
    public void test4(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 1; i <= buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        buffer.put(0, (byte) 100);


        System.out.println(readOnlyBuffer.position());
        readOnlyBuffer.position(0);
        System.out.println(buffer.position());
        System.out.println(readOnlyBuffer.position());
        System.out.println(readOnlyBuffer.get(0));

        //readOnlyBuffer.put(0, (byte) 66);  //java.nio.ReadOnlyBufferException会报错

    }


    /**
     * 直接缓冲区
     */

    @Test
    public void  test5 () {
        ByteBuffer buffer = ByteBuffer.allocateDirect(10);  // 使用 allocateDirect，而不是 allocate
        for (int i = 0; i < buffer.capacity(); i++) {
            // 将给定整数写入此缓冲区的当前位置，当前位置递增
            buffer.put((byte) i);
        }
        buffer.flip();  // 重设此缓冲区，将限制设置为当前位置，然后将当前位置设置为 0
        System.out.println(buffer);
        while (buffer.hasRemaining()){
            int b = buffer.get();   //取缓冲区里的数据，当前位置递增
            System.out.println(b);

        }
    }

    /**
     * 内存映射
     */
    @Test
    public void test6() throws IOException {
        final int start = 0;
         final int size = 1024;

        RandomAccessFile raf = new RandomAccessFile( "/test.txt", "rw" );
        FileChannel fc = raf.getChannel();
        //把缓冲区跟文件系统进行一个映射关联
        //只要操作缓冲区里面的内容，文件内容也会跟着改变
        MappedByteBuffer mbb = fc.map( FileChannel.MapMode.READ_WRITE,start, size );
        System.out.println(mbb.get(0));
//        mbb.put( 0, (byte)97 );
        mbb.put( 1023, (byte)122 );
        raf.close();
    }




}
