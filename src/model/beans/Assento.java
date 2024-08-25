package model.beans;


public class Assento {

    
	private int id;
	private LocalDeEvento localDeEvento;
	private int coluna, fileira;
	private int numero;
	private boolean especial;
	public Assento() {
		
	}
	public Assento(LocalDeEvento local, int numero, boolean especial, int fileira, int coluna) {
		super();
		this.localDeEvento = local;
		this.numero = numero;
		this.especial = especial;
		this.fileira = fileira;
		this.coluna = coluna;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDeEvento getLocalDeEvento() {
		return localDeEvento;
	}
	public void setLocalDeEvento(LocalDeEvento localDeEvento) {
		this.localDeEvento = localDeEvento;
	}
	public int getColuna() {
		return coluna;
	}
	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	public int getFileira() {
		return fileira;
	}
	public void setFileira(int fileira) {
		this.fileira = fileira;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public boolean isEspecial() {
		return especial;
	}
	public void setEspecial(boolean especial) {
		this.especial = especial;
	}
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
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
        final Assento other = (Assento) obj;
        return this.id == other.id;
    }
}
