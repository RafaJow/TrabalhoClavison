package application;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import model.Usuario;
import util.Conexao;

public class Tela3Controller {

	@FXML ComboBox<Usuario> cbDestinatario;
	@FXML ComboBox<String> cbUrgencia;
	
	@FXML TextArea txtDescricao;
	
	String usuarioLogado;
	
	public void initialize() {
		inicializarUrgencia();
		inicializarDestinatario();
	}
	
	public void lerArquivo() {
		Properties prop = new Properties();
		try (FileReader fr = new FileReader("conf.properties")) {
			prop.load(fr);
			usuarioLogado = prop.getProperty("Usuario");
			System.out.println("Usuario logado: "+usuarioLogado);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void inicializarDestinatario() {
		lerArquivo();
		try {
			Connection conn = Conexao.getConexao();
			String sql = "Select * from usuario where login != (?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, usuarioLogado);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Usuario u = new Usuario();
				u.setNome(rs.getString("nome"));
				u.setId(rs.getInt("id"));
				cbDestinatario.getItems().add(u);
				System.out.println(u.toString());
			}
			
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inicializarUrgencia() {
		cbUrgencia.getItems().add("Baixa");
		cbUrgencia.getItems().add("Média");
		cbUrgencia.getItems().add("Alta");
	}
	
}
