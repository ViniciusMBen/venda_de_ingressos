package model.beans;


public class Organizador extends Usuario {

    
	private int id;
	public Organizador() {
		super();
                setOrganizador(true);
	}
	public Organizador(String nome, String email, String senha) {
		super(nome, email, senha, true);
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
@Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id;
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
        final Organizador other = (Organizador) obj;
        return this.id == other.id;
    }
}
