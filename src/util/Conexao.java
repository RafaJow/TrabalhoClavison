package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	public static Connection getConexao() {
		Connection conn = null;
		try {
			File bd = new File("Trabalho.sqlite");
			if(bd.exists()){
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:Trabalho.sqlite");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
