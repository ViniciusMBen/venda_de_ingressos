package controller;

import java.util.ArrayList;

import model.beans.*;
import model.dao.*;

public class EventoController {

    public EventoController() {
    }

    public static ArrayList<Evento> getListaEventos() {
        ArrayList<Evento> eventos = EventoDAO.buscarEventos();
        for(Evento e: eventos){ //Carrega informações do local do evento no objeto de evento
            LocalDeEvento lc = LocalDeEventoController.getLocal(e.getId());
            e.setNome(lc.getNome());
            e.setAssentos(lc.getAssentos());
            e.setFileiras(lc.getFileiras());
            e.setColunas(lc.getColunas());
            e.setNumero(lc.getNumero());
            e.setLogradouro(lc.getLogradouro());
            e.setCEP(lc.getCEP());
            e.setCapacidade(lc.getCapacidade());
        }
        return eventos;
    }

    public static Evento excluirEvento(Evento e) {
        return EventoDAO.removerEvento(e);
    }

    public static boolean addEvento(String nome, int idLocalEvento, String duracao, String dataInicio, String dataFim, String horaInicio, String horaFim, int IdadeMin, float preco) {

        Evento e = new Evento(nome, duracao, dataInicio, dataFim, horaInicio, horaFim, preco, IdadeMin);
        e.setId(idLocalEvento);
        return EventoDAO.cadastrarEvento(e);
    }

    public static boolean updEvento(int eId, String nome, int local, String duracao, String dataInicio, String dataFim, String horaInicio, String horaFim, int idadeMin, float preco) {
        Evento evento = new Evento(nome, duracao, dataInicio, dataFim, horaInicio, horaFim, preco, idadeMin);
        evento.setId(local);
        evento.setEId(eId);
        return EventoDAO.alterarEvento(evento);
    }
}
