package ark_compiler_source_translation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.codeinchinese.源码.翻译.功用;

class 标识符重命名Test {

  @Test
  void 重命名() {
    assertEquals("class 编译器类 {", 功用.重命名("class Compiler {", "Compiler", "编译器类"));
  }

}
