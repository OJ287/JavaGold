package Part11;

/**
 * @author liyanpeng
 * @date 2025/5/9
 * @description TODO
 */

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * 比起ListResourceBundle要做class文件进行配置
 * PropertyResourceBundle只需要做成text形式的Property文件就可以
 * PropertyResourceBundle 是 java.util.ResourceBundle 的一个子类，用于从 .properties 格式的文件中加载本地化资源。
 * <p>
 * ✅Property文件的做成规则
 * 1，Property文件名和ListResourceBundle一样是由基础名，语言code，国家code组成的
 * 2，文件形式是.properties
 * 3，Property文件需要配置在ClassPath路径下
 * 4，Resource的键值对，在Property文件中是「key = value」的形式列记的，	纯文本，使用 key=value 格式
 * 编码建议，Java 8 默认 ISO-8859-1，推荐使用 UTF-8 并配合 native2ascii 或 Java 9+
 * 如果是以Shift-JIS做成的文件，PropertyResourceBundle是读取不了的需要换成ISO-8859-1可以读取的Unicode
 * 变换命令：使用native2ascii     ：native2ascii MyResources.properties_sjis MyResourcesP.properties
 */
public class B05_PropertyResourceBundle {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        Locale japan = Locale.getDefault(); // デフォルト（日本）
        Locale us = new Locale("en", "US"); // 米国
        Locale[] locArray = {japan, us};
        for (Locale locale : locArray) {
            ResourceBundle obj1
                    = ResourceBundle.getBundle("Part11.MyResourcesP", locale);
            // MyResourcesP应该放在src文件夹（ClassPath）下的，这样这里就只指定Property文件名就可以了
            // 就不用像ListResourceBundle一样指定Class名了
            System.out.println("send   : " + obj1.getString("send"));
            System.out.println("cancel : " + obj1.getString("cancel"));
        }
        /**
         * send   : P_送信
         * cancel : P_取消
         * send   : P_send
         * cancel : P_cancel
         */


        ResourceBundle bundle
                = ResourceBundle.getBundle("Part11.MyResourcesP", Locale.US);
        Set<String> keys = bundle.keySet();
        keys.stream()
                .map(k -> bundle.getString(k))
                .forEach(System.out::println);
        /**
         * P_cancel
         * P_send
         */
    }
}