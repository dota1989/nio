package com.zb.nio;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by zhangbin on 2019/1/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileTest1 {

    @Test
    public void test1() throws IOException {
        File file = new File("C:\\Users\\DELL\\Desktop\\StandardColumn.sql");
        System.out.println(file.isDirectory());
        System.out.println(file.isFile());
        System.out.println(file.exists());

        System.out.println(file.canExecute());
        System.out.println(file.canRead());
        System.out.println(file.canWrite());

        System.out.println(file.getAbsolutePath());
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.getPath());

        System.out.println(file.length());

        Path path = Paths.get("C:\\Users\\DELL\\Desktop\\StandardColumn.sql");
        System.out.println(path.getFileName());

        BasicFileAttributeView basicView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        BasicFileAttributes basicFileAttributes = basicView.readAttributes();

        System.out.println(basicFileAttributes.creationTime());
        System.out.println(basicFileAttributes.lastModifiedTime());
        System.out.println(basicFileAttributes.size());
    }
}
