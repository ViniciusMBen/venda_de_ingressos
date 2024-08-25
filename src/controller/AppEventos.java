package controller;

import view.*;
import javax.swing.*;

import model.beans.Evento;
import model.beans.LocalDeEvento;

public class AppEventos {

    public static void main(String[] args) {
        abreInicio();
    }

    public static JFrame abreLogin(JFrame janelaAtual, String tipoUsuario) {
        janelaAtual.setVisible(false);
        return new FrmLogin(tipoUsuario);
    }

    public static JFrame abreCadastro(JFrame janelaAtual, String tipoUsuario) {
        janelaAtual.setVisible(false);
        return new FrmCadastro(tipoUsuario);
    }

    public static JFrame abreInicio(JFrame janelaAtual) {
        janelaAtual.setVisible(false);
        return new FrmInicio();
    }

    public static JFrame abreInicio() {
        return new FrmInicio();
    }

    public static JFrame abreEventos(JFrame janelaAtual) {
        janelaAtual.setVisible(false);
        return new FrmListagemEventos();
    }

    public static JFrame abreEvento(Evento ev) {
        return new FrmEvento(ev);
    }

    public static JFrame abreEventosO(JFrame janelaAtual) {
        janelaAtual.setVisible(false);
        return new FrmListagemEventosO();
    }

    public static JFrame abreLocais(JFrame janelaAtual) {
        janelaAtual.setVisible(false);
        return new FrmListagemLocaisO();
    }

    public static JFrame abreCadEvento(JFrame janelaAtual) {
        janelaAtual.setVisible(false);
        return new FrmCadastroEvento();
    }

    public static JFrame abreCadLocal(JFrame janelaAtual) {
        janelaAtual.setVisible(false);
        return new FrmCadastroLocalDeEvento();
    }

    public static JFrame abreEditEvento(Evento ev) {
        return new FrmEditarEvento(ev);
    }

    public static JFrame abreEditLocal(LocalDeEvento loc) {
        return new FrmEditarLocalDeEvento(loc);
    }


    public static JFrame abreCompra(JFrame janelaAtual, Evento e) {
        janelaAtual.setVisible(false);
        return new FrmCompra(e);
    }

}
