/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.util.database;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * creer une connexion sous forme d'un singleton, afin de creer le schema, 
 * @author lenovo
 */
public class MysqlConnection {
    private Connection connection;
    private static MysqlConnection INSTANCE;
    
    private MysqlConnection() {
        try {
            final String url = "jdbc:mysql://" + Settings.getDISTINATION_MYSQL() + ":3306/information_schema";
            connection = (Connection) DriverManager.getConnection(url, Settings.getUSERNAME(), Settings.getPASSWORD());
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
    }

    public static MysqlConnection getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MysqlConnection();
        }
        return INSTANCE;
    }

    public Connection getConnection() {
        return this.connection;
    }

}
