package Part12;

/**
 * @author liyanpeng
 * @date 2025/5/20
 * @description TODO
 */

/**
 * ┌────────────────────────────┬──────────────────────────────────────────────┐
 * │ 考点分类                   │ 内容说明                                     │
 * ├────────────────────────────┼──────────────────────────────────────────────┤
 * │ SecurityManager 的作用     │ 控制 Java 程序访问敏感资源（如文件、网络等）  │
 * ├────────────────────────────┼──────────────────────────────────────────────┤
 * │ SecurityManager 的启用方式 │ System.setSecurityManager(new SecurityManager());│
 * ├────────────────────────────┼──────────────────────────────────────────────┤
 * │ 常见触发点（需记）         │ 读取系统属性、文件读写、网络访问、创建类加载器│
 * ├────────────────────────────┼──────────────────────────────────────────────┤
 * │ 常见异常                   │ SecurityException（权限不足时抛出）           │
 * ├────────────────────────────┼──────────────────────────────────────────────┤
 * │ Permission 类（需了解）    │ java.security.Permission 是所有权限的基类     │
 * ├────────────────────────────┼──────────────────────────────────────────────┤
 * │ 常见子类（需识别）         │ FilePermission / SocketPermission /           │
 * │                            │ PropertyPermission / RuntimePermission        │
 * ├────────────────────────────┼──────────────────────────────────────────────┤
 * │ FilePermission 示例        │ new FilePermission("C:/data.txt", "read");    │
 * ├────────────────────────────┼──────────────────────────────────────────────┤
 * │ Policy 文件作用（了解即可）│ 定义授予哪些代码（路径）什么权限             │
 * ├────────────────────────────┼──────────────────────────────────────────────┤
 * │ Policy 文件格式            │ grant { permission xxxPermission "name", "action"; };│
 * ├────────────────────────────┼──────────────────────────────────────────────┤
 * │ 默认策略                   │ 无 policy 文件时，默认拒绝大多数操作         │
 * ├────────────────────────────┼──────────────────────────────────────────────┤
 * │ java.policy 设置方式       │ -Djava.security.policy=example.policy         │
 * ├────────────────────────────┼──────────────────────────────────────────────┤
 * │ AllPermission（⚠️不要使用）│ 表示拥有所有权限，违背最小权限原则            │
 * └────────────────────────────┴──────────────────────────────────────────────┘
 * <p>
 * <p>
 * ┌────┬────────────────────────────────────────────────────────────┐
 * │ No │ 题目内容（单选）                                           │
 * ├────┼────────────────────────────────────────────────────────────┤
 * │ 1  │ 在以下哪种情况下最有可能抛出 SecurityException？            │
 * │    │ A. 使用 equals() 方法比较字符串                             │
 * │    │ B. 调用 System.exit(0)                                     │
 * │    │ C. 访问本地变量                                             │
 * │    │ D. 创建一个 ArrayList 实例                                  │
 * ├────┼────────────────────────────────────────────────────────────┤
 * │ ✅ 正确答案：B 解析：System.exit() 会被 SecurityManager 拦截检查。 │
 * ├────┼────────────────────────────────────────────────────────────┤
 * │ 2  │ 哪个类的实例可用于限制对文件系统的访问？                     │
 * │    │ A. RuntimePermission                                        │
 * │    │ B. SocketPermission                                         │
 * │    │ C. FilePermission ✅                                        │
 * │    │ D. AccessPermission                                         │
 * ├────┼────────────────────────────────────────────────────────────┤
 * │ ✅ 正确答案：C 解析：FilePermission 控制文件读写权限。             │
 * ├────┼────────────────────────────────────────────────────────────┤
 * │ 3  │ 以下哪项最可能出现在 policy 文件中？                         │
 * │    │ A. allow FilePermission: "file.txt", "read";               │
 * │    │ B. permission java.io.FilePermission "file.txt", "read"; ✅│
 * │    │ C. grant FilePermission for "file.txt" with "read";        │
 * │    │ D. access file:"file.txt" as read;                         │
 * ├────┼────────────────────────────────────────────────────────────┤
 * │ ✅ 正确答案：B policy 语法是 permission + 类全名 + name + actions │
 * ├────┼────────────────────────────────────────────────────────────┤
 * │ 4  │ 当程序被设置了 SecurityManager，但未给予权限时调用           │
 * │    │ System.getProperty("user.home") 会如何？                   │
 * │    │ A. 正常返回用户主目录路径                                   │
 * │    │ B. 返回 null                                                │
 * │    │ C. 抛出 SecurityException ✅                                │
 * │    │ D. 抛出 IOException                                         │
 * ├────┼────────────────────────────────────────────────────────────┤
 * │ ✅ 正确答案：C 读取系统属性是受限制操作，未授权会抛异常。          │
 * ├────┼────────────────────────────────────────────────────────────┤
 * │ 5  │ 下列哪项不是 java.security.Permission 的子类？               │
 * │    │ A. FilePermission                                           │
 * │    │ B. SocketPermission                                         │
 * │    │ C. RuntimePermission                                        │
 * │    │ D. SystemPermission ❌（伪造选项）                           │
 * ├────┼────────────────────────────────────────────────────────────┤
 * │ ✅ 正确答案：D 解析：SystemPermission 并不存在。                  │
 * └────┴────────────────────────────────────────────────────────────┘
 */

/**
 * 📄 Policy 文件示例
 * ❌ policy-deny.policy（不给权限，会抛异常）
 * grant {
 *     // 空权限，什么都不允许
 * };
 * ✅ policy-allow.policy（允许读取 user.home）
 * grant {
 *     permission java.util.PropertyPermission "user.home", "read";
 * };
 *
 * 🏁 编译与运行方法（命令行）
 * javac SecurityExample.java
 *
 * 运行时 不加权限
 * java -Djava.security.manager \
 *      -Djava.security.policy=policy-deny.policy \
 *      SecurityExample
 *  输出：SecurityException: 无权限访问 user.home
 *
 *  运行时 加权限
 *  java -Djava.security.manager \
 *      -Djava.security.policy=policy-allow.policy \
 *      SecurityExample
 *   输出： User home: /Users/liyanpeng
 *
 *
 *
 */
public class F01_Security {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO

        // 设置安全管理器
        System.setSecurityManager(new SecurityManager());

        try {
            // 读取受限制的系统属性
            String home = System.getProperty("user.home");
            System.out.println("User home: " + home);
        } catch (SecurityException e) {
            System.out.println("SecurityException: 无权限访问 user.home");
        }
    }
}