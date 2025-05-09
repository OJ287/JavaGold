package Part10;

/**
 * @author liyanpeng
 * @date 2025/5/9
 * @description TODO
 */

/**
 * 使用DataSource进行数据库连接详解
 * 一、Web系统中连接数据库的两种主要方法
 * 在Java Web系统中，连接数据库主要有两种方式：
 * DriverManager方式（基础方式）
 * 直接使用JDBC的DriverManager获取连接
 * 每次请求都创建新连接，性能较差
 * 适合简单应用或测试环境
 * DataSource方式（推荐方式）
 * 通过数据源获取连接
 * 通常配合连接池使用
 * 适合生产环境，性能更好
 * 支持分布式事务等高级特性
 * <p>
 * <p>
 * 二、DataSource进行DB连接的详细步骤与示例
 * 一. 基本使用步骤
 * 1.添加依赖
 * 2.配置DataSource
 * 3.获取连接
 * 4.执行SQL
 * 5.释放资源
 * 二. 示例代码（以HikariCP连接池为例）
 * import com.zaxxer.hikari.HikariConfig;
 * import com.zaxxer.hikari.HikariDataSource;
 * import java.sql.Connection;
 * import java.sql.PreparedStatement;
 * import java.sql.ResultSet;
 * import java.sql.SQLException;
 * <p>
 * public class DataSourceExample {
 * // 1. 定义DataSource变量
 * private static HikariDataSource dataSource;
 * <p>
 * static {
 * // 2. 配置连接池
 * HikariConfig config = new HikariConfig();
 * config.setJdbcUrl("jdbc:mysql://localhost:3306/golddb?serverTimezone=Asia/Tokyo");
 * config.setUsername("root");
 * config.setPassword("rieh1111");
 * config.setDriverClassName("com.mysql.cj.jdbc.Driver");
 * <p>
 * // 3. 连接池优化配置
 * config.setMaximumPoolSize(20);      // 最大连接数
 * config.setMinimumIdle(5);           // 最小空闲连接
 * config.setConnectionTimeout(30000); // 连接超时时间(ms)
 * config.setIdleTimeout(600000);      // 空闲连接超时时间(ms)
 * config.setMaxLifetime(1800000);     // 连接最大生命周期(ms)
 * <p>
 * // 4. 创建DataSource实例
 * dataSource = new HikariDataSource(config);
 * }
 * <p>
 * public void queryData() {
 * Connection conn = null;
 * PreparedStatement pstmt = null;
 * ResultSet rs = null;
 * <p>
 * try {
 * // 5. 从连接池获取连接
 * conn = dataSource.getConnection();
 * <p>
 * // 6. 执行SQL查询
 * pstmt = conn.prepareStatement("SELECT * FROM department");
 * rs = pstmt.executeQuery();
 * <p>
 * // 7. 处理结果集
 * while(rs.next()) {
 * System.out.println(rs.getInt("dept_code") + ": " + rs.getString("dept_name"));
 * }
 * } catch (SQLException e) {
 * e.printStackTrace();
 * } finally {
 * // 8. 释放资源（连接会返回连接池，不是真正关闭）
 * if (rs != null) try { rs.close(); } catch (SQLException e) {  忽略  }
 * if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {  忽略  }
 * if (conn != null) try { conn.close(); } catch (SQLException e) {  忽略  }
 * }
 * }
 * }
 * <p>
 * 三. 各步骤详解
 * 1.添加依赖（Maven示例）
 * <!-- HikariCP连接池 -->
 * <dependency>
 * <groupId>com.zaxxer</groupId>
 * <artifactId>HikariCP</artifactId>
 * <version>5.0.1</version>
 * </dependency>
 * <!-- MySQL驱动 -->
 * <dependency>
 * <groupId>mysql</groupId>
 * <artifactId>mysql-connector-java</artifactId>
 * <version>8.0.29</version>
 * </dependency>
 * <p>
 * . 2.配置DataSource
 * 设置数据库URL、用户名、密码等基本信息
 * 配置连接池参数（如最大连接数、超时时间等）
 * <p>
 * 3.获取连接
 * 通过dataSource.getConnection()获取连接
 * 实际上是从连接池中获取一个可用连接
 * <p>
 * 4.执行SQL
 * 与普通JDBC操作相同
 * <p>
 * 5.释放资源
 * 调用close()方法时，连接会返回连接池而非真正关闭
 */

public class E01_DataSource_connDB {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
    }
}

/**
 * 三、DataSource在Web系统中的应用
 * 1. 作为Web系统架构的一部分
 * 1.初始化时机：
 * 通常在应用启动时初始化（如ServletContextListener）
 * 整个应用共享同一个DataSource实例
 * 2.典型架构：
 * Web层(Controller) → 服务层(Service) → 数据访问层(DAO)
 * ↑
 * DataSource
 * 3.依赖注入：
 * 在Spring等框架中，DataSource通常被注入到DAO层
 * 通过@Autowired或XML配置
 * <p>
 * 2. 性能优化实现
 * 1.连接池的优势：
 * 连接复用：避免频繁创建/销毁连接的开销
 * 资源控制：防止连接泄露和过度消耗
 * 智能管理：自动检测和淘汰无效连接
 * 2.关键优化参数：
 * config.setMaximumPoolSize(20);      // 根据系统负载调整
 * config.setMinimumIdle(5);           // 减少连接创建开销
 * config.setConnectionTimeout(30000); // 避免长时间等待
 * config.setIdleTimeout(600000);      // 释放闲置连接
 * config.setMaxLifetime(1800000);     // 定期刷新连接
 * <p>
 * 3.不用连接池的问题：
 * 每次请求都创建新连接，性能极差
 * 容易耗尽数据库连接资源
 * 无法应对高并发场景
 */