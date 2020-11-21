package com.jdbc;

import java.sql.*;

/**
 * @Author: LuzhengLi
 * @Date: 2020/11/21 - 18:49
 * @Description: PACKAGE_NAME
 * @Version: 1.0
 */
public class App {
    public static void main(String[] args) throws Exception {
        String JDBC_URL = "jdbc:mysql://localhost:3306/learnjdbc?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "password";

        // 使用 Statement 查询
//        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT id, grade, name, gender FROM students WHERE gender=1")
//        ) {
//            while (rs.next()) {
//                long id = rs.getLong(1);
//                long grade = rs.getLong(2);
//                String name = rs.getString(3);
//                int gender = rs.getInt(4);
//                System.out.printf("id = %d, grade = %d, name = %s, gender = %d\n", id, grade, name, gender);
//            }
//        }

        // 使用 preparedStatement 查询
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT id, grade, name, gender FROM students WHERE gender=? AND grade=?")) { // 注意这里的占位符 ?
                ps.setObject(1, 1); // 注意：索引从1开始
                ps.setObject(2, 3);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        long id = rs.getLong("id");
                        long grade = rs.getLong("grade");
                        String name = rs.getString("name");
                        int gender = rs.getInt("gender");

                        System.out.printf("id = %d, grade = %d, name = %s, gender = %d\n", id, grade, name, gender);
                    }
                }
            }
        }


    }
}
