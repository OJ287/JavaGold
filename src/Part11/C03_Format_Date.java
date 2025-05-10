package Part11;

/**
 * @author liyanpeng
 * @date 2025/5/10
 * @description TODO
 */

import java.time.LocalDate;

/**
 * 出题范围多的
 * java.time包下面的
 * |____LocalDate
 * |____LocalTime
 * |____LocalDateTime
 */
public class C03_Format_Date {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        LocalDate date = LocalDate.of(2021, 2, 10);// 引数全部是int型
        System.out.println("date       : " + date);
        System.out.println("getYear       : " + date.getYear());
        System.out.println("getMonth      : " + date.getMonth()); // 返回值是java.time.Month的枚举型
        System.out.println("getMonthValue : " + date.getMonthValue());
        System.out.println("getDayOfMonth : " + date.getDayOfMonth());
        System.out.println("getDayOfYear  : " + date.getDayOfYear());
        System.out.println("getDayOfWeek  : " + date.getDayOfWeek()); // 返回值是周几的表达
        /**
         * date       : 2021-02-10
         * getYear       : 2021
         * getMonth      : FEBRUARY
         * getMonthValue : 2
         * getDayOfMonth : 10
         * getDayOfYear  : 41
         * getDayOfWeek  : WEDNESDAY
         */
    }
}