package com.codeinchinese.源码.翻译;

public class 重命名行 {

  public String 结果;
  public boolean 注释开始 = false;
  public boolean 注释结束 = false;

  public 重命名行(String 结果) {
    this.结果 = 结果;
  }

  public void 标记注释(boolean 开始, boolean 结束) {
    this.注释开始 = 开始;
    this.注释结束 = 结束;
  }
}
