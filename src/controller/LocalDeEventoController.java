package controller;

import java.util.ArrayList;

import model.beans.*;
import model.dao.*;

public class LocalDeEventoController {

    public LocalDeEventoController() {
        // TODO Auto-generated constructor stub
    }

    public static ArrayList<LocalDeEvento> getListaLocais() {
        return LocalDeEventoDAO.buscarLocais();
    }

    public static LocalDeEvento excluiLocal(LocalDeEvento lc) {
        return LocalDeEventoDAO.removerLocal(lc);
    }

    public static boolean addLocal(String nome, String logradouro, String cep, String numero, int capacidade, int fileiras, int colunas) {
        LocalDeEvento lc = new LocalDeEvento(nome, logradouro, cep, numero, capacidade, fileiras, colunas);

        return LocalDeEventoDAO.cadastrarLocal(lc);
    }

    public static LocalDeEvento getLocal(int id) {
        return LocalDeEventoDAO.buscaLocal(id);
    }

    public static boolean updLocal(int id, String nome, String logradouro, String cep, String num, int capacidade, int fil, int col) {
        LocalDeEvento local = new LocalDeEvento(nome, logradouro, cep, num, capacidade, fil, col);
        local.setId(id);
        return LocalDeEventoDAO.alterarLocal(local);
    }

}
