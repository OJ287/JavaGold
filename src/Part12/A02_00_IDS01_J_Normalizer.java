package Part12;

/**
 * @author liyanpeng
 * @date 2025/5/10
 * @description TODO
 */

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ✅ IDS01-J：文字列は検査する前に標準化する
 * ✅✅✅安全规范：使用Normalizer.normalize(用户入力文字, Normalizer.Form.NFKC);
 * ✅✅✅将入力的字符串进行正则检查，正则检查可能有被编码的文字，所以还要使用Normalizer
 * <p>
 * ■ 规约标题（Rule Title）
 * IDS01-J: Normalize strings before validating them
 * 文字列は検査する前に標準化する
 * <p>
 * ■ 规约目的（Why This Rule Exists）
 * 攻击者可能会利用不同表示方式的字符（例如全角/半角、Unicode 合成字符、替代字符、控制字符等）绕过验证逻辑或白名单检查，进而：
 * 绕过访问控制
 * 插入非法命令（如 XSS、SQL Injection）
 * 引发不一致逻辑判断（例如用户名重复检测失败）
 * <p>
 * <p>
 * ■ Normalizer API 一覧
 * ┌──────────────────────────────────────────────┬────────────────────────────────┐
 * │ メソッド名                                   │ 説明                             │
 * ├──────────────────────────────────────────────┼────────────────────────────────┤
 * │ Normalizer.normalize(String, Form)           │ 指定フォーマットに文字列を標準化 │
 * │ Normalizer.isNormalized(String, Form)        │ 既に標準化されているかをチェック │
 * │ Normalizer.Form.NFC                          │ 正規合成（推奨）                │
 * │ Normalizer.Form.NFD                          │ 正規分解                        │
 * │ Normalizer.Form.NFKC                         │ 互換合成（記号含む）            │
 * │ Normalizer.Form.NFKD                         │ 互換分解                        │
 * └──────────────────────────────────────────────┴────────────────────────────────┘
 * <p>
 * ■ よく使われる用途
 * ┌────────────────────┬──────────────────────────────────────────────────────┐
 * │ 用途               │ 説明                                                 │
 * ├────────────────────┼──────────────────────────────────────────────────────┤
 * │ ログイン名比較     │ admin、ａｄｍｉｎ、a\u030Admin 等の統一                 │
 * │ ファイルパスの比較 │ /tmp/../etc/passwd などのバイパス回避                 │
 * │ ホワイトリスト検査 │ 許可語と完全一致するか（可視的でなく構造的に）         │
 * │ キーワードブロック │ “s\u0063ript” ≠ “script” 問題に対応                   │
 * └────────────────────┴──────────────────────────────────────────────────────┘
 * <p>
 * ■ 注意点
 * ・Normalizer.Form.NFC が推奨される（Unicode 標準のデフォルト形式）
 * ・検証/内部処理のみに使う（表示には不向きな場合あり）
 * ・標準化前の equals や contains 判定は危険
 */
public class A02_00_IDS01_J_Normalizer {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        /**
         *  不能让用户在表单页面输入类似代码的文本
         */
        String msg = "<script>";
        Pattern pattern = Pattern.compile("[<>]");
        Matcher matcher = pattern.matcher(msg);
        if (matcher.find()) {
//            throw new IllegalStateException();
            System.out.println("throw new IllegalStateException();");
        }
        /**
         * throw new IllegalStateException();
         */


        // 但如果用户使用Unicode的文字列输入的话
        String msg1 = "¥uFE64" + "script" + "¥uFE645";
        Pattern pattern1 = Pattern.compile("[<>]");
        Matcher matcher1 = pattern1.matcher(msg1);
        if (matcher1.find()) {//false
//            throw new IllegalStateException();
            System.out.println("throw new IllegalStateException(1);");
        }

        /**
         * 可以对任意形式的被编码的文字列进行检查：Form.NFKC
         */
        String msg2 = "\uFE64" + "script" + "\uFE65";
        msg2 = Normalizer.normalize(msg2, Normalizer.Form.NFKC);

        System.out.println(msg2);
        Pattern pattern2 = Pattern.compile("[<>]");
        Matcher matcher2 = pattern2.matcher(msg2);
        if (matcher2.find()) {//false
//            throw new IllegalStateException();
            System.out.println("throw new IllegalStateException(2);");
        }

        /**
         * <script>
         * throw new IllegalStateException(2);
         */


    }
}