package Part9;

/**
 * @author liyanpeng
 * @date 2025/5/4
 * @description TODO
 */

/**
 * ✅字节流（Byte Stream）
 * +---------------------+---------------------+
 * | 入力 (Input)        | 出力 (Output)       |
 * +=====================+=====================+
 * | InputStream         | OutputStream        |
 * | (抽象类)            | (抽象类)            |
 * +---------------------+---------------------+
 * | FileInputStream     | FileOutputStream    |  file的byte单位进行读写
 * | DataInputStream     | DataOutputStream    |　基本数据行的数据的读写
 * | ByteArrayInputStream| ByteArrayOutputStream|
 * | FilterInputStream   | FilterOutputStream  |
 * | ObjectInputStream   | ObjectOutputStream  |
 * +---------------------+---------------------+
 * <p>
 * <p>
 * ✅字符流（Char Stream）
 * +---------------------+---------------------+
 * | 入力 (Input)        | 出力 (Output)       |
 * +=====================+=====================+
 * | Reader              | Writer              |
 * | (抽象类)            | (抽象类)            |
 * +---------------------+---------------------+
 * | FileReader          | FileWriter          |  file的char单位进行读写
 * | BufferedReader      | BufferedWriter      |  以char为单位对文字，数组，行，边缓存边读写
 * | CharArrayReader     | CharArrayWriter     |
 * | InputStreamReader   | OutputStreamWriter  |
 * +---------------------+---------------------+
 */
public class B01_Stream {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
    }
}