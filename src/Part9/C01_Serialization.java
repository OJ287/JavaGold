package Part9;

import java.io.*;

/**
 * @author liyanpeng
 * @date 2025/5/5
 * @description TODO
 */

/**
 * 📦 什么是序列化（Serialization）？
 * 序列化 是指：
 * 👉 把对象的状态（字段的值）转换成可保存或传输的字节流的过程。
 * 这样可以把对象写入文件、数据库，或通过网络传输。
 * 与之相对的是 反序列化（Deserialization），
 * 👉 是将字节流还原为原始对象的过程。
 * <p>
 * 🧩 实现序列化的方式
 * 在 Java 中，只需实现 java.io.Serializable 接口（是个空接口）就可以让对象具备序列化能力：
 * public class Person implements Serializable {
 * private String name;
 * private int age;
 * }
 * <p>
 * ⚠️ 使用 Serializable 接口的注意事项
 * +---------------------------+--------------------------------------------------------------+
 * | 注意点                    | 说明                                                         |
 * +===========================+==============================================================+
 * | static 字段不会被序列化    | static 是类变量，不属于对象状态，序列化时不会保存其值            |
 * +---------------------------+--------------------------------------------------------------+
 * | transient 字段不会被序列化 | transient 修饰的字段在序列化时会被忽略，反序列化后为默认值       |
 * +---------------------------+--------------------------------------------------------------+
 * | serialVersionUID 的使用   | 建议自定义 serialVersionUID，避免类修改后反序列化失败            |
 * +---------------------------+--------------------------------------------------------------+
 * | 引用类型字段必须可序列化  | 如果字段是对象类型，那个对象类也必须实现 Serializable 接口        |
 * +---------------------------+--------------------------------------------------------------+
 * | 版本兼容问题              | 类结构变化时若 serialVersionUID 不一致，会抛出 InvalidClassException |
 * +---------------------------+--------------------------------------------------------------+
 * | 可自定义序列化过程        | 可通过定义 writeObject() 和 readObject() 方法自定义序列化行为     |
 * +---------------------------+--------------------------------------------------------------+
 * <p>
 * ❌ 为何 static 字段不会被序列化？
 * static 表示“类的共享变量”，它属于类而不是对象，所以不会随对象被保存。
 * 序列化的本质是保存“对象状态”，类变量并不是对象状态的一部分。
 */
public class C01_Serialization {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        Employee writeEmp = new Employee(100, "tanaka");
        Employee writeEmp2 = new Employee(100000000, "th");
        C01 c01 = new C01();
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("src/Part9/data_C01_1.txt"));
             ObjectInputStream ois = new ObjectInputStream(
                     new FileInputStream("src/Part9/data_C01_1.txt"))) {
            oos.writeObject(writeEmp); // 第一次写入 ：Employee对象

            writeEmp.setId(10000);
            writeEmp.setName("second");
            oos.reset();  // 重置流状态（避免引用共享） 没有这句话的话，下面的write就是还是写出的修改之前的writeEmp
            oos.writeObject(writeEmp); // 第二次写入： Employee对象,和第一次是同一个对象，只是修改了对象数据


            oos.writeObject(c01); // 第三次写入 ： C01对象
            oos.writeObject(writeEmp2); // 第三次写入 ： Employee对象，和第一次第二次是不一样的对象

//            C01 c01_read = (C01)ois.readObject(); // 読み込み.
            /**
             * 第一次取出是取出来第一次插入进去的对象Employee，所以报错不能转换
             * Exception in thread "main" java.lang.ClassCastException: class Part9.Employee cannot be cast to class Part9.C01 (Part9.Employee and Part9.C01 are in unnamed module of loader 'app')
             * 	at Part9.C01_Serialization.main(C01_Serialization.java:70)
             */

            Employee readEmp = (Employee) ois.readObject(); // 第一次读入 ： 必须读第一个Employee对象
            System.out.println("ID  : " + readEmp.getId());
            System.out.println("Name: " + readEmp.getName());

            Employee readEmp1 = (Employee) ois.readObject(); // 第二次读入 ： 必须读第二个Employee对象
            System.out.println("ID  : " + readEmp1.getId());
            System.out.println("Name: " + readEmp1.getName());

            C01 c01_read = (C01) ois.readObject(); // 第三次读入 ： 必须在第三次读取C01，所有对象什么顺序插进去的，就什么顺序读取出来
            System.out.println("ID  : " + c01_read.getA());

            Employee readEmp2 = (Employee) ois.readObject(); // 第四次读入 ： 必须读第二个Employee对象
            System.out.println("ID  : " + readEmp2.getId());
            System.out.println("Name: " + readEmp2.getName());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        /**
         * ID  : 100
         * Name: tanaka
         * ID  : 10000
         * Name: second
         * ID  : 10
         * ID  : 100000000
         * Name: th
         */
    }
}

class C01 implements Serializable {
    int a;

    C01() {
        a = 10;
    }

    public int getA() {
        return a;
    }
}

/**
 * 1. 第二次写入的对象和第一次写入的对象一样，只是修改了数据。这样的话 reset() 是必须的吗？为什么？
 * ✅ 必须调用 reset()
 * 原因：
 * Java 的 ObjectOutputStream 会缓存已序列化的对象引用。
 * 如果不调用 reset()，第二次写入 writeEmp 时，流会认为这是同一个对象引用，只会写入一个引用标记，而不会重新序列化对象的新状态。
 * 调用 reset() 会清空缓存，强制重新完整序列化对象的新状态。
 * <p>
 * 2. 第三次写入的对象和第一次、第二次不一样的对象，所以就不需要 reset() 是吗？为什么？
 * ✅ 不需要 reset()
 * 原因：
 * writeEmp2 是一个全新的对象引用，ObjectOutputStream 不会将其与之前写入的对象混淆。
 * 只有重复写入同一对象引用时才需要 reset()，而新对象会直接触发完整序列化。
 * <p>
 * 3. 如果一个文件写出了同一个对象多次，就必须使用 readObject() 按顺序读取出来是吗？
 * ✅ 必须按写入顺序读取
 * 原因：
 * 序列化文件是一个有序的字节流，readObject() 必须严格按写入顺序读取，否则会：
 * 数据错乱（读取到错误类型的对象）
 * 抛出 ClassCastException 或 EOFException
 * <p>
 * 4. 如果一个文件写入多个不同类型的对象，如何读取特定对象？
 * ✅ 必须按顺序读取，不能跳过
 * 原因：
 * 序列化流是顺序访问的，没有“随机访问”能力。如果要读取第 N 个对象，必须先读取前 N-1 个对象。
 * <p>
 * <p>
 * +----------------------+-----------------------------+-----------------------------------------+
 * | 问题场景             | 关键操作                    | 原因                                    |
 * +======================+=============================+=========================================+
 * | 重复写入同一对象      | 必须调用 reset()            | 避免引用共享，强制写入对象的新状态       |
 * +----------------------+-----------------------------+-----------------------------------------+
 * | 写入不同对象          | 无需 reset()                | 新对象引用会直接触发完整序列化           |
 * +----------------------+-----------------------------+-----------------------------------------+
 * | 读取顺序             | 必须严格按写入顺序读取       | 序列化流是严格有序的字节序列             |
 * +----------------------+-----------------------------+-----------------------------------------+
 * | 读取特定类型对象      | 需顺序读完前面的所有对象     | 不支持随机访问，只能顺序读取             |
 * +----------------------+-----------------------------+-----------------------------------------+
 * 1. reset() 的作用
 * - 清空 ObjectOutputStream 的对象引用缓存
 * - 确保重复写入同一对象时，每次都是完整状态快照
 * <p>
 * 2. 读取顺序不可跳过
 * - 序列化文件类似于磁带，必须顺序读取
 * - 尝试跳过对象会导致类型转换错误或数据错乱
 * <p>
 * 3. 混合类型写入时的读取
 * +------------------------+------------------------------+
 * | 写入顺序               | 必须读取顺序                  |
 * +========================+==============================+
 * | Employee → Employee → OOO | 读2次Employee再读OOO        |
 * +------------------------+------------------------------+
 * | 直接读取OOO            | ❌ 会抛出 ClassCastException  |
 * +------------------------+------------------------------+
 */