/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.sql.*;

public class Conexao {
    /**
     * Classe que abre e fecha a conexão com o banco de dados, define o driver jdbc e o url, usuário e senha do banco.
     */
    public Conexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Driver jdbc não encontrado\n" + e);
        }
    }

    private final static String url = "jdbc:mysql://localhost:3306/empresadeeventos";//URL do banco de dados
    private final static String username = "root"; // usuário do db
    private final static String password = ""; // senha do db

    public Connection con;

    public void openDB() {
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Não foi possível estabelecer conexão: \n" + e);
            System.exit(1);
        }
    }

    public void closeDB() {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("Falha ao fechar a conexão: \n" + e);
            System.exit(1);
        }
    }

}
