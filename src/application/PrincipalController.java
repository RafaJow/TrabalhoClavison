package application;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class PrincipalController {

	@FXML TabPane tabPane;
	@FXML Label lblUsuario;
	String usuario;
	
	public void initialize() {
		lerArquivo();
		lblUsuario.setText(usuario+"");
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
	
	public void deslogar() {
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
	
	@FXML
	public void abreTela2() {
		abreTab("Pagina", "Tela2.fxml");
	}
	
	@FXML
	public void abreTela3() {
		abreTab("Chamado", "Tela3.fxml");
	}
	
	private Tab tabAberta(String titulo) {
		for(Tab tb : tabPane.getTabs()) {
			if(tb.getText().equalsIgnoreCase(titulo)) {
				return tb;
			}
		}
		return null;
	}
	
	private void selecionaTab(Tab tab) {
		tabPane.getSelectionModel().select(tab);
	}
	
	private void abreTab(String titulo, String path) {
		try {
			Tab tab = tabAberta(titulo);
			if(tab==null) {
				tab = new Tab(titulo);
				tab.setClosable(true);
				tabPane.getTabs().add(tab);
				tab.setContent((Node) FXMLLoader.load(getClass().getResource(path)));
			}
			selecionaTab(tab);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
