package ark_compiler_source_translation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.codeinchinese.源码.翻译.功用;

class 标识符重命名Test {

  @Test
  void 行内重命名() {
    // 单个标识符
    assertEquals("class 编译器类 {",
        功用.重命名("class Compiler {",
            "Compiler", "编译器类"));

    // 多个同名标识符
    assertEquals("  explicit 编译器类(const std::string &名称) : 名称(名称) {}",
        功用.重命名("  explicit 编译器类(const std::string &name) : name(name) {}",
            "name", "名称"));

    // 大小写敏感
    assertEquals("  const int 运行(const 选项类 &MplOptions, const std::string &普通选项) const;",
        功用.重命名("  const int 运行(const 选项类 &MplOptions, const std::string &options) const;",
            "options", "普通选项"));

    // 无视//打头的行
    assertEquals("// options",
        功用.重命名("// options",
            "options", "普通选项"));

    // 无视//之后部分
    assertEquals("普通选项// options",
        功用.重命名("options// options",
            "options", "普通选项"));
  }

  @Test
  void 块重命名() {
    assertEquals(
        Arrays.asList("class 编译器类 {\n", " public:\n",
            "  explicit 编译器类(const std::string &名称) : 名称(名称) {}\n",
            "\n",
            "  virtual ~编译器类() {}"),
        功用.块重命名(Arrays.asList("class Compiler {\n", " public:\n",
            "  explicit Compiler(const std::string &名称) : 名称(名称) {}\n",
            "\n",
            "  virtual ~Compiler() {}"
            ),
            "Compiler", "编译器类"));
  }

}
