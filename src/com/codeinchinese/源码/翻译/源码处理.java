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
  private final static String 源码路径 = "/Users/xuanwu/git/OpenArkCompiler_official/src";
  private final static String 翻译路径 = "/Users/xuanwu/git/OpenArkCompiler_cn/src";
  private final static Map<String, String> 字典 = new LinkedHashMap<>();
  
  // 仅在某些目录下翻译的标识符
  private final static Map<String, String> 限定范围 = new LinkedHashMap<>();
  
  static {
    限定范围.put("name", "maple_driver");
    // 字符串常量
    字典.put("Error Exit!", "因错误退出!");
    字典.put("Invalid Parameter!", "非法参数!");
    字典.put("Init Fail!", "初始化失败!");
    字典.put("File Not Found!", "文件未找到!");
    字典.put("Tool Not Found!", "工具未找到!");
    字典.put("Compile Fail!", "编译失败!");   // 因为在Compile之前, 因此会先被替换. 这不能保证
    字典.put("Not Implement!", "未实现!");
    
    字典.put("PrintErrorMessage", "报错");
    字典.put("mplOptions", "选项");
    字典.put("kLncErr", "错误日志");
    字典.put("ERR", "错误");
    字典.put("kErrorNoError", "无误");
    字典.put("MplOptions", "选项类");
    字典.put("ErrorCode", "错误码");
    字典.put("kErrorExit", "退出");
    字典.put("kErrorExitHelp", "退出帮助");
    字典.put("kErrorInvalidParameter", "非法参数");
    字典.put("kErrorInitFail", "初始化失败");
    字典.put("kErrorFileNotFound", "文件未找到");
    字典.put("kErrorToolNotFound", "工具未找到");
    字典.put("kErrorCompileFail", "编译失败");
    字典.put("kErrorNotImplement", "未实现");
    字典.put("Parse", "分析");
    字典.put("CompilerFactory", "编译器工厂类");
    字典.put("GetInstance", "取个例");
    字典.put("Compile", "编译");
    
    // 节点类
    字典.put("BaseNode", "基础节点类");
    字典.put("UnaryNode", "一元节点类");
    字典.put("TypeCvtNode", "类型转换节点类");
    字典.put("RetypeNode", "类型重置节点类");
    字典.put("ExtractbitsNode", "比特提取节点类");
    字典.put("GCMallocNode", "自动回收内存分配节点类");
    字典.put("JarrayMallocNode", "Java数组内存分配节点类");
    字典.put("DassignNode", "直接赋值节点类");
    字典.put("DreadNode", "直接读取节点类");
    字典.put("IassignNode", "间接赋值节点类");
    字典.put("IreadNode", "间接读取节点类");
    字典.put("IassignoffNode", "带偏移间接赋值节点类");
    字典.put("BlockNode", "块节点类");
    字典.put("WhileStmtNode", "While声明节点类");
    字典.put("IfStmtNode", "If声明节点类");
    字典.put("SwitchNode", "Switch节点类");
    字典.put("MultiwayNode", "多路节点类");
    字典.put("ForeachelemNode", "遍历元素节点类");
    字典.put("CommentNode", "注释节点类");
    字典.put("UnaryStmtNode", "一元声明节点类");
    字典.put("BinaryStmtNode", "二元声明节点类");
    字典.put("AddrofNode", "取址节点类");
    字典.put("SizeoftypeNode", "类型大小节点类");
    字典.put("ArrayNode", "数组节点类");
    字典.put("RegassignNode", "寄存器赋值节点类");
    字典.put("GotoNode", "跳转节点类");
    字典.put("CondGotoNode", "条件跳转节点类");
    字典.put("RangegotoNode", "范围跳转节点类");
    字典.put("CallNode", "调用节点类");
    字典.put("IcallNode", "间接调用节点类");
    字典.put("CallinstantNode", "实例化调用节点类");
    字典.put("TernaryNode", "三元节点类");
    字典.put("TryNode", "Try节点类");
    字典.put("CatchNode", "Catch节点类");
    字典.put("LabelNode", "标记节点类");
    字典.put("IreadoffNode", "带偏移间接读取节点类");
    字典.put("RegreadNode", "寄存器读取节点类");
    字典.put("AddroflabelNode", "标记取址节点类");
    字典.put("AddroffuncNode", "函数取址节点类");
    字典.put("BinaryNode", "二元节点类");
    字典.put("CompareNode", "比较节点类");
    字典.put("DepositbitsNode", "比特放置节点类");
    字典.put("ConstvalNode", "常量值节点类");
    字典.put("ConststrNode", "常量字符串节点类");
    字典.put("Conststr16Node", "常量16字符串节点类");
    字典.put("DoloopNode", "循环节点类");
    字典.put("IassignFPoffNode", "帧址带偏移间接赋值节点类 ");
    字典.put("IreadFPoffNode", "帧址带偏移间接读取节点类");
    字典.put("ParseInput", "解析输入");
    字典.put("ParseMIR", "解析内部表示");
    字典.put("MIRParser", "内部表示解析器类");
    字典.put("MapleCombCompiler", "联合编译器类");
    
    字典.put("GetInputFileName", "取输入文件名");
    字典.put("Compiler", "编译器类");
    // TODO: 与mplOptions重名时, 需要改为"普通选项"
    字典.put("options", "选项");
    字典.put("MIRModulePtr", "内部表示模块指针");
    字典.put("mplOptions", "选项");
    字典.put("inputFiles", "输入文件");
    字典.put("optionParser", "分析器");
    字典.put("GetName", "求名称");
    字典.put("GetTmpFilesToDelete", "求待删临时文件");
    字典.put("GetFinalOutputs", "求最终输出");
    字典.put("Exe", "运行");
    字典.put("PrintCommand", "显示命令");
    字典.put("tempFiles", "临时文件");

    // TODO: 并未全局替换, 暂时不包括在内
    // 字典.put("ret", "返回");
    
    // 部分替换
    字典.put("name", "名称");
  }
  
  public static void main(String[] args) throws Exception {
    File 源码目录 = new File(源码路径);
    遍历(源码目录);
  }
  
  private static void 遍历(File 目录) throws Exception {
    String 相对路径 = 目录.getAbsolutePath().substring(源码路径.length());
    System.out.println(相对路径);
    if (目录.isDirectory()) {
      String 目录名 = 目录.getName();
      if (目录名.equals("bin") || 目录名.equals("deplibs") || 目录名.equals("third_party")) {
        return;
      }
      for (File 子目录 : 目录.listFiles()) {
        遍历(子目录);
      }
    } else if (目录.isFile()) {
      String 名称 = 目录.getName();
      if (!(名称.contains(".h") || 名称.contains(".cpp"))) {
        return;
      }
      String 目标文件路径 = 翻译路径 + 相对路径;
      System.out.println("翻译到: " + 目标文件路径);
      List<String> 翻译后代码 = 文件功能.取行(目录);
      for (Entry<String, String> 词条 : 字典.entrySet()) {
        String 英文 = 词条.getKey();
        if (限定范围.containsKey(英文) && !(目录.getAbsolutePath().contains(限定范围.get(英文)))) {
          continue;
        }
        翻译后代码 = 代码处理功能.块重命名(翻译后代码, 英文, 词条.getValue());
      }
      文件功能.写行入文件(翻译后代码, 目标文件路径);
    }
    return;
  }
}
