package controller;

import model.beans.*;
import model.dao.*;

public class ClienteController {

    public static boolean cadastrarCliente(Cliente c) {
        return ClienteDAO.cadastrarCliente(c);
    }
}
