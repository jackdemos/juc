package com.taocaicai;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @project JUC
 * @author Oakley
 * @created 2021-10-16 12:10:12:10
 * @package com.taocaicai
 * @description TODO
 * @since: 0.0.0.1
 */
public class OSFile {

  static byte[] bytes = "123456789\n".getBytes();
  static String path = "./out.txt";

  public static void main(String[] args) throws IOException {
    int select = Integer.parseInt(args[0]);
    switch (select) {
      case 0:
        oldStream();
        break;
      case 1:
        nioStream();
        break;
      default:
        System.out.println("test test");
    }
  }

  private static void oldStream() throws IOException {

    File file = new File(path);
    FileOutputStream outputStream = new FileOutputStream(file);
    for (int i = 0; i < 100000; i++) {
      outputStream.write(bytes);
      outputStream.flush();
    }
    outputStream.close();
  }

  public static void nioStream() throws IOException {
    RandomAccessFile aFile = new RandomAccessFile(path, "rw");
    FileChannel channel = aFile.getChannel();
    for (int i = 0; i < 100000; i++) {
      channel.write(ByteBuffer.wrap(bytes));
    }
    channel.force(true);
    channel.close();
  }
}
