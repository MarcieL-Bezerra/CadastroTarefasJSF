package com.marcielbezerra.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class conexao {

	public String url;
	public String usuario;
	public String senha;
	public Connection con;
 
	public conexao() {
		url = "jdbc:postgresql://localhost:5432/tarefadb";
		usuario = "postgres";
		senha = "123456";

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, usuario, senha);

			System.out.println("conectado ao banco");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public int executaSQL(String sql) {
		
		try {
			Statement stm = con.createStatement();
			int res = stm.executeUpdate(sql);
			con.close();
			return res;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
public ResultSet executaBusca(String sql) {
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			con.close();
			return rs;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getUsuario() {
	return usuario;
}
public void setUsuario(String usuario) {
	this.usuario = usuario;
}
public String getSenha() {
	return senha;
}
public void setSenha(String senha) {
	this.senha = senha;
}
public Connection getCon() {
	return con;
}
public void setCon(Connection con) {
	this.con = con;
}

}
