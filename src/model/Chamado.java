package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Chamado {

	private Usuario destinatario = new Usuario();
	private Usuario remetente = new Usuario();
	private StringProperty descricao = new SimpleStringProperty("");
	private StringProperty urgencia = new SimpleStringProperty("");
	private StringProperty dataCriacao = new SimpleStringProperty("");
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private BooleanProperty status = new SimpleBooleanProperty(false);
	private StringProperty destinatarioNome = new SimpleStringProperty("");
	private StringProperty remetenteNome = new SimpleStringProperty("");
	
	
	
	
	public Usuario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Object destinatario) {
		this.destinatario = (Usuario) destinatario;
	}

	public Usuario getRemetente() {
		return remetente;
	}

	public void setRemetente(Object remetente) {
		this.remetente = (Usuario) remetente;
	}

	public final StringProperty descricaoProperty() {
		return this.descricao;
	}
	
	public final String getDescricao() {
		return this.descricaoProperty().get();
	}
	
	public final void setDescricao(final String descricao) {
		this.descricaoProperty().set(descricao);
	}
	
	public final StringProperty urgenciaProperty() {
		return this.urgencia;
	}
	
	public final String getUrgencia() {
		return this.urgenciaProperty().get();
	}
	
	public final void setUrgencia(final String urgencia) {
		this.urgenciaProperty().set(urgencia);
	}
	
	public final StringProperty dataCriacaoProperty() {
		return this.dataCriacao;
	}
	
	public final String getDataCriacao() {
		return this.dataCriacaoProperty().get();
	}
	
	public final void setDataCriacao(String date) {
		this.dataCriacaoProperty().set(date);
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

	public final BooleanProperty statusProperty() {
		return this.status;
	}
	

	public final boolean isStatus() {
		return this.statusProperty().get();
	}
	

	public final void setStatus(final boolean status) {
		this.statusProperty().set(status);
	}
	
}
