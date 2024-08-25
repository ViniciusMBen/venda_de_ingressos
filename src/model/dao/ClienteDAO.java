package model.dao;

import java.util.ArrayList;

import model.beans.Cliente;
import java.sql.*;
public class ClienteDAO {
        private static Conexao conexao = new Conexao();
	public ClienteDAO() {
		
	}
	public static boolean cadastrarCliente(Cliente cliente) {
            try{
                conexao.openDB();
                PreparedStatement pstmt = conexao.con.prepareStatement("insert into cliente (dataNascimento, id_usuario) values (?,?)");
                String dataFormatada = cliente.getDataNascimento().toString();
                pstmt.setString(1, dataFormatada);
                pstmt.setInt(2, cliente.getId());
                pstmt.execute();
                conexao.closeDB();
                return true;
            }catch(SQLException e){
                System.out.println("Erro ao cadastrar: \n"+e);
                return false;
            }
	}
	public static boolean alterarCliente(Cliente cliente) {
		return true;
	}
	public static Cliente removerCliente(Cliente cliente) {
		return null;
	}
	public static ArrayList<Cliente> buscarClientes() {
		return null;
	}
}
