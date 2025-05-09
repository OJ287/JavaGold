package Part11;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author liyanpeng
 * @date 2025/5/9
 * @description TODO
 */

/**
 * ✅Properties
 * java.util.Properties 是 Java 提供的 用于处理配置参数的类，它本质上是一个键值对集合（key-value），键和值都为字符串（String）类型。
 * 主要用于读取和写入配置文件（*.properties），也是国际化资源加载的核心工具之一。
 * 它继承自 java.util.Hashtable<Object, Object>，但通常只使用 String 类型的键和值。
 * 所以是键值对管理的对象
 * ResourceBundle取出来的键值对放进Properties
 * <p>
 * ✅ 方法一览说明表
 * ┌────────────────────────────────────────────────┬──────────────────────────────┬──────────────┬──────────────────────────────────────────────┐
 * │ 方法签名                                       │ 参数说明                        │ 抛出异常      │ 说明                                         │
 * ├────────────────────────────────────────────────┼──────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * │ Object get(Object key)                         │ key: 要查找的键（Object）       │ 无             │ 返回与 key 对应的值（Object 类型），Hashtable提供，返回Object可能不是String │
 * ├────────────────────────────────────────────────┼──────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * │ String getProperty(String key)                 │ key: 要查找的键（String）       │ 无             │ 返回与 key 对应的值，找不到则返回 null，Properties提供，返回String      │
 * ├────────────────────────────────────────────────┼──────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * │ String getProperty(String key, String default) │ key: 键，default: 默认值       │ 无             │ 返回值或默认值，若 key 不存在时返回 default │
 * ├────────────────────────────────────────────────┼────────────────────────────────────┼──────────────┼────────────────────────────────────────────────────────────┤
 * │ Object put(Object key, Object value)           │ key: 键（Object），value: 值（Object） │ 无         │ 添加或替换键值对；键值通常为 String，但理论上可为 Object │
 * └────────────────────────────────────────────────┴──────────────────────────────┴──────────────┴──────────────────────────────────────────────┘
 * ResourceBundle的实装有PropertyResourceBundle和ListResourceBundle
 * 这两个同时存在，并且内容有重复的时候，以先定位到的文件为主。
 * ResourceBundle的搜索优先度的规则
 * 1.语言code和国家code一致的ListResourceBundle (类文件)
 * 2.语言code和国家code一致的PropertyResourceBundle (属性文件)
 * 3.语言code一致的ListResourceBundle (类文件)
 * 4.语言code一致的PropertyResourceBundle (属性文件)
 * 5.默认的的ListResourceBundle (类文件)
 * 6.默认的的PropertyResourceBundle (属性文件)
 */
public class B06_PropertyResourceBundle {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        ResourceBundle bundle
                = ResourceBundle.getBundle("Part11.MyResourcesP", Locale.US);
        Properties props = new Properties();
        bundle.keySet()
                .stream()
                .forEach(k -> props.put(k, bundle.getString(k)));
        method(props);
    }

    static void method(Properties props) {
        System.out.println(props.get("send"));
        System.out.println(props.getProperty("send"));
        System.out.println(props.get("xxx"));
        System.out.println(props.getProperty("xxx"));
        System.out.println(props.getProperty("xxx", "default"));
    }
    /**
     * P_send
     * P_send
     * null
     * null
     * default
     */
}