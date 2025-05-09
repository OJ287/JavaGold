package Part11;

import java.util.ResourceBundle;

/**
 * @author liyanpeng
 * @date 2025/5/9
 * @description TODO
 */
public class B04_ListResourceBundle {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        ResourceBundle obj
                = ResourceBundle.getBundle("Part11.MyResourcesB04");
//        Long data1 = (Long)obj.getObject("data1");//OK
        long data1 = (Long) obj.getObject("data1");
        Integer data2 = (Integer) obj.getObject("data2");
        int[] data3 = (int[]) obj.getObject("data3");
        System.out.println("data1 : " + data1);
        System.out.println("data2 : " + data2);
        System.out.print("data3 : ");
        for (int i : data3) {
            System.out.print(i + " ");
        }
        /**
         * data1 : 10000
         * data2 : 500
         * data3 : 10 20 30
         */
    }
}