package com.samwei12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author xiaosen.dxs
 * @date 2020/3/7 8:30 PM
 */
public class SQLInjectDemo {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        final String userName = sc.nextLine();
        System.out.println("请输入用户密码：");
        final String password = sc.nextLine();
        loginWithoutInjection(userName, password);
    }

    public static void login(String userName, String password) throws SQLException {
        final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/samwei12", "root",
            "root");
        String sql = "select * from user where name='"+userName+"' and password='"+password+"'";
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            System.out.println("登录成功，欢迎您：" + userName);
        } else {
            System.out.println("登录失败");
        }

        statement.close();
        connection.close();
    }

    public static void loginWithoutInjection(String userName, String password) throws SQLException {
        final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/samwei12", "root",
            "root");
        // 1. 构造一个带参数的 SQL 语句，这里不需要加单引号了，而是使用占位符 ？
        String sql = "select * from user where name=? and password=?";
        // 2. 获取 PreparedStatement 对象
        final PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 3. 设置参数，这里需要注意的是索引是从 1 开始的，而不是 0
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, userName);
        // 4. 执行参数化 SQL 语句，这里由于 sql 语句已经准备好了，不再需要传入参数，直接执行即可
        final ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("登录成功，欢迎您：" + userName);
        } else {
            System.out.println("登录失败");
        }

        // 5. 关闭资源
        preparedStatement.close();
        connection.close();
    }
}
