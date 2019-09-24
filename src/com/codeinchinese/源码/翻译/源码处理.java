package com.codeinchinese.源码.翻译;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class 源码处理 {
  
  //"/Users/xuanwu/git/OpenArkCompiler_official/src"
  private final static String 源码路径 = "/Users/xuanwu/work/program-in-chinese/ark_compiler_source_translation/test/资源/源码目录";;
  private final static String 翻译路径 = "/Users/xuanwu/work/program-in-chinese/ark_compiler_source_translation/test/资源/翻译目录";
  private final static Map<String, String> 字典 = new LinkedHashMap<>();
  
  static {
    字典.put("PrintErrorMessage", "报错");
    字典.put("ret", "返回");
    字典.put("mplOptions", "选项");
    //字典.put("Compiler", "编译器类");
  }
  
  public static void main(String[] args) throws Exception {
    File 源码目录 = new File(源码路径);
    遍历(源码目录);
  }
  
  private static void 遍历(File 目录) throws Exception {
    String 相对路径 = 目录.getAbsolutePath().substring(源码路径.length());
    System.out.println(相对路径);
    if (目录.isDirectory()) {
      if (目录.getName().equals("bin") || 目录.getName().equals("deplibs")) {
        return;
      }
      for (File 子目录 : 目录.listFiles()) {
        遍历(子目录);
      }
    } else if (目录.isFile()) {
      String 名称 = 目录.getName();
      if (名称.equals("BUILD.gn") || 名称.equals("Makefile")) {
        return;
      }
      String 目标文件路径 = 翻译路径 + 相对路径;
      System.out.println("翻译到: " + 目标文件路径);
      List<String> 翻译后代码 = 取行(目录);
      for (Entry<String, String> 词条 : 字典.entrySet()) {
        翻译后代码 = 功用.块重命名(翻译后代码, 词条.getKey(), 词条.getValue());
      }
      写行入文件(翻译后代码, 目标文件路径);
    }
    return;
  }

  private static List<String> 取行(File 文件) throws Exception {
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
