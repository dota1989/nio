package com.zb.nio;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

/**
 * Created by zhangbin on 2019/1/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IOTest1 {
    @Test
    public void test1() {
        File file = new File("C:\\Users\\DELL\\Desktop\\StandardColumn.sql");
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] data = new byte[1024];
            inputStream.read(data);
            System.out.println("文件内容:" + new String(data));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2() {
        File file = new File("C:\\Users\\DELL\\Desktop\\StandardColumn.sql");
        try (FileReader fileReader = new FileReader(file)){
            char[] data = new char[1024];
            fileReader.read(data);
            System.out.println("文件内容:" + new String(data));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        File file1 = new File("C:\\Users\\DELL\\Desktop\\StandardColumn.sql");
        File file2 = new File("C:\\Users\\DELL\\Desktop\\123456.txt");
        try {
            FileInputStream inputStream1 = new FileInputStream(file1);
            FileOutputStream outputStream = new FileOutputStream(file2);

            byte[] data = new byte[1024];
            while (inputStream1.read(data) != -1){
                outputStream.write(data);
            }
            inputStream1.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3_1() {
        File file1 = new File("C:\\Users\\DELL\\Desktop\\StandardColumn.sql");
        File file2 = new File("C:\\Users\\DELL\\Desktop\\123456.txt");
        try {
            FileInputStream inputStream1 = new FileInputStream(file1);
            FileOutputStream outputStream = new FileOutputStream(file2);
            int c = 0;
            while ((c = inputStream1.read()) != -1){
                outputStream.write(c);
            }
            inputStream1.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        File file = new File("C:\\Users\\DELL\\Desktop\\123456.txt");
        try {
//            FileOutputStream outputStream = new FileOutputStream(file);
//            outputStream.write("ni真是个好人啊".getBytes());

            FileWriter outputStream = new FileWriter(file);
            outputStream.write("ni真是个好人啊");

            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        File file = new File("C:\\Users\\DELL\\Desktop\\北京.txt");
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(0);
            byte[] data = new byte[1024];
            int hasRead = 0;
            while ((hasRead = randomAccessFile.read(data)) > 0){
                System.out.println(new String(data,0,hasRead));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6() {
        File file = new File("C:\\Users\\DELL\\Desktop\\北京.txt");
        File file1 = new File("C:\\Users\\DELL\\Desktop\\北京123.txt");
        try {
            FileReader inputStream = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(inputStream);

            PrintWriter printWriter = new PrintWriter(new FileWriter(file1));

            String l;
            while ((l=bufferedReader.readLine())!=null){
                printWriter.println(l);
            }

            bufferedReader.close();
            printWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
