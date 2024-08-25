package model.dao;

import controller.LocalDeEventoController;
import java.util.ArrayList;

import model.beans.Assento;
import java.sql.*;

public class AssentoDAO {

    private static Conexao conexao = new Conexao();

    public AssentoDAO() {
        // TODO Auto-generated constructor stub
    }

    public static boolean cadastrarAssento(Assento assento) {
        try {
            conexao.openDB();
            PreparedStatement pstmt = conexao.con.prepareStatement("insert into assento ( numero, coluna, fileira, especial, id_localdeevento) values "
                    + "(?, ?, ?, 0, ?)");
            pstmt.setInt(1, assento.getNumero());
            pstmt.setInt(2, assento.getColuna());
            pstmt.setInt(3, assento.getFileira());
            pstmt.setInt(4, assento.getLocalDeEvento().getId());

            pstmt.executeUpdate();
            conexao.closeDB();
            return true;
        } catch (SQLException e) {
            System.out.println("Falha ao cadastrar assento\n" + e);
            return false;
        }
    }

    public static boolean alterarAssento(Assento assento) {
        return true;
    }

    public static Assento removerAssento(Assento assento) {
        return null;
    }

    public static ArrayList<Assento> buscarAssentos() {
        return null;
    }

    public static Assento buscarAssento(int idLocal, int col, int fil) {
        try {
            conexao.openDB();
            PreparedStatement pstmt = conexao.con.prepareStatement("select * from assento where "
                    + "id_localdeevento = ? and coluna = ? and fileira = ?");
            pstmt.setInt(1, idLocal);
            pstmt.setInt(2, col);
            pstmt.setInt(3, fil);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            Assento a = new Assento(null, rs.getInt("numero"), rs.getBoolean("especial"), rs.getInt("fileira"), rs.getInt("coluna"));
            a.setId(rs.getInt("id"));
            int idLocalDeEvento = rs.getInt("id_localdeevento");
            conexao.closeDB();
            a.setLocalDeEvento(LocalDeEventoController.getLocal(idLocalDeEvento));
            return a;
        } catch (SQLException e) {
            System.out.println("Falha ao buscar assento\n" + e);
            return null;
        }
    }
}
