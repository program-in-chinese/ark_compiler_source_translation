package ark_compiler_source_translation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.codeinchinese.源码.功用.代码处理功能;

public class 字符串常量Test {

  @Test
  void 行内文本常量() {
    assertEquals("      ERR(kLncErr, \"因错误退出!\");",
        代码处理功能.重命名("      ERR(kLncErr, \"Error Exit!\");", "Error Exit!", "因错误退出!").结果);
  }
}
