package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import util.Conexao;

public class ChamadoModel {
	
	public static void inserirChamado(ArrayList<Chamado> chamados) {
		try {
			Connection conn = Conexao.getConexao();
			
			String sql = "insert into chamado (destinatario, remetente, descricao, urgencia, dataCriacao, status) values (?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			for (Chamado c : chamados) {
				ps.setString(1, c.getDestinatario()+"");
				ps.setString(2, c.getRemetente()+"");
				ps.setString(3, c.getDescricao());
				ps.setString(4, c.getUrgencia());
				ps.setString(5, c.getDataCriacao());
				ps.setBoolean(6, c.isStatus());
				ps.executeUpdate();
				
			}
			
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
