/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab;
// enkele imports

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_createStatement_readNullValues {

    private static final String URL = "jdbc:mysql://localhost/tuincentrum";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String SELECT_SQL
            = "select naam,aantalkinderen from leveranciers order by naam";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_SQL)) {
            while (resultSet.next()) {
                System.out.print(resultSet.getString("naam") + ' ');
                int aantalKinderen = resultSet.getInt("aantalkinderen");
                System.out.println(resultSet.wasNull() ? "onbekend" : aantalKinderen);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
