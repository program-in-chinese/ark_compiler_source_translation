package com.codeinchinese.源码.功用;

import java.util.ArrayList;
import java.util.List;

import com.codeinchinese.源码.翻译.重命名行;

public class 代码处理功能 {

  private static final String 单行注释 = "//";
  private static final String 块注释开头 = "/*";
  private static final String 块注释结尾 = "*/";
  private static final String 字符串标志 = "\"";

  public static 重命名行 重命名(String 代码行, String 英文, String 中文) {
    String 注释 = "";
    String 清理后 = 代码行;
    if (代码行.contains(单行注释)) {
      int 注释位置 = 代码行.indexOf(单行注释);
      注释 = 代码行.substring(注释位置, 代码行.length());
      清理后 = 代码行.substring(0, 注释位置);
      重命名行 结果 = new 重命名行(标识符重命名(清理后, 英文, 中文) + 注释);
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
      重命名行 结果 = new 重命名行(标识符重命名(开头, 英文, 中文) + 注释 + 标识符重命名(结尾, 英文, 中文));
      结果.标记注释(注释开始 > -1, 注释结束 > 注释开始);
      return 结果;
    } else {
      重命名行 结果 = new 重命名行(标识符重命名(代码行, 英文, 中文));
      return 结果;
    }
  }

  public static String 标识符重命名(String 非注释代码, String 英文, String 中文) {
    int 当前位置 = 0;
    String 翻译后代码 = "";
    String 非标识符字符 = "[^0-9a-zA-Z_]";
    do {
      int 词位置 = 非注释代码.indexOf(英文, 当前位置);
      if (词位置 == -1) {
        翻译后代码 += 非注释代码.substring(当前位置, 非注释代码.length());
        break;
      } else {
        翻译后代码 += 非注释代码.substring(当前位置, 词位置);

        // 如果词在引号中间, 则忽略
        int 前引号位置 = 非注释代码.indexOf(字符串标志);
        if (前引号位置 > -1) {
          // System.out.println("前引号位置: " + 前引号位置);
          if (前引号位置 < 词位置 - 1) {
            // 权宜: 如果中间还有引号, 则仍翻译
            int 后引号位置 = 非注释代码.indexOf(字符串标志, 前引号位置 + 1);
            // System.out.println("检查后引号位置: " + 后引号位置);
            if (后引号位置 == -1) {
              翻译后代码 += 英文;
              当前位置 = 词位置 + 英文.length();
              continue;
            } else if (后引号位置 > 词位置){
              翻译后代码 += 英文;
              当前位置 = 词位置 + 英文.length();
              continue;
            }
          } else if (前引号位置 == 词位置 - 1) {
            int 后引号位置 = 非注释代码.indexOf(字符串标志, 词位置 + 英文.length() + 1);
            // System.out.println("后引号位置: " + 后引号位置);
            // 如果紧接着, 翻译
            if (后引号位置 > 0) {
              翻译后代码 += 英文;
              当前位置 = 词位置 + 英文.length();
              continue;
            }
          }
        }
          
        int 词结束位置 = 词位置 + 英文.length();
        if (词位置 == 0) {
          if (非注释代码.length() > 词结束位置) {
            if (非注释代码.substring(词结束位置, 词结束位置 + 1).matches(非标识符字符)) {
              翻译后代码 += 中文;
            } else {
              翻译后代码 += 英文;
            }
          } else {
            翻译后代码 += 中文;
          }
        } else if (非注释代码.substring(词位置 - 1, 词位置).matches(非标识符字符)){
          if (非注释代码.length() > 词结束位置) {
            if (非注释代码.substring(词结束位置, 词结束位置 + 1).matches(非标识符字符)) {
              翻译后代码 += 中文;
            } else {
              翻译后代码 += 英文;
            }
          } else {
            翻译后代码 += 中文;
          }
        } else {
          翻译后代码 += 英文;
        }
        当前位置 = 词位置 + 英文.length();
      }
    } while (当前位置 < 非注释代码.length());
    return 翻译后代码;
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
