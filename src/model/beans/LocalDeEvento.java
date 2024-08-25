package model.beans;

import java.util.ArrayList;

public class LocalDeEvento {

    
	private int id;
	private String nome, logradouro, CEP, numero;
	private int capacidade;
	private int colunas, fileiras;
	private ArrayList<Assento> assentos;
	public LocalDeEvento() {
		
	}
	public LocalDeEvento(String nome, String logradouro, String CEP, String numero, int capacidade, int fileiras, int colunas) {
		this.nome = nome;
		this.logradouro = logradouro;
		this.CEP = CEP;
		this.numero = numero;
		this.capacidade = capacidade;
		this.assentos = new ArrayList<>();
		this.fileiras = fileiras;
		this.colunas = colunas;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String CEP) {
		this.CEP = CEP;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	public boolean hasAssentos() {
		return assentos.size() > 0;
	}
	public ArrayList<Assento> getAssentos() {
		return this.assentos;
	}
	public void setAssentos(ArrayList<Assento> assentos) {
		this.assentos = assentos;
	}
	public int getColunas() {
		return colunas;
	}
	public void setColunas(int colunas) {
		this.colunas = colunas;
	}
	public int getFileiras() {
		return fileiras;
	}
	public void setFileiras(int fileiras) {
		this.fileiras = fileiras;
	}
@Override
    public int hashCode() {
        int hash = 5;
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
        final LocalDeEvento other = (LocalDeEvento) obj;
        return this.id == other.id;
    }
}
