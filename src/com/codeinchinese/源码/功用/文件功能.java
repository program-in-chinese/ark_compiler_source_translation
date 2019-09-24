package com.codeinchinese.源码.功用;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class 文件功能 {

  public static List<String> 取行(File 文件) throws Exception {
    List<String> 行 = new ArrayList<>();
    // StandardCharsets.ISO_8859_1
    Files.lines(文件.toPath()).forEach(new Consumer<String>() {

      @Override
      public void accept(String 文本) {
        行.add(文本);
      }

    });
    return 行;
  }

  public static void 写行入文件(Collection<String> 行, String 文件名) {
    try {
      File 文件 = new File(文件名);
      文件.getParentFile().mkdirs();
      BufferedWriter 输出 = new BufferedWriter(new FileWriter(文件));
      for (String 某行 : 行) {
        输出.write(某行 + "\n");
      }
      输出.close();
    } catch (IOException e) {
    }
  }
}
