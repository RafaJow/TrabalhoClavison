package application;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Chamado;
import model.ChamadoModel;
import util.Conexao;

public class Tela2Controller {
	
	@FXML TabPane tabAberto;
	
	@FXML TableView<Chamado> tblEfetuados;
	
	@FXML TableColumn<Chamado, String> colEfDestinatario;
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
	
	private ArrayList<Chamado> chamadosEfetuados = new ArrayList<Chamado>();
	private ArrayList<Chamado> chamadosRecebidos = new ArrayList<Chamado>();
	
	public void initialize() {
		lerArquivo();
		inicializarTabelaEfetuados();
		listarChamadosEfetuados();
		inicializarTabelaRecebidos();
		listarChamadosRecebidos();
	}
	
	public void inicializarTabelaEfetuados() {
		colEfDestinatario.setCellValueFactory(cellData -> cellData.getValue().destinatarioProperty());
		colEfDescricao.setCellValueFactory(cellData -> cellData.getValue().descricaoProperty());
		colEfUrgencia.setCellValueFactory(cellData -> cellData.getValue().urgenciaProperty());
		colEfEfetuado.setCellValueFactory(cellData -> cellData.getValue().dataCriacaoProperty());
		colEfStatus.setCellValueFactory(cellData -> cellData.getValue().statusStrProperty());
		
	}
	
	public void inicializarTabelaRecebidos() {
		colReRemetente.setCellValueFactory(cellData -> cellData.getValue().remetenteNomeProperty());
		colReDescricao.setCellValueFactory(cellData -> cellData.getValue().descricaoProperty());
		colReUrgencia.setCellValueFactory(cellData -> cellData.getValue().urgenciaProperty());
		colReEfetuado.setCellValueFactory(cellData -> cellData.getValue().dataCriacaoProperty());
	}
	
	@FXML
	public void listarChamadosRecebidos() {
		chamadosRecebidos.clear();
		try {
			Connection conn = Conexao.getConexao();
			String sql = "select * from chamado where destinatario = (?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			int id = buscarIdDoUsuarioLogado();
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Chamado c = new Chamado();
				c.setDataCriacao(rs.getString("dataCriacao")+"");
				c.setDescricao(rs.getString("descricao"));
				c.setDestinatario(rs.getString("destinatario"));
				c.setId(rs.getInt("id"));
				c.setRemetente(rs.getInt("remetente"));
				c.setStatus(rs.getBoolean("status"));
				c.setUrgencia(rs.getString("urgencia"));
				c.setRemetenteNome(ChamadoModel.buscarNomePorId(c.getRemetente()));
				System.out.println("Nome top "+c.getRemetenteNome());
				chamadosRecebidos.add(c);
			}
			for (Chamado c : chamadosRecebidos) {
				tblRecebidos.getItems().add(c);
			}
			System.out.println("ID recebidos "+id);
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void listarChamadosEfetuados() {
		chamadosEfetuados.clear();
		try {
			Connection conn = Conexao.getConexao();
			String sql = "select * from chamado where remetente = (?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			int id = buscarIdDoUsuarioLogado();
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Chamado c = new Chamado();
				c.setDataCriacao(rs.getString("dataCriacao")+"");
				c.setDescricao(rs.getString("descricao"));
				c.setDestinatario(rs.getString("destinatario"));
				c.setId(rs.getInt("id"));
				c.setRemetente(rs.getInt("remetente"));
				c.setStatus(rs.getBoolean("status"));
				c.setUrgencia(rs.getString("urgencia"));
				//System.out.println(rs.getString("urgencia"));
				chamadosEfetuados.add(c);
			}
			for (Chamado c : chamadosEfetuados) {
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
	
	public int buscarIdDoUsuarioLogado() {
		int id=0;
		try {
			Connection conn = Conexao.getConexao();
		
			String sql = "select * from usuario where login = (?) ";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, usuario);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
				conn.close();
			//	System.out.println("ID "+id);
				return id;
			}
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return id;
		
	}
}
