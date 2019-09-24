package com.codeinchinese.源码.翻译;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.codeinchinese.源码.功用.代码处理功能;
import com.codeinchinese.源码.功用.文件功能;

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
      List<String> 翻译后代码 = 文件功能.取行(目录);
      for (Entry<String, String> 词条 : 字典.entrySet()) {
        翻译后代码 = 代码处理功能.块重命名(翻译后代码, 词条.getKey(), 词条.getValue());
      }
      文件功能.写行入文件(翻译后代码, 目标文件路径);
    }
    return;
  }
}
