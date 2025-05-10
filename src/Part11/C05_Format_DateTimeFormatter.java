package Part11;

/**
 * @author liyanpeng
 * @date 2025/5/10
 * @description TODO
 */

import java.time.LocalDate;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * ✅ofPattern
 * ✅ 1. ofPattern 所支持的主要 pattern 字符一览表
 * ┌────────┬──────────────────────┬──────────────────────────────────────┐
 * │ 字符   │ 含义                 │ 示例（yMd）                          │
 * ├────────┼──────────────────────┼──────────────────────────────────────┤
 * │ G      │ 纪元（Era）          │ AD, BC                               │
 * │ u      │ 西历年               │                                      │
 * │ y      │ 年（year）           │ 2025                                 │
 * │ M      │ 月（month）          │ 1～12 或 January、Jan                │
 * │ d      │ 日（day）            │ 1～31                                │
 * │ E      │ 星期（day-of-week）  │ Tue, Tuesday                         │
 * │ a      │ 上午/下午（AM/PM）   │ AM, PM                               │
 * │ h      │ 时（1-12）           │ 1～12                                │
 * │ H      │ 时（0-23）           │ 0～23                                │
 * │ m      │ 分                   │ 00～59                               │
 * │ s      │ 秒                   │ 00～59                               │
 * │ S      │ 毫秒                 │ 0～999                               │
 * │ z      │ 时区名               │ Japan Standard Time                  │
 * │ Z      │ 时区偏移             │ +0900                                │
 * └────────┴──────────────────────┴──────────────────────────────────────┘
 * <p>
 * ✅ 2. 日期 pattern 示例一览表（格式 → 说明 → 输出）
 * ┌─────────────────────────────┬────────────────────────────┬──────────────────────────┐
 * │ pattern 文字                │ 说明                       │ 示例输出（2025年5月10日）│
 * ├─────────────────────────────┼────────────────────────────┼──────────────────────────┤　　日本
 * │ d.MM.yy                     │ 日.月.年                  │ 24.8.20               │　　　　24.8.20
 * │ EEE, MMM dd ,''yy            │ 星期 + 月 + 日 + 年        │ Sat, May 10 ,'25         │ 土,５月 10,'25
 * │ yyyy-MM-dd                  │ 年-月-日（ISO风）          │ 2025-05-10               │
 * │ yy/MM/dd                    │ 两位年/斜杠月/日           │ 25/05/10                 │
 * │ yyyy年M月d日                │ 中文年月日                 │ 2025年5月10日            │
 * │ E, MMM dd yyyy              │ 星期 + 月 + 日 + 年        │ Sat, May 10 2025         │
 * │ yyyy.MM.dd G 'at' HH:mm:ss  │ 纪元 + 日期时间（带固定字）│ 2025.05.10 AD at 13:45:20│   2025.05.10 西暦 at 13:45:20
 * └─────────────────────────────┴────────────────────────────┴──────────────────────────┘
 * <p>
 * ✅ 3. pattern 字符数不同 → 显示格式差异（中/美/日比较）
 * 字符4个以下就是缩短形式表示
 * 字符4个以上就是全部完整表示
 * 字符5个就是最短形式表示（5个字符表示最短形式的规则是DateTimeFormatter特有的，其他一般是和4个字符表示一样）
 * <p>
 * ┌────────────┬────────────┬────────────────────┬──────────────────────────────┐
 * │ pattern    │ 字符数     │ 表现差异（说明）   │ 输出示例（中/美/日）         │
 * ├────────────┼────────────┼────────────────────┼──────────────────────────────┤
 * │ M          │ 1          │ 数字月（无0补齐）   │ 5                             │
 * │ MM         │ 2          │ 数字月（0补齐）     │ 05                            │
 * │ MMM        │ 3          │ 简写月名            │ May / 5月 / 5月               │
 * │ MMMM       │ 4+         │ 全写月名            │ May / 五月 / 五月             │
 * ├────────────┼────────────┼────────────────────┼──────────────────────────────┤
 * │ E          │ 1～3       │ 简写星期            │ Sat / 土 / 土                 │
 * │ EEEE       │ 4+         │ 全写星期            │ Saturday / 土曜日 / 星期六    │
 * │ EEEEE       │ 4         │ 最短星期            │ S / 土 / 六    │
 * ├────────────┼────────────┼────────────────────┼──────────────────────────────┤
 * │ y          │ 1～2       │ 年（2位）           │ 25                            │
 * │ yyyy       │ 4          │ 年（4位）           │ 2025                          │
 * ├────────────┼────────────┼────────────────────┼──────────────────────────────┤
 * │ a          │ 任意       │ 上午/下午           │ AM / PM / 午前 / 午後         │
 * └────────────┴────────────┴────────────────────┴──────────────────────────────┘
 * <p>
 * EEEEE：中文没有
 * MONDAY: M
 * TUESDAY: T
 * WEDNESDAY: W
 * THURSDAY: T
 * FRIDAY: F
 * SATURDAY: S
 * SUNDAY: U
 */
public class C05_Format_DateTimeFormatter {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        LocalDate date = LocalDate.of(2021, 1, 1);
        DateTimeFormatter f1 =
                DateTimeFormatter.ofPattern("G yy年 E", Locale.US);
        DateTimeFormatter f2 =
                DateTimeFormatter.ofPattern("GGGG yy年 EEEE", Locale.US);
        DateTimeFormatter f3 =
                DateTimeFormatter.ofPattern("GGGGG yy年 EEEEE", Locale.US);
        System.out.println("G yy年 E         : " + date.format(f1));
        System.out.println("GGGG yy年 EEEE   : " + date.format(f2));
        System.out.println("GGGGG yy年 EEEEE : " + date.format(f3));

        JapaneseDate j_date = JapaneseDate.of(2021, 1, 1);
        DateTimeFormatter jf1 =
                DateTimeFormatter.ofPattern("G yy年 E");
        DateTimeFormatter jf2 =
                DateTimeFormatter.ofPattern("GGGG yy年 EEEE");
        DateTimeFormatter jf3 =
                DateTimeFormatter.ofPattern("GGGGG yy年 EEEEE");
        System.out.println("G yy年 E         : " + j_date.format(jf1));
        System.out.println("GGGG yy年 EEEE   : " + j_date.format(jf2));
        System.out.println("GGGGG yy年 EEEEE : " + j_date.format(jf3));


        LocalDate date1 = LocalDate.of(2021, 1, 1);
        DateTimeFormatter jf11 =
                DateTimeFormatter.ofPattern("G yy年 E", Locale.JAPAN);
        DateTimeFormatter jf21 =
                DateTimeFormatter.ofPattern("GGGG yy年 EEEE", Locale.JAPAN);
        DateTimeFormatter jf31 =
                DateTimeFormatter.ofPattern("GGGGG yy年 EEEEE", Locale.JAPAN);
        System.out.println("G yy年 E         : " + date1.format(jf11));
        System.out.println("GGGG yy年 EEEE   : " + date1.format(jf21));
        System.out.println("GGGGG yy年 EEEEE : " + date1.format(jf31));
        /**
         * G yy年 E         : AD 21年 Fri
         * GGGG yy年 EEEE   : Anno Domini 21年 Friday
         * GGGGG yy年 EEEEE : A 21年 F
         *
         * G yy年 E         : 令和 03年 金
         * GGGG yy年 EEEE   : 令和 03年 金曜日
         * GGGGG yy年 EEEEE : R 03年 金
         *
         * G yy年 E         : 西暦 21年 金
         * GGGG yy年 EEEE   : 西暦 21年 金曜日
         * GGGGG yy年 EEEEE : AD 21年 金
         */


        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy年 MM月dd日 EEEE");
        String target = "2021年 01月30日 土曜日";
        LocalDate date11 = LocalDate.parse(target, fmt);
        System.out.println(date11);
        /**
         * 2021-01-30
         */
    }
}