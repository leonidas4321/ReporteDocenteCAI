package com.mx.chaos.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConection {
	
	private Connection con = null;

    private final String url = "jdbc:sqlite:C:\\ZK3.0\\ZKTime.Net\\ZKTimeNet.db";

    public Connection getConexion() {

        try {
        	Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(this.url);
            if(con != null) {
                System.out.println("Successfully connected to the access database");
            }
        } catch(SQLException | ClassNotFoundException e)
        {
            System.err.println(e);
        }
      return con;  
    }



}
