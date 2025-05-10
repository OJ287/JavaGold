package Part11;

/**
 * @author liyanpeng
 * @date 2025/5/10
 * @description TODO
 */

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * 答え：
 * 1:AD
 * 2:D
 * 3:C
 * 4:C
 * 5:AC
 * 6:D
 * 7:B→F
 * 8:C
 * 9:B→F
 * 10:B→F
 * 11:D
 * 12:C
 * 13:B→E
 */
public class YYY_Practice01 {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        /**
         * 7
         */
//        Locale.setDefault(new Locale("it", "IT"));
//        Locale locale = Locale.getDefault();
//        ResourceBundle obj
//                = ResourceBundle.getBundle("MyResources", locale);//Exception in thread "main" java.util.MissingResourceException: Can't find bundle for base name MyResources, locale it_IT
//        System.out.println("data : " + obj.getString("data"));

        /**
         * 9
         */
//        ResourceBundle bundle
//                = ResourceBundle.getBundle("SE", Locale.US);
//        Properties props = new Properties();
//        bundle.keySet()
//                .stream()
//                .forEach(k -> props.put(k, bundle.getString(k)));
//        bar(props);

        /**
         * 10
         * DateTimeFormatter不能格式化LocalDate
         * DateTimeFormatter可以格式化LocalDateTime
         * 实行时error
         */
//        LocalDate date = LocalDate.of(2020, Month.FEBRUARY, 10);
//        DateTimeFormatter fmt1 =
//                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
//        System.out.println(fmt1.format(date));//Exception in thread "main" java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: HourOfDay


        /**
         * 13
         * 运行时error
         * "yyyy month:M"  会将month:也当成pattern文字处理，但是o，t不是pattern文字识别不了报错
         * 如果想输出month:的话需要将想输出的固定文字列使用单引号包括
         * "yyyy month:M"   "yyyy 'month:'M"
         */
        var f = DateTimeFormatter.ofPattern("yyyy month:M");//Exception in thread "main" java.lang.IllegalArgumentException: Unknown pattern letter: o
        System.out.println(f.format(LocalTime.of(2020, 10, 30)));


    }

    static void bar(Properties props) {
        // get只有一个参数就是key，返回Obkext
        // getProperty是有一个参数key返回String，和两个参数key，default的返回String
//        System.out.println(props.get("drink", "water") + " " +
//                props.get("snack", "fruit"));
        System.out.println(props.getProperty("drink", "water") + " " +
                props.getProperty("snack", "fruit"));
    }
}

/**
 * 类	                    是否可直接 new	获取实例的方式
 * Locale	                ✅ 可以	        new Locale(...)
 * ResourceBundle	        ❌ 不能	        ResourceBundle.getBundle()
 * ListResourceBundle	    ❌ 不能	        继承后通过 getBundle() 加载
 * PropertyResourceBundle	❌ 不能	        通过 properties 文件自动加载
 * NumberFormat	            ❌ 不能	        NumberFormat.getInstance() 等工厂方法
 * DecimalFormat	        ✅ 可以	        new DecimalFormat(...) 或 (DecimalFormat)NumberFormat.getInstance()
 * DateTimeFormatter	    ❌ 不能	        DateTimeFormatter.ofPattern() 或使用预定义常量
 * DateFormat	            ❌ 不能	        DateFormat.getInstance() 等工厂方法
 * MessageFormat	        ✅ 可以	        new MessageFormat(...)
 */