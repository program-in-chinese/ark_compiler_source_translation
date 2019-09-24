package com.codeinchinese.源码.翻译;

import java.util.ArrayList;
import java.util.List;

public class 功用 {

  private static final String 单行注释 = "//";
  private static final String 块注释开头 = "/*";
  private static final String 块注释结尾 = "*/";

  public static 重命名行 重命名(String 代码行, String 英文, String 中文) {
    String 注释 = "";
    String 清理后 = 代码行;
    if (代码行.contains(单行注释)) {
      int 注释位置 = 代码行.indexOf(单行注释);
      注释 = 代码行.substring(注释位置, 代码行.length());
      清理后 = 代码行.substring(0, 注释位置);
      重命名行 结果 = new 重命名行(清理后.replace(英文, 中文) + 注释);
      结果.标记注释(true, true);
      return 结果;
    } else if (代码行.contains(块注释开头) || 代码行.contains(块注释结尾)) {
      String 开头 = 代码行;
      String 结尾 = "";
      int 注释开始 = 代码行.indexOf(块注释开头);
      int 注释结束 = 代码行.indexOf(块注释结尾);
      if (注释开始 == -1 ) {
        开头 = "";
        注释 = 代码行.substring(0, 注释结束);
        结尾 = 代码行.substring(注释结束, 代码行.length());
      } else {
        开头 = 代码行.substring(0, 注释开始);
        if (注释结束 == -1) {
          注释 = 代码行.substring(注释开始, 代码行.length());
        } else {
          注释 = 代码行.substring(注释开始, 注释结束);
          结尾 = 代码行.substring(注释结束, 代码行.length());
        }
      }
      重命名行 结果 = new 重命名行(开头.replace(英文, 中文) + 注释 + 结尾.replace(英文, 中文));
      结果.标记注释(注释开始 > -1, 注释结束 > 注释开始);
      return 结果;
    } else {
      重命名行 结果 = new 重命名行(代码行.replace(英文, 中文));
      return 结果;
    }
  }

  public static List<String> 块重命名(List<String> 代码块, String 英文, String 中文) {
    List<String> 翻译后 = new ArrayList<>();
    boolean 在块注释内 = false;
    for (String 行 : 代码块) {
      重命名行 翻译 = 重命名(行, 英文, 中文);
      if (翻译.注释开始 && !翻译.注释结束) {
        在块注释内 = true;
      } else if (翻译.注释结束 && !翻译.注释开始) {
        在块注释内 = false;
      }
      if (!在块注释内) {
        翻译后.add(翻译.结果);
      } else {
        翻译后.add(行);
      }
    }
    return 翻译后;
  }

}
