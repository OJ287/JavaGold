package Part9;

/**
 * @author liyanpeng
 * @date 2025/5/5
 * @description TODO
 */

import java.io.*;

class Foo implements Serializable {
    int a;

    Foo() {
        a = 10;
        System.out.println("Foo()");
    }
}

class Bar extends Foo {
    int b;

    Bar() {
        b = 10;
        System.out.println("Bar()");
    }
}

public class C03_Serializable_Extends {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        Bar bar = new Bar();
        bar.a = 100;
        bar.b = 100;
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("src/Part9/data_C03_1.txt"));
             ObjectInputStream ois = new ObjectInputStream(
                     new FileInputStream("src/Part9/data_C03_1.txt"))) {
            oos.writeObject(bar); // シリアライズ
            Bar readBar = (Bar) ois.readObject(); // デシリアライズ
            System.out.println("readBar.a + : " + readBar.a);
            System.out.println("readBar.b + : " + readBar.b);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        /**
         * Foo()
         * Bar()
         * readBar.a + : 100
         * readBar.b + : 100
         */
    }
}