package model.dao;

import java.util.ArrayList;

import model.beans.Organizador;
import java.sql.*;

public class OrganizadorDAO {

    private static Conexao conexao = new Conexao();

    public OrganizadorDAO() {
        // TODO Auto-generated constructor stub
    }

    public static boolean cadastrarOrganizador(Organizador organizador) {
        try {
            conexao.openDB();
            PreparedStatement pstmt = conexao.con.prepareStatement("insert into organizador (id_usuario) values (?)");
            pstmt.setInt(1, organizador.getId());
            pstmt.execute();
            conexao.closeDB();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar: \n" + e);
            return false;
        }
    }

    public static boolean alterarOrganizador(Organizador organizador) {
        return true;
    }

    public static Organizador removerOrganizador(Organizador organizador) {
        return null;
    }

    public static ArrayList<Organizador> buscarOrganizadores() {
        return null;
    }
}
