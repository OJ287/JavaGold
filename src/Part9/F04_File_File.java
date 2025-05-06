package Part9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author liyanpeng
 * @date 2025/5/6
 * @description TODO
 */

public class F04_File_File {
    // 自动生成 main 方法
    public static void main(String[] args) throws IOException {
        // TODO
        /**
         * 之前是使用InputStream、OutputStream，
         * BufferedReader，BufferedWriter进行文件内容的读取操作
         * 现在可以使用更方便的Files.readLines（）
         * readlines的第二个参数不指定的话。默认是UTF-8进行读取。想用其他文字code的话就指定第二个参数
         */
        Path path = Paths.get("src/Part9/data_F_1.txt");
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            System.out.println(line);
        }
        /**
         * apple
         * orange
         * banana
         */


    }
}