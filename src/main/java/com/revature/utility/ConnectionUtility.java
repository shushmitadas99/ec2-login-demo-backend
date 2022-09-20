package com.revature.utility;

import org.postgresql.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUtility {

    //static method that returns a brand-new Connection object
    public static Connection createConnection() throws SQLException {
        //Dotenv dotenv = Dotenv.load();

        //1: Register the driver w/ the DriverManager
        Driver postgresDriver = new Driver(); //pass in Driver object into DriverManager
        DriverManager.registerDriver(postgresDriver);

        //For local dotenv connection
        //String url = dotenv.get("url");
        //String user = dotenv.get("user");
        //String password = dotenv.get("password");

        //For EC2 instance connection using system variables
        String url = System.getenv("db_url");
        String username = System.getenv("db_username");
        String password = System.getenv("db_password");

        //2: Grab a Connection object
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }
}
