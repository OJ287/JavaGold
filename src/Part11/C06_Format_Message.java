package Part11;

/**
 * @author liyanpeng
 * @date 2025/5/10
 * @description TODO
 */

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ✅ 一、MessageFormat 的介绍与用途
 * MessageFormat 是 Java 提供的格式化类，用于将带参数的字符串模板与实际值进行组合，特别适合国际化场景下的多语言格式化输出。
 * <p>
 * 可以使用new做成对象，构造函数传入pattern，生成的对象可以重复使用各种相设置的参数。
 * <p>
 * 特点：
 * 可插入多个动态参数（{0}、{1}…）
 * 可与 ResourceBundle 结合使用实现多语言格式化
 * 支持数字、日期、时间等格式指定（如 {0,number}、{1,date}）
 * <p>
 * ✅ 二、构造方法与常用方法一览表
 * ┌──────────────────────────────────────────┬─────────────────────────────┬──────────────┬────────────────────────────────────────────┐
 * │ 方法签名                                 │ 参数说明                    │ 抛出异常     │ 说明                                       │
 * ├──────────────────────────────────────────┼─────────────────────────────┼──────────────┼────────────────────────────────────────────┤
 * │ MessageFormat(String pattern)            │ pattern：模板字符串          │ 无           │ 使用指定模板创建对象                        │
 * │ MessageFormat.format(String pattern,     │ pattern, args               │ 无           │ 静态方法，快速格式化                        │
 * │    Object... arguments)                  │                             │              │                                            │
 * │ public String format(Object[] arguments) │ arguments：参数值数组        │ 无           │ 格式化为字符串                              │
 * │ public void applyPattern(String pattern) │ pattern：模板字符串          │ 无           │ 重新设定格式模板                            │
 * │ public String toPattern()                │ 无                          │ 无           │ 返回当前格式的 pattern 字符串               │
 * └──────────────────────────────────────────┴─────────────────────────────┴──────────────┴────────────────────────────────────────────┘
 * <p>
 * <p>
 * <p>
 * ✅ 三、MessageFormat 模板格式说明
 * ┌──────────────┬──────────────────────────────┐
 * │ 模板占位符   │ 含义                         │
 * ├──────────────┼──────────────────────────────┤
 * │ {0}          │ 第一个参数（Object[0]）       │
 * │ {1,number}   │ 第二个参数，以数字方式输出     │
 * │ {2,date}     │ 第三个参数，以默认日期格式输出 │
 * │ {3,time}     │ 第四个参数，以默认时间格式输出 │
 * └──────────────┴──────────────────────────────┘
 * <p>
 * <p>
 * ✅ 四、与 ResourceBundle 的结合使用方式
 * 在国际化场景中，通常使用 ResourceBundle 管理多语言文案，再用 MessageFormat 插入参数：
 * <p>
 * 1. 示例资源文件（messages.properties）
 * greeting=こんにちは、{0}さん！今日は{1,date}です。
 * 2. Java 程序示例：
 * Locale locale = Locale.JAPAN;
 * ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
 * <p>
 * String pattern = bundle.getString("greeting");
 * <p>
 * Object[] params = { "山田", new Date() };
 * String result = MessageFormat.format(pattern, params);
 * <p>
 * System.out.println(result);
 * // 输出例：こんにちは、山田さん！今日は2025/05/10です。
 */
public class C06_Format_Message {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        String template = "こんにちは{0}さん。私は{1}です。今年は{2,number}歳です。今日は{3,date}です。";
        Object[] params = {"Duke", "Taro", 99, new Date()};// 99 :AutoBoxing成Integer
        String msg = MessageFormat.format(template, params);
        System.out.println(msg);
        /**
         * こんにちはDukeさん。私はTaroです。！今日は2025/05/10です。
         */


        String template1 = "こんにちは{0}さん。私は{1}です。";
        Object[][] params1 = {{"Duke", "Taro"},
                {"Nao", "misa"}};
        MessageFormat mf = new MessageFormat(template1);
        for (Object[] p : params1) {
            System.out.println(mf.format(p));
        }
        /**
         * こんにちはDukeさん。私はTaroです。
         * こんにちはNaoさん。私はmisaです。
         */


        ResourceBundle bundle
                = ResourceBundle.getBundle("Part11.MyResourcesP", Locale.US);
        //Object[] params = {"Duke"};
        String params2 = "Duke";
        String msg2 = MessageFormat.format(bundle.getString("message"), params2);
        System.out.println(msg2);
    }
}