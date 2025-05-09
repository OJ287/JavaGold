package Part11;

/**
 * @author liyanpeng
 * @date 2025/5/9
 * @description TODO
 */

/**
 * ✅ 一、ListResourceBundle 介绍与用途
 * ListResourceBundle 是 ResourceBundle 的一个抽象子类，用于通过 Java 类以代码方式定义国际化资源。
 * 与 .properties 文件相比，它的资源以 Java 数组的形式写在类中，适用于资源值中含有对象、结构数据或复杂逻辑的情况。
 * <p>
 * <p>
 * ✅ 二、ListResourceBundle 的定义规则
 * 类必须是 public 且无参构造。
 * 必须实现并重写 protected Object[][] getContents() 方法，返回键值对数组。
 * Resource是以key和value作为要素的数组做成的
 * 类名必须包含基础名 + Locale 后缀，如 Messages_ja, Messages_en_US 等。
 * 基础名（例）：Messages
 * 必须继承 java.util.ListResourceBundle。
 * 编译后需放在类路径下，包名需与 ResourceBundle.getBundle() 中一致。
 * <p>
 * 基础名：
 * ・MyResources
 * 　　　只是基础名的时候，被默认为是默认情况デフォルトロール
 * ・MyResources_en
 * 基础名加上语言code的时候，那就是指定了语言code的ロール，读取此文件
 * ・MyResources_en_US
 * 基础名加上语言code再加上国家code的时候，那就是指定了语言code以及国家code的ロール，读取此文件
 * <p>
 * <p>
 * <p>
 * ✅ 三、getContents() 方法说明一览表
 * ┌────────────────────────────────────────────┬────────────────────────────┬──────────────┬──────────────────────────────────────────────┐
 * │ 方法签名                                   │ 参数说明                   │ 抛出异常     │ 说明                                         │
 * ├────────────────────────────────────────────┼────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * │ protected Object[][] getContents()         │ 无                         │ 无           │ 返回 key-value 对应的 Object 二维数组         │
 * │                                            │                            │              │ 每行一个键值对，key 必须是 String 类型         │
 * └────────────────────────────────────────────┴────────────────────────────┴──────────────┴──────────────────────────────────────────────┘
 * <p>
 * ✅ 四、图解资源内容
 * 🎌 デフォルトロケール用（例：Messages_ja.java）
 * ┌────────────┬────────────┐
 * │    key     │   value    │
 * ├────────────┼────────────┤
 * │   send     │   送信     │
 * │  cancel    │   取消     │
 * └────────────┴────────────┘
 * <p>
 * 🇺🇸 アメリカ用（例：Messages_en_US.java）
 * ┌────────────┬────────────┐
 * │    key     │   value    │
 * ├────────────┼────────────┤
 * │   send     │   send     │
 * │  cancel    │   cancel   │
 * └────────────┴────────────┘
 * <p>
 * ✅使用例
 * MyResources
 * MyResources_en_US
 */
public class B02_ListResourceBundle {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
    }
}