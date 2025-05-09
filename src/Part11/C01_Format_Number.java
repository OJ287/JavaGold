package Part11;

/**
 * @author liyanpeng
 * @date 2025/5/10
 * @description TODO
 */

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * java.text.Format 是 Java 提供的一个 抽象类，用于格式化（Format）和解析（Parse）数据。
 * 抽象类所以不能new来获取对象
 * 它是所有格式化类的基类，常用于将数据对象（如数字、日期等）转化为用户可读的字符串，或反过来从字符串解析出数据对象。
 * <p>
 * ✅ Format 的作用
 * 格式化（format）：将对象转成字符串，例如把 Date 对象转成 "2025-05-09"。
 * 解析（parse）：将字符串转成对象，例如把 "2025年5月9日" 转成 Date 对象。
 * <p>
 * <p>
 * ✅ 常用 Format 子类一览表
 * ┌────────────┬──────────────────────────────────────┬────────────────────────────────────────────────────────────────┐
 * │ 分类       │ 类名（含包名）                       │ 说明                                                           │
 * ├────────────┼──────────────────────────────────────┼────────────────────────────────────────────────────────────────┤
 * │ 数值与货币 │ java.text.NumberFormat               │ 抽象类，用于格式化整数、小数、货币、百分比等                   │
 * │            │ java.text.DecimalFormat              │ NumberFormat 的实现类，支持自定义数字格式                      │
 * │            │ java.text.DecimalFormatSymbols       │ 用于自定义 DecimalFormat 中的符号（如小数点、千位分隔符等）   │
 * ├────────────┼──────────────────────────────────────┼────────────────────────────────────────────────────────────────┤
 * │  日期与时间  │ java.time.format.DateTimeFormatter   │ Java 8+ 中的新 API，线程安全且强大，推荐使用                   │
 * │            │ java.text.DateFormat                 │ 抽象类，用于格式化和解析日期时间                               │
 * │            │ java.text.SimpleDateFormat           │ DateFormat 的实现类，支持自定义日期格式字符串                  │
 * ├────────────┼──────────────────────────────────────┼────────────────────────────────────────────────────────────────┤
 * │ 文本消息   │ java.text.MessageFormat              │ 用于格式化带占位符的消息模板，支持参数插入与国际化             │
 * │            │ java.text.ChoiceFormat               │ 与 MessageFormat 搭配使用，可根据数值区间选择文本              │
 * └────────────┴──────────────────────────────────────┴────────────────────────────────────────────────────────────────┘
 * <p>
 * ✅ NumberFormat 的 static 方法一览表（获得实例用）
 * ┌──────────────────────────────────────────────┬──────────────────────────┬──────────────┬────────────────────────────────────────────────────────┐
 * │ 方法签名                                     │ 参数说明                 │ 抛出异常     │ 说明                                                       │
 * ├──────────────────────────────────────────────┼──────────────────────────┼──────────────┼────────────────────────────────────────────────────────┤
 * │ static NumberFormat getInstance()            │ 无                        │ 无           │ 获取当前默认 Locale 的数字格式（含千位，小数）           │
 * │ static NumberFormat getInstance(Locale)      │ Locale                    │ 无           │ 获取指定 Locale 的数字格式                                 │
 * │ static NumberFormat getCurrencyInstance()    │ 无                        │ 无           │ 获取当前默认 Locale 的货币格式（如 ￥、$）               │
 * │ static NumberFormat getCurrencyInstance(Locale)│ Locale                  │ 无           │ 获取指定 Locale 的货币格式                                 │
 * │ static NumberFormat getIntegerInstance()     │ 无                        │ 无           │ 获取当前默认 Locale 的整数格式（不含小数）               │
 * │ static NumberFormat getIntegerInstance(Locale)│ Locale                  │ 无           │ 获取指定 Locale 的整数格式                                 │
 * 下面不是教科书内容
 * │ static NumberFormat getNumberInstance()      │ 无                        │ 无           │ 获取当前默认 Locale 的数字格式（同 getInstance）         │
 * │ static NumberFormat getNumberInstance(Locale)│ Locale                    │ 无           │ 获取指定 Locale 的数字格式                                 │
 * │ static NumberFormat getPercentInstance()     │ 无                        │ 无           │ 获取当前默认 Locale 的百分比格式（如 0.75 → 75%）        │
 * │ static NumberFormat getPercentInstance(Locale)│ Locale                  │ 无           │ 获取指定 Locale 的百分比格式                               │
 * └──────────────────────────────────────────────┴──────────────────────────┴──────────────┴────────────────────────────────────────────────────────┘
 * <p>
 * ✅ NumberFormat 的主要实例方法一览表（含重载）
 * ┌────────────────────────────────────────────┬──────────────────────────┬──────────────┬──────────────────────────────────────────────────────┐
 * │ 方法签名                                   │ 参数说明                 │ 抛出异常     │ 说明                                                     │
 * ├────────────────────────────────────────────┼──────────────────────────┼──────────────┼──────────────────────────────────────────────────────┤
 * │ String format(double number)               │ double                    │ 无           │ 将 double 类型数值格式化为字符串                         │
 * │ String format(long number)                 │ long                      │ 无           │ 将 long 类型数值格式化为字符串                           │
 * │ Number parse(String source)                │ String                    │ ParseException│ 将字符串解析为 Number 对象                              │
 * │ Number parse(String source, ParsePosition) │ String, ParsePosition     │ 无           │ 从指定位置开始解析字符串                                 │
 * 下面不是教科书内容
 * │ void setMaximumFractionDigits(int)         │ 最大小数位数             │ 无           │ 设置小数部分的最大位数                                   │
 * │ void setMinimumFractionDigits(int)         │ 最小小数位数             │ 无           │ 设置小数部分的最小位数                                   │
 * │ void setMaximumIntegerDigits(int)          │ 最大整数位数             │ 无           │ 设置整数部分的最大位数                                   │
 * │ void setMinimumIntegerDigits(int)          │ 最小整数位数             │ 无           │ 设置整数部分的最小位数                                   │
 * │ void setGroupingUsed(boolean)              │ 是否使用分组符（如千位） │ 无           │ 启用/禁用千位分隔符                                       │
 * │ void setParseIntegerOnly(boolean)          │ 是否只解析整数           │ 无           │ 若设置为 true，忽略小数部分                              │
 * │ Currency getCurrency()                     │ 无                        │ 无           │ 获取当前的 Currency 实例                                 │
 * │ void setCurrency(Currency currency)        │ Currency                  │ 无           │ 设置 Currency，用于货币格式                             │
 * └────────────────────────────────────────────┴──────────────────────────┴──────────────┴──────────────────────────────────────────────────────┘
 */
public class C01_Format_Number {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        NumberFormat jpNum = NumberFormat.getInstance(); // 日本
        NumberFormat jpCur = NumberFormat.getCurrencyInstance();
        System.out.println("日本の数値 : " + jpNum.format(50000));
        System.out.println("日本の通貨 : " + jpCur.format(50000));
        NumberFormat usNum = NumberFormat.getInstance(Locale.US); // 米国
        NumberFormat usCur = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println("米国の数値 : " + usNum.format(50000));
        System.out.println("米国の通貨 : " + usCur.format(50000));
        NumberFormat zhNum = NumberFormat.getInstance(Locale.CHINA); // 米国
        NumberFormat zhCur = NumberFormat.getCurrencyInstance(Locale.CHINA);
        System.out.println("中国の数値 : " + zhNum.format(50000));
        System.out.println("中国の通貨 : " + zhCur.format(50000));
        /**
         * 日本の数値 : 50,000
         * 日本の通貨 : ￥50,000
         * 米国の数値 : 50,000
         * 米国の通貨 : $50,000.00
         * 中国の数値 : 50,000
         * 中国の通貨 : ￥50,000.00
         */


        try {
            NumberFormat usNum1 = NumberFormat.getInstance(Locale.US);
            Number value1 = usNum1.parse("500.12");
            System.out.println("value1 : " + value1);
            NumberFormat usCur1 =
                    NumberFormat.getCurrencyInstance(Locale.US);
            double value2 = (double) usCur1.parse("$20,456.99");
            System.out.println("value2 : " + value2);
//            NumberFormat jpCur1 =
//                    NumberFormat.getCurrencyInstance(Locale.JAPAN);
//            double value3 = (double)jpCur1.parse("$20,456.99");
// JAPAN解析不了$20,456.99。java.text.ParseException: Unparseable number: "$20,456.99"
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /**
         * value1 : 500.12
         * value2 : 20456.99
         */
    }
}