package application;

import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import model.Chamado;
import model.ChamadoModel;
import model.Usuario;
import util.Conexao;

public class Tela3Controller {

	@FXML ComboBox<Usuario> cbDestinatario;
	@FXML ComboBox<String> cbUrgencia;
	
	@FXML TextArea txtDescricao;
	
	String usuarioLogado;
	
	private ArrayList<Chamado> chamados = new ArrayList<Chamado>();
	
	public void initialize() {
		inicializarUrgencia();
		inicializarDestinatario();
	}
	
	public void efetuarChamado() {
		chamados.clear();
		try {
			Chamado c = new Chamado();
			
			try {
				c.setDestinatario(cbDestinatario.getSelectionModel().getSelectedItem().toString());
				int id = cbDestinatario.getSelectionModel().getSelectedItem().getId();
				escreveProperties(id+"");
				if(cbDestinatario.getSelectionModel().isEmpty()) {
					throw new Exception("Campo não pode ser vazio");
				}
				
				try {
					c.setUrgencia(cbUrgencia.getSelectionModel().getSelectedItem());
					if(cbUrgencia.getSelectionModel().isEmpty()) {
						throw new Exception("Campo não pode ser vazio");
					}
					try {
						c.setDescricao(txtDescricao.getText());
						if(txtDescricao.getText().isEmpty() || txtDescricao.getText().equalsIgnoreCase(" ")) {
							txtDescricao.requestFocus();
							txtDescricao.selectAll();
							throw new Exception("Campo não pode ser vazio");
						}
						try {
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
							LocalDate hoje = LocalDate.now();
							c.setDataCriacao(dtf.format(hoje));
							try {
								Usuario u = new Usuario();
								u = selecionarRemetente();
								c.setRemetente(u.getId());
								chamados.add(c);
								ChamadoModel.inserirChamado(chamados);
								
								mostrarMensagem("Chamado efetuado com sucesso", AlertType.CONFIRMATION);
								
							}catch (Exception e) {
								mostrarMensagem("Erro não identificado... "+e.getMessage(), AlertType.WARNING);
								e.printStackTrace();
								//remetente
							}
						}catch (Exception e) {
							mostrarMensagem("Erro não identificado... "+e.getMessage(), AlertType.WARNING);
							e.printStackTrace();
							//dataCriacao
						}
					}catch (Exception e) {
						mostrarMensagem("Erro não identificado... "+e.getMessage(), AlertType.WARNING);
						e.printStackTrace();
						//descricao
					}
				}catch (Exception e) {
					mostrarMensagem("Erro não identificado... "+e.getMessage(), AlertType.WARNING);
					e.printStackTrace();
					//urgencia
				}
			}catch(Exception e){
				mostrarMensagem("Erro não identificado... "+e.getMessage(), AlertType.WARNING);
				e.printStackTrace();
				//destinatario
			}
		}catch (Exception e) {
			mostrarMensagem("Erro não identificado... "+e.getMessage(), AlertType.WARNING);
			e.printStackTrace();
			//raiz
		}
	}
	
	public void escreveProperties(String id) {
		Properties properties = new Properties();
		properties.setProperty("Id", id);
		
		try {
			FileWriter fw = new FileWriter("id.properties");
			properties.store(fw, "Arquivo de Id do remetente");
			properties.clone();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Usuario selecionarRemetente() {
		Usuario u = new Usuario();
		try {
			Connection conn = Conexao.getConexao();
			String sql = "Select * from usuario where login = (?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, usuarioLogado);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				u.setNome(rs.getString("nome"));
				u.setId(rs.getInt("id"));
				u.setCargo(rs.getString("cargo"));
				u.setLogin(rs.getString("login"));
				
				//System.out.println(u.toString());
			}
			
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public void lerArquivo() {
		Properties prop = new Properties();
		try (FileReader fr = new FileReader("conf.properties")) {
			prop.load(fr);
			usuarioLogado = prop.getProperty("Usuario");
		//	System.out.println("Usuario logado: "+usuarioLogado);
		}catch (Exception e) {
			mostrarMensagem("Erro não identificado... "+e.getMessage(), AlertType.WARNING);
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
			
			while(rs.next()) {
				Usuario u = new Usuario();
				u.setNome(rs.getString("nome"));
				u.setId(rs.getInt("id"));
				u.setCargo(rs.getString("cargo"));
				u.setLogin(rs.getString("login"));
				cbDestinatario.getItems().add(u);
				//System.out.println(u.toString());
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
	
	public void mostrarMensagem(String msg, AlertType tipo) {
		Alert a = new Alert(tipo);
		a.setHeaderText(null);
		a.setTitle(null);
		a.setContentText(msg);
		a.show();
	}
}
