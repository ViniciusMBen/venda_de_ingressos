package controller;

import model.beans.*;
import model.dao.*;

public class AssentoController {

    static Assento getAssento(int idLoc, int col, int fil) {
        
         return AssentoDAO.buscarAssento(idLoc, col, fil);
    }

    public AssentoController() {
        // TODO Auto-generated constructor stub
    }
    public static boolean inserirAssento(int fileira, int coluna, int idLocal){
        LocalDeEvento lc = new LocalDeEvento();
        lc.setId(idLocal);
        int numero = Integer.parseInt(Integer.toString(fileira)+Integer.toString(coluna));
        Assento assento = new Assento(lc,numero, false,fileira, coluna);
        return AssentoDAO.cadastrarAssento(assento);
    }

}
