package model.dao;

import java.util.ArrayList;
import java.sql.*;
import model.beans.LocalDeEvento;

public class LocalDeEventoDAO {

    private static Conexao conexao = new Conexao();

    public LocalDeEventoDAO() {
        // TODO Auto-generated constructor stub
    }

    public static boolean cadastrarLocal(LocalDeEvento local) {
        try {
            conexao.openDB();
            PreparedStatement pstmt = conexao.con.prepareStatement("insert into localdeevento (nome, logradouro, cep,"
                    + " numero, capacidade, fileiras, colunas) values"
                    + "(?,?,?,?,?,?,?)");
            pstmt.setString(1, local.getNome());
            pstmt.setString(2, local.getLogradouro());
            pstmt.setString(3, local.getCEP());
            pstmt.setString(4, local.getNumero());
            pstmt.setInt(5, local.getCapacidade());
            pstmt.setInt(6, local.getFileiras());
            pstmt.setInt(7, local.getColunas());

            pstmt.executeUpdate();
            conexao.closeDB();
        } catch (SQLException e) {
            System.out.println("Falha ao inserir local de evento\n" + e);
            return false;
        }
        return true;
    }

    public static boolean alterarLocal(LocalDeEvento local) {
        try {
            conexao.openDB();
            PreparedStatement pstmt = conexao.con.prepareStatement(
                    "UPDATE localdeevento SET nome = ?, logradouro = ?, cep = ?, "
                    + "numero = ?, capacidade = ?, fileiras = ?, colunas = ? WHERE id = ?");

            pstmt.setString(1, local.getNome());
            pstmt.setString(2, local.getLogradouro());
            pstmt.setString(3, local.getCEP());
            pstmt.setString(4, local.getNumero());
            pstmt.setInt(5, local.getCapacidade());
            pstmt.setInt(6, local.getFileiras());
            pstmt.setInt(7, local.getColunas());
            pstmt.setInt(8, local.getId());

            pstmt.executeUpdate();
            conexao.closeDB();

        } catch (SQLException e) {
            System.out.println("Falha ao editar local\n" + e);
            return false;
        }
        return true;
    }

    public static LocalDeEvento removerLocal(LocalDeEvento l) {
        try {
            conexao.openDB();
            PreparedStatement pstmt = conexao.con.prepareStatement("DELETE FROM localdeevento WHERE id = ?");
            pstmt.setInt(1, l.getId());
            pstmt.executeUpdate();
            conexao.closeDB();
            return l;
        } catch (SQLException ex) {
            System.out.println("Falha ao deletar local de evento\n" + ex);
            return null;
        }

    }


    public static ArrayList<LocalDeEvento> buscarLocais() {
        ArrayList<LocalDeEvento> locais = new ArrayList<>();
        LocalDeEvento local;
        try {
            conexao.openDB();
            PreparedStatement pstmt = conexao.con.prepareStatement("select * from localdeevento");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                local = new LocalDeEvento();
                local.setNome(rs.getString("nome"));
                local.setLogradouro(rs.getString("logradouro"));
                local.setCEP(rs.getString("CEP"));
                local.setNumero(rs.getString("numero"));
                local.setCapacidade(rs.getInt("capacidade"));
                local.setFileiras(rs.getInt("fileiras"));
                local.setColunas(rs.getInt("colunas"));
                local.setId(rs.getInt("id"));
                locais.add(local);
            }
            conexao.closeDB();
        } catch (SQLException ex) {
            System.out.println("Falha ao buscar locais de eventos\n" + ex);
        }
        return locais;
    }

    public static LocalDeEvento buscaLocal(int id) {
        try {
            conexao.openDB();
            PreparedStatement pstmt = conexao.con.prepareStatement("select * from localdeevento where id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            LocalDeEvento local;
            rs.next();
            local = new LocalDeEvento();
            local.setNome(rs.getString("nome"));
            local.setLogradouro(rs.getString("logradouro"));
            local.setCEP(rs.getString("CEP"));
            local.setNumero(rs.getString("numero"));
            local.setCapacidade(rs.getInt("capacidade"));
            local.setFileiras(rs.getInt("fileiras"));
            local.setColunas(rs.getInt("colunas"));
            local.setId(rs.getInt("id"));
            conexao.closeDB();
            return local;
        } catch (SQLException ex) {
            System.out.println("Falha ao buscar o local de eventos com id: " + id + "\n" + ex);
            return null;
        }
    }

    public static LocalDeEvento getLocalDeEvento(int id) {
        LocalDeEvento local = new LocalDeEvento();
        try {
            conexao.openDB();
            PreparedStatement pstmt = conexao.con.prepareStatement("select * from localdeevento");
            ResultSet rs = pstmt.executeQuery();
            local = new LocalDeEvento();
            local.setId(rs.getInt("id_localdeevento"));
            local.setNome(rs.getString("nome"));
            local.setLogradouro(rs.getString("logradouro"));
            local.setCEP(rs.getString("CEP"));
            local.setNumero(rs.getString("numero"));
            local.setCapacidade(rs.getInt("capacidade"));
            local.setFileiras(rs.getInt("fileiras"));
            local.setColunas(rs.getInt("colunas"));
            conexao.closeDB();

        } catch (SQLException ex) {
            System.out.println("Falha ao buscar locais de eventos\n" + ex);
        }
        return local;
    }
}
