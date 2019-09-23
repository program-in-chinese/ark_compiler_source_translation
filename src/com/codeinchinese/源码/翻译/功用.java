package com.codeinchinese.源码.翻译;

import java.util.ArrayList;
import java.util.List;

public class 功用 {

  public static String 重命名(String 代码行, String 英文, String 中文) {
    String 注释 = "";
    String 清理后 = 代码行;
    if (代码行.contains("//")) {
      int 注释位置 = 代码行.indexOf("//");
      注释 = 代码行.substring(注释位置, 代码行.length());
      清理后 = 代码行.substring(0, 注释位置);
    }
    return 清理后.replace(英文, 中文) + 注释;
  }

  public static List<String> 块重命名(List<String> 代码块, String 英文, String 中文) {
    List<String> 翻译后 = new ArrayList<>();
    for (String 行 : 代码块) {
      翻译后.add(重命名(行, 英文, 中文));
    }
    return 翻译后;
  }

}
