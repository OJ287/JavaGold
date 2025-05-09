package Part11;

/**
 * @author liyanpeng
 * @date 2025/5/9
 * @description TODO
 */

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ✅ListResourceBundle
 * ✅ 一、ResourceBundle.getBundle() 方法一览表
 * ┌────────────────────────────────────────────────────────────┬────────────────────────────────────┬──────────────┬────────────────────────────────────────────────────────┐
 * │ 方法签名                                                   │ 参数说明                           │ 抛出异常     │ 说明                                                       │
 * ├────────────────────────────────────────────────────────────┼────────────────────────────────────┼──────────────┼────────────────────────────────────────────────────────┤
 * │ public static ResourceBundle getBundle(String baseName)    │ baseName: 基础资源名（不含扩展名）.  │ MissingResourceException │ 使用默认 Locale 加载资源 ,只是基础名的文件        │
 * ├────────────────────────────────────────────────────────────┼────────────────────────────────────┼──────────────┼────────────────────────────────────────────────────────┤
 * │ public static ResourceBundle getBundle(String baseName,    │ baseName: 基础资源名                │ MissingResourceException │ 根据指定的 Locale 加载对应资源（回退机制适用）                │
 * │                                    Locale locale)          │ locale: 目标 Locale                │              │ 如找不到 messages_ja_JP，则依次尝试 messages_ja、messages 等 │
 * └────────────────────────────────────────────────────────────┴────────────────────────────────────┴──────────────┴────────────────────────────────────────────────────────┘
 * <p>
 * <p>
 * ✅ 二、资源包被 package 化时的 baseName 指定方式
 * 资源类或 .properties 文件如果放在某个 package 下，例如：
 * Part11.MyResources.java
 * Part11.MyResources_en_US.java
 * 那么 baseName 需要使用完全限定名（fully-qualified name），即：
 * .   ResourceBundle.getBundle("Part11.MyResources");
 * <p>
 * ✅ 三、根据 Locale 切换加载资源的使用方式
 * <p>
 * <p>
 * ✅ 四、ResourceBundle 的资源检索方法一览表
 * ┌────────────────────────────────────────────┬────────────────────────────┬──────────────┬──────────────────────────────────────────────┐
 * │ 方法签名                                   │ 参数说明                   │ 抛出异常     │ 说明                                         │
 * ├────────────────────────────────────────────┼────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * │ public boolean containsKey(String key)     │ key: 键字符串               │ 无           │ 是否包含指定键，true: 存在, false: 不存在     │
 * ├────────────────────────────────────────────┼────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * │ public Object getObject(String key)        │ key: 键字符串               │ MissingResourceException │ 获取资源的值（Object 类型）        │
 * ├────────────────────────────────────────────┼────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * │ public String[] getStringArray(String key) │ key: 键字符串               │ ClassCastException,     │ 获取字符串数组类型的资源值                    │
 * │                                            │                            │ MissingResourceException│ 键不存在或类型错误时抛异常                     │
 * ├────────────────────────────────────────────┼────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * │ public Set<String> keySet()                │ 无                         │ 无           │ 返回该资源包中所有键的 Set<String> 集合       │
 * └────────────────────────────────────────────┴────────────────────────────┴──────────────┴──────────────────────────────────────────────┘
 */
public class B03_ListResourceBundle {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        Locale japan = Locale.getDefault(); // デフォルト（日本）
        Locale us = Locale.US;            // 米国
        Locale[] locArray = {japan, us};
        for (Locale locale : locArray) {
            ResourceBundle obj1
                    = ResourceBundle.getBundle("Part11.MyResources", locale);
            System.out.println("send   : " + obj1.getString("send"));
            System.out.println("cancel : " + obj1.getString("cancel"));
        }
        ResourceBundle obj2
                = ResourceBundle.getBundle("Part11.MyResources");
        System.out.println("検証用 : " + obj2.getString("send"));
        /**
         * send   : 送信
         * cancel : 取消
         * send   : send
         * cancel : cancel
         * 検証用 : 送信
         */


        // ✅ 三、根据 Locale 切换加载资源的使用方式
        // 指定 locale
        Locale locale = Locale.JAPAN; // 或 new Locale("ja", "JP");

        // 加载资源包
        ResourceBundle bundle = ResourceBundle.getBundle("Part11.MyResources", locale);
        // 若指定 Locale 找不到对应资源，会回退到默认资源（例如无 _ja_JP 时会查找 _ja、然后默认）
        // 默认 Locale 可通过 Locale.getDefault() 获取，也可使用 Locale.setDefault(...) 设置

        // 使用资源
        System.out.println(bundle.getString("send"));   // → 送信
        System.out.println(bundle.getString("cancel")); // → 取消
        /**
         * 送信
         * 取消
         */
    }
}