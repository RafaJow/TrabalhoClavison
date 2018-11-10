package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.Conexao;

public class UsuarioModel {

	public static void inserir(ArrayList<Usuario> usuarios) {
		try {
			
			Connection conn = Conexao.getConexao();
			
			String sql = "insert into usuario (nome, login, senha, cargo) values (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			for (Usuario u : usuarios) {
				ps.setString(1, u.getNome());
				ps.setString(2, u.getLogin());
				ps.setString(3, u.getSenha());
				ps.setString(4, u.getCargo());
				ps.executeUpdate();
				
			}
			
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean compararLogin(String login) {
		try {
			
			Connection conn = Conexao.getConexao();
		
			String sql = "select * from usuario where login = (?) ";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				String loginRS = rs.getString("login");
				if(loginRS.equalsIgnoreCase(login)) {
					conn.close();
					return true;
				}
			}
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean compararSenha(String login, String senha) {
		try {
			
			Connection conn = Conexao.getConexao();
			
			String sql = "select * from usuario where login = (?) and senha = (?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, login);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String loginRS = rs.getString("login");
				String senhaRS = rs.getString("senha");
				if(loginRS.equalsIgnoreCase(login) && senhaRS.equals(senha)) {
					conn.close();
					return true;
				}
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
