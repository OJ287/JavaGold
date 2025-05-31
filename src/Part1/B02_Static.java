package Part1;

/**
 * @author liyanpeng
 * @date 2025/5/22
 * @description TODO
 */

class Parent {
    static int s = 1;    // 静态字段（类级别）
    int x = 10;          // 非静态字段

    public static void sMothed() {
        System.out.println("Parent.sMothed");
    }

    public void xMothed() {
        System.out.println("Parent.xMothed");
    }
}

class Child extends Parent {
    static int s = 2;    // 隐藏 Parent.s
    int x = 20;          // 隐藏 Parent.x

    public static void sMothed() {
        System.out.println("Child.sMothed");
    }

    public void xMothed() {
        System.out.println("Child.xMothed");
    }
}

public class B02_Static {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO

        Parent obj1 = new Child();// 除了非static方法以外，都是调用的父类成员
        System.out.println(Parent.s);
        System.out.println(obj1.x);
        Parent.sMothed();
        Child.sMothed();
        obj1.xMothed();
        /**
         * 1
         * 10
         * Parent.sMothed
         * Child.sMothed
         * Child.xMothed
         */

        System.out.println("------------");
        Child obj2 = new Child();
        System.out.println(Child.s);
        System.out.println(obj2.x);
        Parent.sMothed();
        Child.sMothed();
        obj2.xMothed();
        /**
         * 2
         * 20
         * Parent.sMothed
         * Child.sMothed
         * Child.xMothed
         */
    }
}