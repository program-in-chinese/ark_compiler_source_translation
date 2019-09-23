package com.codeinchinese.源码.翻译;

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

}
