package Part11;

/**
 * @author liyanpeng
 * @date 2025/5/9
 * @description TODO
 */

/**
 * Resource Bundleリソースバンドル
 * java.util.ResourceBundle
 * |____ListResourceBundle
 * |____PropertyResourceBundle
 * 一个Resource是由键值对构成的
 * <p>
 * ✅ 一、ResourceBundle 的作用与简介
 * ResourceBundle 是 Java 国际化（i18n）机制的核心类，用于根据语言环境（Locale）加载对应的资源数据，常见形式有：
 * .properties 文件（推荐方式）
 * Java 类（继承 ListResourceBundle）
 * .    本质是一个以 key-value（键值对）形式组织的资源集合。
 * <p>
 * <p>
 * ✅ 二、ResourceBundle 两种主要子类的一览表
 * ┌──────────────────────────────┬───────────────────────────────────────────────┬────────────────────────────────────────┐
 * │ 类名                          │ 特征说明                                       │ 使用场景说明                           │
 * ├──────────────────────────────┼───────────────────────────────────────────────┼────────────────────────────────────────┤
 * │ ListResourceBundle           │ 使用 Java 类代码实现资源键值对                    │ 适合逻辑较复杂或动态生成的资源           │
 * │                              │ 通过重写 getContents() 方法返回 Object[][]       │                                      │
 * ├──────────────────────────────┼───────────────────────────────────────────────┼────────────────────────────────────────┤
 * │ PropertyResourceBundle       │ 使用 .properties 文件编写资源键值对               │ 最常用，便于维护和本地化（非程序员友好）│
 * │                              │ 键值对以 UTF-8 或 ISO8859-1 编码保存             │                                      │
 * └──────────────────────────────┴───────────────────────────────────────────────┴────────────────────────────────────────┘
 * <p>
 * ✅ 三、ResourceBundle 使用结构示意图（text 图解）
 * 我们以下面结构为例：
 * resources/
 * ├── Messages.properties
 * ├── Messages_ja.properties
 * ├── Messages_en_US.properties
 * <p>
 * Messages.properties（默认资源）内容如下：
 * greeting = Hello!
 * farewell = Goodbye!
 * <p>
 * Messages_ja.properties：
 * greeting = こんにちは！
 * farewell = さようなら！
 * <p>
 * 对应 Java 加载方式：
 * Locale locale = Locale.JAPAN;
 * ResourceBundle rb = ResourceBundle.getBundle("resources.Messages", locale);
 * System.out.println(rb.getString("greeting"));  // 输出：こんにちは！
 * <p>
 * <p>
 * ✅ 四、ListResourceBundle 示例
 * 加载方式相同，只是读取的是 Java 类而非 .properties 文件。
 * public class Messages_ja extends ListResourceBundle {
 * protected Object[][] getContents() {
 * return new Object[][] {
 * {"greeting", "こんにちは！"},
 * {"farewell", "さようなら！"}
 * };
 * }
 * }
 */
public class B01_ResourceBundle {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
    }
}