package Part11;

/**
 * @author liyanpeng
 * @date 2025/5/10
 * @description TODO
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * ✅ 一、DateTimeFormatter 是什么
 * java.time.format.DateTimeFormatter 是 Java 8 提供的日期时间格式化/解析类。
 * 用于将 LocalDate、LocalDateTime 等类格式化为字符串，或反之解析。
 * 线程安全、不可变，推荐在多线程环境中使用。
 * 线程安全：所有 DateTimeFormatter 实例都是不可变且线程安全的
 * <p>
 * ✅ 二、主要用途
 * 格式化：将时间对象格式化成字符串
 * 解析：将字符串解析成时间对象（如 LocalDate、LocalDateTime 等）
 * <p>
 * <p>
 * ✅ 三、常用预定义格式常量（static final）
 * ┌────────────────────────────┬────────────────────────────────────────────────────┐
 * │ 常量名                     │ 说明                                               │
 * ├────────────────────────────┼────────────────────────────────────────────────────┤
 * │ DateTimeFormatter.ISO_LOCAL_DATE │ ISO-8601 格式（如 2025-05-10）               │
 * │ DateTimeFormatter.ISO_LOCAL_TIME │ ISO-8601 时间（如 14:30:00）                 │
 * │ DateTimeFormatter.ISO_LOCAL_DATE_TIME │ 日期+时间（如 2025-05-10T14:30:00）     │
 * │ DateTimeFormatter.BASIC_ISO_DATE │ 基本 ISO 日期格式（如 20250510）            │
 * │ DateTimeFormatter.ISO_INSTANT     │ UTC 格式（如 2025-05-10T05:30:00Z）         │
 * │ DateTimeFormatter.ISO_OFFSET_DATE │ 带偏移量的日期格式                          │
 * │ DateTimeFormatter.RFC_1123_DATE_TIME │ RFC 1123格式（如 Sat, 10 May 2025 14:30:00 GMT） │
 * └────────────────────────────┴────────────────────────────────────────────────────┘
 * <p>
 * ✅ 四、主要使用方法（静态/实例方法）
 * 每个框内的第一个是教科书内容。
 * 最后的ofPattern两个方法都是教科书内容
 * ┌─────────────────────────────────────────────────────────────────────────────┬────────────────────────────────────┬────────────────────────────┬────────────────────────────────────────────────────────────┐
 * │ 方法签名                                                                    │ 参数说明                           │ 抛出异常                   │ 说明                                                       │
 * ├─────────────────────────────────────────────────────────────────────────────┼────────────────────────────────────┼────────────────────────────┼────────────────────────────────────────────────────────────┤
 * │ public static final DateTimeFormatter ISO_DATE                              │ 无                                 │ 无                         │ ISO 标准日期（yyyy-MM-dd）                                │
 * │ public static final DateTimeFormatter ISO_DATE_TIME                         │ 无                                 │ 无                         │ ISO 标准日期时间（yyyy-MM-dd'T'HH:mm:ss）                │
 * │ public static final DateTimeFormatter ISO_LOCAL_DATE                        │ 无                                 │ 无                         │ 不含时区的日期格式                                         │
 * │ public static final DateTimeFormatter ISO_LOCAL_DATE_TIME                   │ 无                                 │ 无                         │ 不含时区的日期时间格式                                     │
 * │ public static final DateTimeFormatter ISO_LOCAL_TIME                        │ 无                                 │ 无                         │ 不含时区的时间格式                                         │
 * │ public static final DateTimeFormatter ISO_TIME                              │ 无                                 │ 无                         │ 标准时间格式                                               │
 * ├─────────────────────────────────────────────────────────────────────────────┼────────────────────────────────────┼────────────────────────────┼────────────────────────────────────────────────────────────┤
 * │ public static DateTimeFormatter ofLocalizedDate(FormatStyle style)          │ style: 日期格式样式                │ 无                         │ 根据风格创建仅日期 formatter                               │
 * │ public static DateTimeFormatter ofLocalizedTime(FormatStyle style)          │ style: 时间格式样式                │ 无                         │ 根据风格创建仅时间 formatter                               │
 * │ public static DateTimeFormatter ofLocalizedDateTime(FormatStyle style)      │ style: 日期+时间格式样式           │ 无                         │ 创建日期时间 formatter                                     │
 * │ public static DateTimeFormatter ofLocalizedDateTime(FormatStyle dateStyle,  │ dateStyle, timeStyle: 各自风格     │ 无                         │ 分别指定日期与时间的格式风格                               │
 * │                                              FormatStyle timeStyle)         │                                    │                            │                                                            │
 * ├─────────────────────────────────────────────────────────────────────────────┼────────────────────────────────────┼────────────────────────────┼────────────────────────────────────────────────────────────┤
 * │ public String format(TemporalAccessor temporal)                             │ temporal: 如 LocalDateTime         │ DateTimeException          │ 将时间对象格式化为字符串                                   │
 * │ public TemporalAccessor parse(CharSequence text)                            │ text: 时间字符串                    │ DateTimeParseException     │ 将字符串解析为时间对象（泛型 TemporalAccessor）           │
 * │ public <T extends TemporalAccessor> T parse(CharSequence text,              │ text: 时间字符串                    │ DateTimeParseException     │ 解析为指定类型（如 LocalDate、LocalDateTime）             │
 * │                                   TemporalQuery<T> query)                   │ query: 结果类型                    │                            │                                                            │
 * ├─────────────────────────────────────────────────────────────────────────────┼────────────────────────────────────┼────────────────────────────┼────────────────────────────────────────────────────────────┤
 * │ public DateTimeFormatter localizedBy(Locale locale)                         │ locale: 地区信息                    │ 无                         │ 同上，JDK 17 推荐的替代方法                                │
 * │ public DateTimeFormatter withLocale(Locale locale)                          │ locale: 地区信息                    │ 无                         │ 返回使用指定地区的 formatter（用于多语言）               │
 * └─────────────────────────────────────────────────────────────────────────────┴────────────────────────────────────┴────────────────────────────┴────────────────────────────────────────────────────────────┘
 * │ public static DateTimeFormatter ofPattern(String pattern)                   │ pattern: 格式字符串                 │ IllegalArgumentException   │ 使用指定 pattern 创建 formatter                           │
 * │ public static DateTimeFormatter ofPattern(String pattern, Locale locale)    │ pattern: 格式字符串                 │ IllegalArgumentException   │ 使用指定 pattern 与 locale 创建 formatter                 │
 * │                                                                             │ locale: 地区设定                    │                            │                                                            │
 * ├─────────────────────────────────────────────────────────────────────────────┼────────────────────────────────────┼────────────────────────────┼────────────────────────────────────────────────────────────┤
 * <p>
 * <p>
 * ✅ 五、FormatStyle 的常用枚举常量（用于本地化格式）
 * ┌────────────┬────────────────────────────────────────────┐
 * │ 枚举常量   │ 说明                                       │
 * ├────────────┼────────────────────────────────────────────┤
 * │ FULL       │ 最完整格式，如 “2025年5月10日 星期六”      │
 * │ LONG       │ 较长格式，如 “2025年5月10日”               │
 * │ MEDIUM     │ 中等格式，如 “2025/05/10”                  │
 * │ SHORT      │ 简短格式，如 “25/05/10”                    │
 * └────────────┴────────────────────────────────────────────┘
 */
public class C04_Format_DateTimeFormatter {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        LocalDate date = LocalDate.of(2021, 2, 1);
        DateTimeFormatter fmt1 = DateTimeFormatter.ISO_DATE;
        System.out.println("date           : " + date);
        System.out.println("fmt1#format()  : " + fmt1.format(date));
        System.out.println("date#format()  : " + date.format(fmt1));

        DateTimeFormatter fmt2 =
                DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        System.out.println("FormatStyle#JP fmt2.format(date): " + fmt2.format(date));
        System.out.println("FormatStyle#JP date#format(): " + date.format(fmt2));

        DateTimeFormatter fmt3 = fmt2.localizedBy(Locale.US);
        System.out.println("FormatStyle#US : " + fmt3.format(date));
        /**
         * date           : 2021-02-01
         * fmt1#format()  : 2021-02-01
         * date#format()  : 2021-02-01
         * FormatStyle#JP fmt2.format(date): 2021/02/01
         * FormatStyle#JP date#format(): 2021/02/01
         * FormatStyle#US : 2/1/21
         */


        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter fmt11 = DateTimeFormatter.ISO_DATE;
        System.out.println("dateTime           : " + dateTime);
        System.out.println("fmt11#format()     : " + fmt11.format(dateTime));
        /**
         * dateTime           : 2025-05-10T11:15:19.270870
         * fmt11#format()     : 2025-05-10
         */
    }
}