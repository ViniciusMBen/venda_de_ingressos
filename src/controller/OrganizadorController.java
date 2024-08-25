package controller;
import model.beans.*;
import model.dao.*;
public class OrganizadorController {
    public static boolean cadastrarOrganizador(Organizador o){
        return OrganizadorDAO.cadastrarOrganizador(o);
    }
}
