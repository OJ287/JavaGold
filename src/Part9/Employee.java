package Part9;

/**
 * @author liyanpeng
 * @date 2025/5/5
 * @description TODO
 */

import java.io.Serializable;

// クラス定義時にimplements Serializableと記述
public class Employee implements Serializable {
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Serializableインタフェースはメソッドをもたないため
    // オーバーライドしなければならないメソッドはない
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
