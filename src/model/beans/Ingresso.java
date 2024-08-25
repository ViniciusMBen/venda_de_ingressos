package model.beans;


public class Ingresso {
	private int id;
	private boolean comprado;
	private Evento evento;
	private Assento assento;
	
	public Ingresso() {
		
	}
	public Ingresso(boolean comprado, Evento evento) {

		this.comprado = comprado;
		this.evento = evento;
	}
	public Ingresso(boolean comprado, Evento evento, Assento assento) {

		this.comprado = comprado;
		this.evento = evento;
		this.assento = assento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isComprado() {
		return comprado;
	}

	public void setComprado(boolean comprado) {
		this.comprado = comprado;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public Assento getAssento() {
		return assento;
	}
	public void setAssento(Assento assento) {
		this.assento = assento;
	}
	
@Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id;
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
        final Ingresso other = (Ingresso) obj;
        return this.id == other.id;
    }
}
