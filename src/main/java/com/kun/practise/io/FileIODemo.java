package com.kun.practise.io;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by jrjiakun on 2018/11/26
 */
public class FileIODemo {
    private static final String sourcePath = System.getProperty("user.dir")+"/src/main/resources/file/source.txt";
    private static final String targetPath = System.getProperty("user.dir")+"/src/main/resources/file/target.txt";

    public static void bioFileCopy() {
        try (InputStream in = new BufferedInputStream(new FileInputStream(new File(sourcePath)));
             OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(targetPath)))
        ) {
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }

    public static void zeroCopy() {
        try (FileChannel sourceChannel = new FileInputStream(new File(sourcePath)).getChannel();
             FileChannel targeChannel = new FileOutputStream(new File(targetPath)).getChannel();
        ) {
            for (long count = sourceChannel.size(); count > 0; ) {
                long transferred = sourceChannel.transferTo(
                        sourceChannel.position(), count, targeChannel);
                sourceChannel.position(sourceChannel.position() + transferred);
                count -= transferred;
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }

    public static void fileLibCopy() {
        try (InputStream in = new BufferedInputStream(new FileInputStream(new File(sourcePath)))
        ) {
            //类似于stream
            Files.copy(in, Paths.get(targetPath));
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }

    public static void main(String[]args){
        long systemTimeStart = System.currentTimeMillis();
        zeroCopy();
        long systemTimeEnd = System.currentTimeMillis();
        System.out.println(systemTimeEnd-systemTimeStart);
    }
}
