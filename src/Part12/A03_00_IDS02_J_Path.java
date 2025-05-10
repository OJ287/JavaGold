package Part12;

/**
 * @author liyanpeng
 * @date 2025/5/10
 * @description TODO
 */

/**
 * ✅ IDS02-J：パス名は検証する前に正規化する
 * ✅✅✅安全规范：使用getCanonicalPath代替getAbsolutePath和getPath
 * <p>
 * <p>
 * ■ 规则标题
 * IDS02-J: Canonicalize path names before validating them
 * （パス名は検証する前に正規化する）
 * <p>
 * ■ 规则目的
 * 攻击者可能构造「看似安全但实际指向危险位置」的路径，绕过路径白名单或文件权限检查。例如：
 * 使用 ../、符号链接（symlink）等手段逃出限定目录
 * 表面路径合法，但实际文件不在允许范围内
 * <p>
 * ■ 风险示例（未正規化时的バイパス）
 * File file = new File(request.getParameter("filename"));
 * if (!file.getPath().startsWith("/safe/dir/")) {
 * throw new SecurityException("Unauthorized access");
 * }
 * // 攻击者传入路径："/safe/dir/../etc/passwd"
 * <p>
 * <p>
 * ■ 正确做法：使用 getCanonicalPath() 做路径正規化
 * File file = new File(request.getParameter("filename"));
 * String canonicalPath = file.getCanonicalPath();//规范路径
 * String base = new File("/safe/dir/").getCanonicalPath();
 * <p>
 * if (!canonicalPath.startsWith(base)) {
 * throw new SecurityException("Unauthorized access");
 * }
 * <p>
 * ■ getPath() vs getCanonicalPath() 比较
 * ┌────────────────────────────┬────────────────────────────────────────────────────┐
 * │ メソッド名                 │ 説明                                                 │
 * ├────────────────────────────┼────────────────────────────────────────────────────┤
 * │ File.getPath()             │ 入力された文字列通りのパス文字列を返す               │
 * │ File.getAbsolutePath()     │ 絶対パスに変換するが、シンボリックリンクは解決しない │
 * │ File.getCanonicalPath()    │ 完全な物理パスに解決し、シンボリックリンクも除外     │
 * └────────────────────────────┴────────────────────────────────────────────────────┘
 */
public class A03_00_IDS02_J_Path {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO

    }
}