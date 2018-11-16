package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Chamado {

	private IntegerProperty remetente = new SimpleIntegerProperty(0);
	private IntegerProperty destinatario = new SimpleIntegerProperty(0);
	private StringProperty descricao = new SimpleStringProperty("");
	private StringProperty urgencia = new SimpleStringProperty("");
	private StringProperty dataCriacao = new SimpleStringProperty("");
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private BooleanProperty status = new SimpleBooleanProperty(false);
	private StringProperty statusStr = new SimpleStringProperty("");
	private StringProperty remetenteNome = new SimpleStringProperty("");
	private StringProperty destinatarioNome = new SimpleStringProperty("");
	
	

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

	public final StringProperty statusStrProperty() {
		return this.statusStr;
	}
	

	public final String getStatusStr() {
		return this.statusStrProperty().get();
	}
	

	public final void setStatusStr(final String statusStr) {
		this.statusStrProperty().set(statusStr);
	}
	
	public Chamado() {
		if(!isStatus()) {
			setStatusStr("Nao resolvido");
		}else {
			setStatusStr("Resolvido");
		}
		
		//setRemetenteNome(ChamadoModel.buscarNomePorId());
	}

	public final IntegerProperty remetenteProperty() {
		return this.remetente;
	}
	

	public final int getRemetente() {
		return this.remetenteProperty().get();
	}
	

	public final void setRemetente(final int remetente) {
		this.remetenteProperty().set(remetente);
	}

	public final StringProperty remetenteNomeProperty() {
		return this.remetenteNome;
	}
	

	public final String getRemetenteNome() {
		return this.remetenteNomeProperty().get();
	}
	

	public final void setRemetenteNome(final String remetenteNome) {
		this.remetenteNomeProperty().set(remetenteNome);
	}

	public final IntegerProperty destinatarioProperty() {
		return this.destinatario;
	}
	

	public final int getDestinatario() {
		return this.destinatarioProperty().get();
	}
	

	public final void setDestinatario(final int destinatario) {
		this.destinatarioProperty().set(destinatario);
	}
	

	public final StringProperty destinatarioNomeProperty() {
		return this.destinatarioNome;
	}
	

	public final String getDestinatarioNome() {
		return this.destinatarioNomeProperty().get();
	}
	

	public final void setDestinatarioNome(final String destinatarioNome) {
		this.destinatarioNomeProperty().set(destinatarioNome);
	}
	
	
	
	


	
	

}
