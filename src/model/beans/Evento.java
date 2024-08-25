package model.beans;

import java.util.ArrayList;

public class Evento extends LocalDeEvento {

    private int eid;
    private String duracao, dataInicio, dataFim, horaInicio, horaFim, nomeEvento;
    private float valorIngresso;
    private int idadeMin;
    private ArrayList<Ingresso> ingressos;
    

    public Evento(String nomeEvento, String duracao, String dataInicio, String dataFim, String horaInicio, String horaFim,
            float valorIngresso, int idadeMin) {
        this.duracao = duracao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.nomeEvento = nomeEvento;
        this.valorIngresso = valorIngresso;
        this.idadeMin = idadeMin;
        ingressos = new ArrayList<>();
    }

    private void gerarIngressos() {
    	//Refatorar depois
        ingressos.add(new Ingresso());
    }


    public Evento() {

    }

    public int getEId() {
        return eid;
    }

    public void setEId(int id) {
        this.eid = id;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public float getValorIngresso() {
        return valorIngresso;
    }

    public void setValorIngresso(float valorIngresso) {
        this.valorIngresso = valorIngresso;
    }


    public int getIdadeMin() {
        return idadeMin;
    }

    public void setIdadeMin(int idadeMin) {
        this.idadeMin = idadeMin;
    }

    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(ArrayList<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

    @Override
	public String toString() {
		return "Evento [id=" + eid + ", duracao=" + duracao + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim
				+ ", horaInicio=" + horaInicio + ", horaFim=" + horaFim + ", nomeEvento=" + nomeEvento
				+ ", valorIngresso=" + valorIngresso + ", idadeMin=" + idadeMin + "]";
	}
@Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.eid;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evento other = (Evento) obj;
        return this.eid == other.eid;
    }
}
