package ark_compiler_source_translation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.codeinchinese.源码.功用.代码处理功能;
import com.codeinchinese.源码.翻译.重命名行;

class 标识符重命名Test {

  @Test
  void 行内重命名() {
    // 单个标识符
    assertEquals("class 编译器类 {",
        代码处理功能.重命名("class Compiler {",
            "Compiler", "编译器类").结果);

    // 包含标识符
    assertEquals("class Jbc2MplCompiler : public 编译器类 {",
        代码处理功能.重命名("class Jbc2MplCompiler : public Compiler {",
            "Compiler", "编译器类").结果);
    
    assertEquals("IsUnaryNode",
        代码处理功能.重命名("IsUnaryNode",
            "UnaryNode", "一元节点类").结果);
    
    assertEquals("#include \"default/O0_options_jbc2mpl.def\"",
        代码处理功能.重命名("#include \"default/O0_options_jbc2mpl.def\"",
            "options", "选项").结果);
    
    assertEquals("   错误(kLncErr, \"syntax error at file %s line %d\", filename, lineno);",
        代码处理功能.重命名("   ERR(kLncErr, \"syntax error at file %s line %d\", filename, lineno);",
            "ERR", "错误").结果);
    
    // 多个同名标识符
    assertEquals("  explicit 编译器类(const std::string &名称) : 名称(名称) {}",
        代码处理功能.重命名("  explicit 编译器类(const std::string &name) : name(name) {}",
            "name", "名称").结果);

    // 大小写敏感
    assertEquals("  const int 运行(const 选项类 &MplOptions, const std::string &普通选项) const;",
        代码处理功能.重命名("  const int 运行(const 选项类 &MplOptions, const std::string &options) const;",
            "options", "普通选项").结果);

    // 无视//打头的行
    assertEquals("// options",
        代码处理功能.重命名("// options",
            "options", "普通选项").结果);

    // 无视//之后部分
    assertEquals("普通选项// options",
        代码处理功能.重命名("options// options",
            "options", "普通选项").结果);

    // 无视 /* */之间部分
    assertEquals("/* options */",
        代码处理功能.重命名("/* options */",
            "options", "普通选项").结果);

    // 无视 /* 之后
    重命名行 翻译 = 代码处理功能.重命名("/* options",
        "options", "普通选项");
    assertEquals("/* options", 翻译.结果);
    assertTrue(翻译.注释开始);
    assertFalse(翻译.注释结束);

    // 无视 */ 之前
    翻译 = 代码处理功能.重命名("* options */",
        "options", "普通选项");
    assertEquals("* options */", 翻译.结果);
    assertFalse(翻译.注释开始);
    assertTrue(翻译.注释结束);

    // TODO: 暂不处理这样的注释:
    // 开头注释 */ 代码部分 /* 末尾注释
    assertEquals("    CHECK_FATAL(false, \"Candidates not found for function %s\", callee->求名称().c_str());",
        代码处理功能.重命名("    CHECK_FATAL(false, \"Candidates not found for function %s\", callee->GetName().c_str());",
            "GetName", "求名称").结果);
  }

  @Test
  void 块重命名() {
    assertEquals(
        Arrays.asList("class 编译器类 {\n", " public:\n",
            "  explicit 编译器类(const std::string &名称) : 名称(名称) {}\n",
            "\n",
            "  virtual ~编译器类() {}"),
        代码处理功能.块重命名(Arrays.asList("class Compiler {\n", " public:\n",
            "  explicit Compiler(const std::string &名称) : 名称(名称) {}\n",
            "\n",
            "  virtual ~Compiler() {}"
            ),
            "Compiler", "编译器类"));

    // 忽略单行注释
    assertEquals(
        Arrays.asList("// Compiler"),
        代码处理功能.块重命名(Arrays.asList("// Compiler"),
            "Compiler", "编译器类"));

    // 跳过单行注释
    assertEquals(
        Arrays.asList("// Compiler",
            "class 编译器类 {\n", " public:\n"),
        代码处理功能.块重命名(Arrays.asList("// Compiler",
            "class Compiler {\n", " public:\n"),
            "Compiler", "编译器类"));

    // 跳过多行注释
    assertEquals(
        Arrays.asList("/* Compiler",
            "* Compiler",
            "* Compiler */",
            "class 编译器类 {\n", " public:\n"),
        代码处理功能.块重命名(Arrays.asList("/* Compiler",
            "* Compiler",
            "* Compiler */",
            "class Compiler {\n", " public:\n"),
            "Compiler", "编译器类"));
  }

}
