package Part11;

import java.util.ListResourceBundle;

/**
 * @author liyanpeng
 * @date 2025/5/9
 * @description TODO
 */
public class MyResources extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {{"send", "送信"},
                {"cancel", "取消"}};
        return contents;
    }
}
