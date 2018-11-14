package application;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Chamado;
import model.Usuario;
import util.Conexao;

public class Tela2Controller {
	
	@FXML TabPane tabAberto;
	
	@FXML TableView<Chamado> tblEfetuados;
	
	@FXML TableColumn<Chamado, Usuario> colEfDestinatario;
	@FXML TableColumn<Chamado, String> colEfDescricao;
	@FXML TableColumn<Chamado, String> colEfUrgencia;
	@FXML TableColumn<Chamado, String> colEfEfetuado;
	@FXML TableColumn<Chamado, String> colEfStatus;
	
	
	@FXML TableView<Chamado> tblRecebidos;
	
	@FXML TableColumn<Chamado, String> colReRemetente;
	@FXML TableColumn<Chamado, String> colReDescricao;
	@FXML TableColumn<Chamado, String> colReUrgencia;
	@FXML TableColumn<Chamado, String> colReEfetuado;
	
	String usuario;
	
	public void initialize() {
		lerArquivo();
	}
	
	public void inicializarTabelaEfetuados() {
		colEfDestinatario.setCellValueFactory(cellData -> cellData.getValue().getDestinatario());
		
	}
	
	public void inicializarTabelaRecebidos() {
		
	}
	
	@FXML
	public void listarChamadoEfetuados() {
		 tblEfetuados.getItems().clear();
		try {
			Connection conn = Conexao.getConexao();
			
			String sql = "select * from chamado where login = (?)  ";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, usuario);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Chamado c = new Chamado();
				c.setDataCriacao(rs.getDate("dataCriacao")+"");
				c.setDescricao(rs.getString("descricao"));
				c.setDestinatario(rs.getObject("destinatario"));
				c.setId(rs.getInt("id"));
				c.setRemetente(rs.getObject("remetente"));
				c.setStatus(rs.getBoolean("status"));
				c.setUrgencia(rs.getString("urgencia"));
				tblEfetuados.getItems().add(c);
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void lerArquivo() {
		Properties prop = new Properties();
		try (FileReader fr = new FileReader("conf.properties")) {
			prop.load(fr);
			usuario = prop.getProperty("Usuario");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
