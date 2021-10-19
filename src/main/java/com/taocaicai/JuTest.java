package com.taocaicai;

import org.openjdk.jol.info.ClassLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

public class JuTest {

  public static void main(String[] args) throws IOException {
    final Object object = new Object();

    System.out.println(ClassLayout.parseInstance(object).toPrintable());
    synchronized (object) {
      System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }
    System.out.println(ClassLayout.parseInstance(object).toPrintable());
  }
}
