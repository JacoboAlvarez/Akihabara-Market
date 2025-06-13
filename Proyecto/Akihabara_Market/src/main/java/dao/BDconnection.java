package dao;

import java.sql.Connection;
import java.sql.DriverManager;

import config.ConfigLoader;

public class BDconnection {

    // Método estático para conectar a la base de datos usando ConfigLoader
	 public static Connection getConexion() throws Exception {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        // Usando versión ESTÁTICA 
	        String contra = ConfigLoader.getPropertyAsStatic("db.pass");
	        String user = ConfigLoader.getPropertyAsStatic("db.user");
	        String url = ConfigLoader.getPropertyAsStatic("db.URL");
	        
	        
	        return DriverManager.getConnection(url, user, contra);
	    }
}

