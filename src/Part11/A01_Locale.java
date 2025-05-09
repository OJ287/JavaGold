package Part11;

/**
 * @author liyanpeng
 * @date 2025/5/9
 * @description TODO
 */

import java.util.Locale;

/**
 * Java 的 Locale 类使用 ISO 标准来定义语言和国家/地区代码，主要包括 ISO 639（语言代码）和 ISO 3166（国家/地区代码）标准。
 * ISO 639 语言代码
 * 1. 基本说明
 * ISO 639-1: 2字母语言代码（如 "en" 表示英语）
 * ISO 639-2: 3字母语言代码（更全面，包括更多语言）
 * ISO 639-3: 更详细的语言分类
 * ISO 3166 国家/地区代码
 * 1. 基本说明
 * ISO 3166-1 alpha-2: 2字母国家代码（如 "US" 表示美国）
 * ISO 3166-1 alpha-3: 3字母国家代码
 * ISO 3166-1 numeric: 数字国家代码
 * Java主要使用 alpha-2 代码。
 * <p>
 * <p>
 * ✅ 一、Locale 的作用
 * java.util.Locale 是用来表示地区、语言、国家等信息的类，常用于本地化（i18n）功能中，比如：国际化的日期格式、货币、消息资源文件等。
 * <p>
 * ✅ Locale 的构造方法 & 常用创建方式一览
 * ┌──────────────────────────────────────────────┬───────────────────────────────┬────────────┬──────────────────────────────────────────────┐
 * │ 方法签名                                     │ 参数说明                        │ 抛出异常    │ 说明                                         │
 * ├──────────────────────────────────────────────┼───────────────────────────────┼────────────┼──────────────────────────────────────────────┤
 * │ public Locale(String language)               │ language: 语言代码              │ 无         │ 创建只有语言的 Locale                        │
 * │ public Locale(String language, String country)│ language: 语言代码             │ 无         │ 创建包含语言和国家的 Locale                  │
 * │                                              │ country: 国家代码              │            │                                              │
 * └──────────────────────────────────────────────┴───────────────────────────────┴────────────┴──────────────────────────────────────────────┘
 * <p>
 * ✅ 获取默认 Locale 的方法一览
 * ┌────────────────────────────────────────────┬──────────────┬────────────┬─────────────────────────────┐
 * │ 方法签名                                   │ 参数说明     │ 抛出异常    │ 说明                         │
 * ├────────────────────────────────────────────┼──────────────┼────────────┼─────────────────────────────┤
 * │ public static Locale getDefault()          │ 无           │ 无         │ 获取当前默认 Locale          │
 * │ public static void setDefault(Locale l)    │ l: 设置的Locale│ 无         │ 设置默认 Locale              │
 * └────────────────────────────────────────────┴──────────────┴────────────┴─────────────────────────────┘
 * <p>
 * ✅ Locale 的主要方法一览说明表
 * ┌────────────────────────────────────────────────────────────┬────────────────────────────┬──────────────┬──────────────────────────────────────────────┐
 * │ 方法签名                                                   │ 参数说明                   │ 抛出异常     │ 说明                                         │
 * ├────────────────────────────────────────────────────────────┼────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * │ public final String getDisplayCountry()                    │ 无                         │ 无           │ 返回国家的本地化显示名（系统默认）          │
 * │ public  String getDisplayCountry(Locale inLocale)          │ inLocale: 本地化 Locale   │ 无           │ 返回指定 Locale 中该国家的显示名            │
 * │ public  String getCountry()                                │ 无                         │ 无           │ 返回 ISO 3166 国家代码（如 "US", "JP"）      │
 * ├────────────────────────────────────────────────────────────┼────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * │ public final String getDisplayLanguage()                   │ 无                         │ 无           │ 返回当前 Locale 的语言显示名（系统默认）    │
 * │ public  String getDisplayLanguage(Locale inLocale)         │ inLocale: 本地化 Locale   │ 无           │ 返回指定 Locale 中该语言的显示名            │
 * │ public  String getLanguage()                               │ 无                         │ 无           │ 返回 ISO 639 语言代码（如 "en", "ja"）       │
 * ├────────────────────────────────────────────────────────────┼────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * <p>
 * ✅ 常用 Locale 定数一览
 * ┌──────────────────────────────┬────────────┐
 * │ 常量名 都是static final        │ 说明       │
 * ├──────────────────────────────┼────────────┤
 * │ Locale.JAPAN                 │ 日本地区   │
 * │ Locale.US                    │ 美国地区   │
 * │ Locale.FRANCE                │ 法国地区   │
 * │ Locale.JAPANESE              │ 日语       │
 * │ Locale.ENGLISH               │ 英语       │
 * │ Locale.FRENCH                │ 法语       │
 * │ Locale.CHINA                 │ 中国大陆   │
 * │ Locale.SIMPLIFIED_CHINESE    │ 简体中文   │
 * │ Locale.TRADITIONAL_CHINESE   │ 繁體中文   │
 * └──────────────────────────────┴────────────┘
 * <p>
 * ✅ 语言代码 & 国家代码 一览（部分）
 * ┌────────────┬────────────┬────────────────────────────┐
 * │ 语言代码   │ 国家代码   │ 示例 Locale（语言_国家）   │
 * ├────────────┼────────────┼────────────────────────────┤
 * │ en         │ US         │ en_US（英语/美国）         │
 * │ ja         │ JP         │ ja_JP（日语/日本）         │
 * │ zh         │ CN         │ zh_CN（中文/中国）         │
 * │ fr         │ FR         │ fr_FR（法语/法国）         │
 * │ de         │ DE         │ de_DE（德语/德国）         │
 * └────────────┴────────────┴────────────────────────────┘
 * <p>
 * ✅ Locale.Builder 的常用方法一览表
 * ┌────────────────────────────────────┬────────────────────────────┬────────────┬────────────────────────────────────┐
 * │ 方法签名                           │ 参数说明                   │ 抛出异常    │ 说明                               │
 * ├────────────────────────────────────┼────────────────────────────┼────────────┼────────────────────────────────────┤
 * │ setLanguage(String language)       │ language: ISO 639语言代码   │ IllArgEx   │ 设置语言代码                        │
 * │ setRegion(String region)           │ region: ISO 3166国家代码    │ IllArgEx   │ 设置国家代码                        │
 * │ setVariant(String variant)         │ variant: 变体代码           │ IllArgEx   │ 设置变体标识（特殊方言或地区）     │
 * │ setScript(String script)           │ script: ISO 15924脚本代码   │ IllArgEx   │ 设置书写系统，如 Latin、Hans 等     │
 * │ clear()                            │ 无                          │ 无         │ 清除所有设置                        │
 * │ build()                            │ 无                          │ 无         │ 根据设置构建 Locale 对象            │
 * └────────────────────────────────────┴────────────────────────────┴────────────┴────────────────────────────────────┘
 */
public class A01_Locale {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        print("デフォルト  ", Locale.getDefault()); // OS设置的语言和国家
        print("new Locale()", new Locale("ja", "JP"));// 设置语言和国家
        print("JAPAN       ", Locale.JAPAN);// 设置语言和国家
        print("JAPANESE    ", Locale.JAPANESE);// 只设置语言


        Locale us = new Locale("en", "US"); // 米国
        System.out.println(us.getDisplayCountry() + " : " +
                us.getDisplayLanguage());
        System.out.println(us.getDisplayCountry(us) + " : " +
                us.getDisplayLanguage(us));
        Locale japan = new Locale.Builder().setLanguage("ja")
                .setRegion("JP").build();
        System.out.println(japan.getCountry() + " : " +
                japan.getLanguage());
        /**
         * アメリカ合衆国 : 英語
         * United States : English
         * JP : ja
         */

    }

    static void print(String msg, Locale locale) {
        System.out.println(msg + ":locale   :" + locale);
        System.out.println(msg + ":language :" + locale.getLanguage());
        System.out.println(msg + ":country  :" + locale.getCountry());
    }
    /**
     * デフォルト  :locale   :ja_JP
     * デフォルト  :language :ja
     * デフォルト  :country  :JP
     * new Locale():locale   :ja_JP
     * new Locale():language :ja
     * new Locale():country  :JP
     * JAPAN       :locale   :ja_JP
     * JAPAN       :language :ja
     * JAPAN       :country  :JP
     * JAPANESE    :locale   :ja
     * JAPANESE    :language :ja
     * JAPANESE    :country  :
     */
}


/**
 * 关键区别
 * 方法	                        显示语言	            示例输出(默认Locale=zh_CN)	说明
 * getDisplayCountry()	        系统默认Locale	    "美国"	                    使用JVM默认语言显示国家名
 * getDisplayLanguage()	        系统默认Locale	    "英文"	                    使用JVM默认语言显示语言名
 * getDisplayCountry(Locale)	指定Locale	        "United States"	            使用指定语言显示国家名
 * getDisplayLanguage(Locale)	指定Locale	        "English"	                使用指定语言显示语言名
 * <p>
 * 最佳实践
 * 在需要用户界面显示时，使用无参版本（尊重用户系统设置）
 * 在需要强制特定语言显示时，使用带Locale参数的版本
 * 对于日志或内部处理，直接使用代码值（"en", "US"等）
 */