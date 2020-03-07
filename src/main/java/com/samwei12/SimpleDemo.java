package com.samwei12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author xiaosen.dxs
 * @date 2020/3/7 6:37 PM
 */
public class SimpleDemo {
    public static void main(String[] args) throws SQLException {
        //simple();
        resultSetDemo();
    }

    private static void simple() throws SQLException {
        //1. 导入驱动jar包
        // 这一步通过 maven 解决
        //2. 注册驱动，可以省略
        //Class.forName("com.mysql.cj.jdbc.Driver");
        //3. 获取数据库连接对象 Connection
        final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/samwei12", "root",
            "root");
        //4. 定义sql
        String sql = "update bank set money=1000 where name='张三'";
        //    5. 获取执行sql语句的对象 Statement
        final Statement statement = connection.createStatement();
        //			6. 执行sql，接受返回结果
        final int count = statement.executeUpdate(sql);
        //7. 处理结果
        System.out.println(count);

        //			8. 释放资源
        statement.close();
        connection.close();
    }

    private static void resultSetDemo() throws SQLException {
        //1. 导入驱动jar包
        // 这一步通过 maven 解决
        //2. 注册驱动，可以省略
        //Class.forName("com.mysql.cj.jdbc.Driver");
        //3. 获取数据库连接对象 Connection
        final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/samwei12", "root",
            "root");
        //4. 定义sql
        String sql = "select * from bank";
        //    5. 获取执行sql语句的对象 Statement
        final Statement statement = connection.createStatement();
        //			6. 执行sql，接受返回结果
        final ResultSet resultSet = statement.executeQuery(sql);
        //7. 处理结果
        while (resultSet.next()) {
            final double money = resultSet.getDouble("money");
            final String name = resultSet.getString("name");
            System.out.println(name+"银行账户："+money);
        }

        //			8. 释放资源
        statement.close();
        connection.close();
    }
}
