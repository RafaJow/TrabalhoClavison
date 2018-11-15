package model;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

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
	
	public static String buscarNomePorId() {
		String nome="";
		try {
			Connection conn = Conexao.getConexao();
		
			String sql = "select * from usuario where id = (?) ";
			PreparedStatement ps = conn.prepareStatement(sql);
			int id = Integer.parseInt(lerArquivoId());
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				nome = rs.getString("nome");
				conn.close();
				return nome;
			}
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return nome;
	}
	
	public static String lerArquivoId() {
		Properties prop = new Properties();
		String id="";
		try (FileReader fr = new FileReader("id.properties")) {
			prop.load(fr);
			id = prop.getProperty("Id");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
}
