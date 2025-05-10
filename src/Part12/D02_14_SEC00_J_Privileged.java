package Part12;

/**
 * @author liyanpeng
 * @date 2025/5/11
 * @description TODO
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

/**
 * 使用AccessController.doPrivileged(PrivilegedAction<T> action)的特权代码块一本是下面这四种情况
 * 1.没有返回值，不抛出异常
 * 2.返回返回值
 * 3.使用local变量
 * 4.抛出异常
 */
public class D02_14_SEC00_J_Privileged {
    // 自动生成 main 方法
    public static void main(String[] args) throws IOException {
        // TODO
        /**
         * ✅ 1. 没有返回值、不抛出异常
         */
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            System.setProperty("my.property", "safeValue");
            return null; // 必须返回 null，因为 T 为 Void
        });


        /**
         * ✅ 2. 返回值
         */
        String userHome = AccessController.doPrivileged((PrivilegedAction<String>) () -> {
            return System.getProperty("user.home");
        });
        System.out.println("User Home: " + userHome);
//        return userHome;


        /**
         * ✅3. 使用 local 变量
         */
        String key = "os.name";

        String osName = AccessController.doPrivileged((PrivilegedAction<String>) () -> {
            // 可以直接访问外部的 local 变量（必须是 effectively final）
            return System.getProperty(key);
        });

        System.out.println("OS Name: " + osName);


        /**
         * ✅ 4. 抛出异常（需要使用 PrivilegedExceptionAction）
         * 虽然 PrivilegedAction<T> 不能抛检查型异常（checked exception），
         * 但你可以使用 AccessController.doPrivileged(PrivilegedExceptionAction<T>) 来处理抛异常的情况。
         */
        try {
            String path = AccessController.doPrivileged((PrivilegedExceptionAction<String>) () -> {
                return Files.readString(Path.of("/etc/passwd"));
            });
            System.out.println("File Content: " + path);
        } catch (PrivilegedActionException e) {
            // 必须捕获并处理包装的异常
            e.printStackTrace();
            throw (IOException) e.getException();
        }

    }


    public static void changeData(String key) throws IOException {
        FileInputStream fis = openFile();
        // 引数のキーに対応した値を読み込む
        // 値をもとに処理を行う
        // ファイルをクローズする
    }

    /**
     * 封装的AccessController.doPrivileged(）使用了public
     * 也就是谁都可以调用到
     * 是危险的
     * 要改成private
     */
    // public static FileInputStream openFile() throws FileNotFoundException {
    private static FileInputStream openFile() throws FileNotFoundException {
        FileInputStream fis = null;
        try {
            fis = AccessController.doPrivileged(
                    new PrivilegedExceptionAction<FileInputStream>() {
                        @Override
                        public FileInputStream run() throws FileNotFoundException {
                            return new FileInputStream("libB/sec.txt");
                        }
                    });
            return fis;
        } catch (PrivilegedActionException e) {
            Exception ex = e.getException();
            if (ex instanceof FileNotFoundException) {
                throw (FileNotFoundException) ex;
            } else {
                throw new Error("予期しないエラー", ex);
            }
        }
    }

}