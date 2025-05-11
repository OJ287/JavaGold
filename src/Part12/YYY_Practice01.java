package Part12;

/**
 * @author liyanpeng
 * @date 2025/5/11
 * @description TODO
 */


import java.util.HashMap;
import java.util.Map;

/**
 * 答え：
 * 1:B
 * 2:E→C
 * 3:D
 * 4:CD
 * 5:D
 * 6:F
 * 7:B→E
 * 8:BE
 */

public class YYY_Practice01 {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        /**
         * 7
         * ❎循环map.keySet()的新得到的set对象而改动map对象不行吗
         *    循环和改动的对象不一样吧？？？
         * ✅这个理解是错误的，map.keySet()没有新建对象，也不是副本，只是视图。
         *    和原本map数据共用，修改会互相影响
         *
         * ✅最佳实践建议
         *    遍历时修改集合：
         *      必须使用迭代器自身的 remove() 方法
         *
         * ✅遇到的错误是 ConcurrentModificationException，这是 Java 集合框架的一个关键设计约束。
         *   虽然确实是通过 keySet() 获取了新的 Set 对象，但根本问题在于：
         *   1. 底层机制
         *     map.keySet() 返回的是 视图（View），而非独立的副本
         *     这个视图与原始 HashMap 共享数据存储结构
         *     当通过迭代器遍历时（增强 for 循环底层使用迭代器），会检查集合的 修改计数（modCount）
         *   2. 错误触发流程
         *   for(Integer key : map.keySet()){  // 隐含创建迭代器
         *     if(key == 2)
         *         map.remove(key); // 此处修改了原始map的结构
         *                         // 导致迭代器的expectedModCount != modCount
         * }
         *
         * 3. 关键概念澄清
         * 我的理解	                实际情况
         * "操作的是不同的对象"	    keySet() 返回的是依赖原始map的视图，结构修改会相互影响
         * "只是循环keySet的副本"	    没有创建副本，返回的是实时视图（节省内存的设计）
         *
         *底层原理图示
         *HashMap
         * ├── table: [Entry, Entry, Entry...]  ← 实际存储结构
         * └── keySetView
         *     └── iterator() → 检查modCount
         *
         * 当直接调用map.remove():
         *   修改table结构 → modCount++ → 迭代器检测到不一致
         *
         */
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "tanaka");
        map.put(2, "sato");
        map.put(3, "suzuki");
        for (Integer key : map.keySet()) {//Exception in thread "main" java.util.ConcurrentModificationException
            if (key == 2) map.remove(key);
        }
        System.out.println(map);
    }
}