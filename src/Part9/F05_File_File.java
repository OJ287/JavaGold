package Part9;

/**
 * @author liyanpeng
 * @date 2025/5/6
 * @description TODO
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;

/**
 * ✅ファイルのメタデータを調べる
 * java.nio.file.attributeパッケージの主なインタフェースとクラス
 * 名称                              类型       简要说明
 * --------------------------------- ---------- -------------------------------------------------------
 * FileTime                          类         表示文件的时间戳（创建、修改、访问时间），不可变类
 * BasicFileAttributes               接口       提供基本文件属性的读取，如创建时间、大小、是否为目录等
 * DosFileAttributes                 接口       提供 DOS 风格的属性，如只读、隐藏、系统文件、归档
 * PosixFileAttributes               接口       提供 POSIX 风格的文件属性（需类 Unix 系统），含权限等
 * FileAttribute<T>                 接口       泛型接口，用于在创建文件时同时设置多个属性
 * --------------------------------- ---------- -------------------------------------------------------
 * <p>
 * ✅BasicFileAttributes 接口中 直接可调用的方法与返回值类型
 * 属性名（用于getAttribute）      BasicFileAttributes方法      返回值类型               说明
 * ----------------------------- --------------------------- ------------------------ ----------------------------------------
 * basic:lastModifiedTime        lastModifiedTime()           FileTime                 最后修改时间
 * basic:lastAccessTime          lastAccessTime()             FileTime                 最后访问时间
 * basic:creationTime            creationTime()               FileTime                 创建时间
 * basic:size                    size()                       long                     文件大小（以字节为单位）
 * basic:isRegularFile           isRegularFile()              boolean                  是否为普通文件
 * basic:isSymbolicLink          isSymbolicLink()             boolean                  是否为符号链接
 * 以下不是教科书内容
 * basic:isDirectory             isDirectory()                boolean                  是否为目录
 * basic:isOther                 isOther()                    boolean                  既不是文件也不是目录的其他类型（如管道）
 * basic:fileKey                 fileKey()                    Object                   文件标识符（可能为null，依平台）
 * ----------------------------- --------------------------- ------------------------ ----------------------------------------
 * <p>
 * <p>
 * ✅DosFileAttributes 相较于 BasicFileAttributes 新增支持的 4 个标准属性
 * 属性名（用于getAttribute）     方法签名                        返回值类型   说明
 * ----------------------------- ------------------------------- ------------ ----------------------------------------------
 * dos:isArchive                 isArchive()                     boolean      是否为归档标记（备份用）
 * dos:isHidden                  isHidden()                      boolean      是否为隐藏文件
 * dos:isReadOnly                isReadOnly()                    boolean      是否为只读文件
 * dos:isSystem                  isSystem()                      boolean      是否为系统文件
 * ----------------------------- ------------------------------- ------------ ----------------------------------------------
 */
public class F05_File_File {
    // 自动生成 main 方法
    public static void main(String[] args) throws IOException {
        // TODO
        // Files.getAttribute
        Path path = Paths.get("src/Part9/data_F_1.txt");
        Object obj1 = Files.getAttribute(path, "creationTime");
        Object obj2 = Files.getAttribute(path, "lastModifiedTime");
        Object obj3 = Files.getAttribute(path, "size");
        System.out.format("creationTime     : %s%n", obj1);
        System.out.format("lastModifiedTime : %s%n", obj2);
        System.out.format("size             : %s%n", obj3);
        /**
         * creationTime     : 2025-05-04T15:17:38Z
         * lastModifiedTime : 2025-05-06T02:57:46.344861Z
         * size             : 19
         */


        //　BasicFileAttributes. Files.readAttributes　　　获取多个文件属性（类型安全版）返回BasicFileAttributes或他的子类
        Path p1 = Paths.get("src/Part9/data_F_1.txt");
        BasicFileAttributes attr =
                Files.readAttributes(p1, BasicFileAttributes.class);
        System.out.format("creationTime     : %s%n", attr.creationTime());
        System.out.format("lastModifiedTime : %s%n",
                attr.lastModifiedTime());
        System.out.format("size             : %s%n", attr.size());
        /**
         * creationTime     : 2025-05-04T15:17:38Z
         * lastModifiedTime : 2025-05-06T02:57:46.344861Z
         * size             : 19
         */

        // DosFileAttributes
        Path p2 = Paths.get("src/Part9/data_F_1.txt");
        DosFileAttributes attr2 =
                Files.readAttributes(p2, DosFileAttributes.class);
        System.out.format("isArchive     : %s%n", attr2.isArchive());
        System.out.format("isHidden      : %s%n", attr2.isHidden());
        System.out.format("isReadOnly    : %s%n", attr2.isReadOnly());
        System.out.format("isSystem      : %s%n", attr2.isSystem());

    }
}