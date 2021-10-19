package com.taocaicai;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * @project JUC
 * @author Oakley
 * @created 2021-10-16 12:41:12:41
 * @package com.taocaicai
 * @description TODO
 * @since: 0.0.0.1
 */
public class UsingThreadRandom {
  public static void main(String[] args) throws Exception {
    File file = new File("out.txt");
    startThread(4, file.length(), "out.txt", "bbb.txt");
  }

  /**
   * 开启多线程下载
   *
   * @param threadNum 线程数
   * @param fileLength 文件大小（用于确认每个线程下载多少东西）
   * @param sourceFilePath 源文件目录
   * @param targetFilePath 目标文件目录
   */
  public static void startThread(
      int threadNum, long fileLength, String sourceFilePath, String targetFilePath) {
    System.out.println("================");
    long modLength = fileLength % threadNum;
    long targetLength = fileLength / threadNum;
    for (int i = 0; i < threadNum; i++) {
      System.out.println((targetLength * i) + "-----" + (targetLength * (i + 1)));
      new FileWriteThread(
              (targetLength * i), (targetLength * (i + 1)), sourceFilePath, targetFilePath)
          .start();
    }
    if (modLength != 0) {
      new FileWriteThread((targetLength * 4), modLength, sourceFilePath, targetFilePath).start();
    }
  }

  /** 写线程：指定文件开始位置、目标位置、源文件、目标文件， */
  static class FileWriteThread extends Thread {
    private long begin;
    private long end;
    private RandomAccessFile sourceFile;
    private RandomAccessFile targetFile;

    public FileWriteThread(long begin, long end, String sourceFilePath, String targetFilePath) {
      this.begin = begin;
      this.end = end;
      try {
        this.sourceFile = new RandomAccessFile(sourceFilePath, "rw");
        this.targetFile = new RandomAccessFile(targetFilePath, "rw");
      } catch (FileNotFoundException e) {
      }
    }

    public void run() {
      try {
        sourceFile.seek(begin);
        targetFile.seek(begin);
        int hasRead = 0;
        byte[] buffer = new byte[1024];
        while (begin < end && -1 != (hasRead = sourceFile.read(buffer))) {
          begin += hasRead;
          targetFile.write(buffer, 0, hasRead);
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          sourceFile.close();
          targetFile.close();
        } catch (Exception e) {
        }
      }
    }
  }
}
