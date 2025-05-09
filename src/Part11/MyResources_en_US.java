package Part11;

import java.util.ListResourceBundle;

/**
 * @author liyanpeng
 * @date 2025/5/9
 * @description TODO
 */
public class MyResources_en_US extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {{"send", "send"},
                {"cancel", "cancel"}};
        return contents;
    }
}
