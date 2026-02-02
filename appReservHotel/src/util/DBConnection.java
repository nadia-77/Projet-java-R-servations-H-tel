/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author lenovo
 */
public class DBConnection {

    public static Connection getConnection() {

        try {
            Properties prop = new Properties();

            InputStream is = DBConnection.class
                    .getClassLoader()
                    .getResourceAsStream("config/config.properties");

            if (is == null) {
                System.out.println("config.properties NOT FOUND in JAR");
                return null;
            }

            prop.load(is);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            System.out.println("config.properties LOADED");
            return DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
      
}
