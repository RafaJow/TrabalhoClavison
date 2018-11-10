package application;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import model.UsuarioModel;

public class Tela1Controller {

	@FXML TextField txtLogin;
	@FXML PasswordField txtSenha;
	
	public void login() {
		try {
			if(txtLogin.getText().equalsIgnoreCase("") || txtLogin.getText().equalsIgnoreCase(" ")) {
				throw new NullPointerException("Campo não pode ser vazio");
			}
			if(UsuarioModel.compararSenha(txtLogin.getText(),txtSenha.getText())) {
				escreveProperties(txtLogin.getText());
				logado();
			}else {
				mostrarMensagem("LOGIN/SENHA INCORRETO",AlertType.CONFIRMATION);
			}
		}catch(NullPointerException e) {
			mostrarMensagem("Campo vazio", AlertType.WARNING);
			e.printStackTrace();
		}catch(Exception e) {
			mostrarMensagem(null,AlertType.WARNING);
			e.printStackTrace();
		}
	}
	
	public void escreveProperties(String usuario) {
		Properties properties = new Properties();
		properties.setProperty("Usuario", usuario);
		
		try {
			FileWriter fw = new FileWriter("conf.properties");
			properties.store(fw, "Arquivo de conf");
			properties.clone();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void logado() {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Principal.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.getStage().setScene(scene);
			Main.getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cadastro() {		
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Cadastro.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.getStage().setScene(scene);
			Main.getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sair() {
		System.exit(0);
	}
	
	public void mostrarMensagem(String msg, AlertType tipo) {
		Alert a = new Alert(tipo);
		a.setHeaderText(null);
		a.setTitle(null);
		a.setContentText(msg);
		a.show();
	}
}
