package com.tehnik.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionJDBC {
    public static PreparedStatement getConnection(String sql) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = null;
        String url = "jdbc:mysql://localhost:3306/webshop?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, "root", "root");
        preparedStatement = connection.prepareStatement(sql);
        return preparedStatement;
    }
}
