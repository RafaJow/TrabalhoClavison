package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import model.Usuario;
import model.UsuarioModel;

public class CadastroController {

	@FXML TextField txtNome;
	@FXML TextField txtLogin;
	@FXML PasswordField txtSenha;
	@FXML TextField txtCargo;
	
	private ArrayList<Usuario> usuarios = new ArrayList <Usuario>();
	
	public void cadastrar() {
		try {
			Usuario u = new Usuario();
			usuarios.clear();
			try {
				u.setNome(txtNome.getText());
				if(txtNome.getText().equalsIgnoreCase("") || txtNome.getText().equalsIgnoreCase(" ")) {
					throw new Exception("Campo não pode ser vazio");
				}
				try {
					u.setLogin(txtLogin.getText());
					if(txtLogin.getText().equalsIgnoreCase("") || txtLogin.getText().equalsIgnoreCase(" ")) {
						throw new Exception("Campo não pode ser vazio");
					}
					if(UsuarioModel.compararLogin(txtLogin.getText())) {
						throw new SQLException();
					}
					try {
						u.setSenha(txtSenha.getText());
						if(txtSenha.getText().equalsIgnoreCase("") || txtSenha.getText().equalsIgnoreCase(" ")) {
							throw new Exception("Campo não pode ser vazio");
						}
						try {
							u.setCargo(txtCargo.getText());
							if(txtCargo.getText().equalsIgnoreCase("") || txtCargo.getText().equalsIgnoreCase(" ")) {
								throw new Exception("Campo não pode ser vazio");
							}
							
							usuarios.add(u);
							UsuarioModel.inserir(usuarios);
							mostrarMensagem("CADASTRADO COM SUCESSO", AlertType.CONFIRMATION);
							
						}catch(Exception e) {
							mostrarMensagem("ERRO NÃO IDENTIFICADO cargo"+e.toString(), AlertType.WARNING);
							e.printStackTrace();
						}
					}catch(Exception e) {
						mostrarMensagem("ERRO NÃO IDENTIFICADO senha"+e.toString(), AlertType.WARNING);
						e.printStackTrace();
					}
				}catch(SQLException e) {
					mostrarMensagem("ERRO SQL, Login invalido"+"\n"+e.toString(), AlertType.WARNING);
					txtLogin.requestFocus();
					txtLogin.selectAll();
				}catch(Exception e) {
					mostrarMensagem("ERRO NÃO IDENTIFICADO login"+e.toString(), AlertType.WARNING);
					e.printStackTrace();
				}
				
			}catch(Exception e) {
				mostrarMensagem("ERRO NÃO IDENTIFICADO nome"+e.toString(), AlertType.WARNING);
				e.printStackTrace();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarMensagem(String msg, AlertType tipo) {
		Alert a = new Alert(tipo);
		a.setHeaderText(null);
		a.setTitle(null);
		a.setContentText(msg);
		a.show();
	}
	
	public void voltar() {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Tela1.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.getStage().setScene(scene);
			Main.getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
