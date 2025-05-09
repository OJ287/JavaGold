package Part11;

import java.util.ListResourceBundle;

/**
 * @author liyanpeng
 * @date 2025/5/9
 * @description TODO
 */
public class MyResourcesB04 extends ListResourceBundle {
    protected Object[][] getContents() {
        Long data1 = 10000L;
        Integer data2 = 500;
        int[] data3 = {10, 20, 30};
        Object[][] contents =
                {{"data1", data1}, {"data2", data2}, {"data3", data3}};
        return contents;
    }
}
