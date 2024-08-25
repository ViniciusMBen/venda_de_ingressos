package controller;

import javax.swing.JOptionPane;
import model.beans.*;
import model.dao.*;

public class UsuarioController {

    private static Usuario userLogado;

    public static Usuario fazerLogin(String email, String senha) {
        Usuario user = new Cliente();
        user.setEmail(email);
        user.setSenha(senha);
        userLogado = UsuarioDAO.login(user.getEmail(), user.getSenha());
        return userLogado;
    }

    public static boolean fazerCadastro(String nome, String email, String senha, boolean organizador, String dataNascimento) {
        Usuario u;
        if (organizador) {
            u = new Organizador(nome, email, senha);
            u = UsuarioDAO.cadastro(u);//Cadastra o usuário e retorna um usuário com seu id do banco de dados
            OrganizadorController.cadastrarOrganizador((Organizador) u);
        } else {
            u = new Cliente(nome, email, senha, dataNascimento);
            u = UsuarioDAO.cadastro(u); //Cadastra o usuário e retorna um usuário com seu id do banco de dados
            ClienteController.cadastrarCliente((Cliente) u);
        }
        return u != null;
    }

    public static Usuario getUserLogado() {
        return userLogado;
    }
}
