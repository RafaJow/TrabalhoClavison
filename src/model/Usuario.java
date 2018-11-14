package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {

	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty senha = new SimpleStringProperty("");
	private StringProperty cargo = new SimpleStringProperty("");
	private StringProperty login = new SimpleStringProperty("");
	private IntegerProperty id = new SimpleIntegerProperty(0);
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getNome()+" - "+getCargo();
	}
	
	public final StringProperty nomeProperty() {
		return this.nome;
	}
	
	public final String getNome() {
		return this.nomeProperty().get();
	}
	
	public final void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}
	
	public final StringProperty senhaProperty() {
		return this.senha;
	}
	
	public final String getSenha() {
		return this.senhaProperty().get();
	}
	
	public final void setSenha(final String senha) {
		this.senhaProperty().set(senha);
	}

	public final StringProperty cargoProperty() {
		return this.cargo;
	}
	
	public final String getCargo() {
		return this.cargoProperty().get();
	}
	
	public final void setCargo(final String cargo) {
		this.cargoProperty().set(cargo);
	}
	
	public final StringProperty loginProperty() {
		return this.login;
	}
	
	public final String getLogin() {
		return this.loginProperty().get();
	}
	
	public final void setLogin(final String login) {
		this.loginProperty().set(login);
	}

	public final IntegerProperty idProperty() {
		return this.id;
	}
	

	public final int getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	
	
	
	
	
}
