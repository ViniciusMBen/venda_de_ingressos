package model.beans;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Period;
public class Cliente extends Usuario {

	private int id;
	private LocalDate dataNascimento;
	private ArrayList<Ingresso> ingressosComprados;
	public Cliente() {
		super();
                setOrganizador(false);
	}
	public Cliente(String nome, String email, String senha, String dataNascimento) {
		super(nome, email, senha, false);
                this.setDataNascimento(dataNascimento);
	}
	public Cliente(String nome, String email, String senha) {
		super(nome, email, senha, false);
		ingressosComprados = new ArrayList<Ingresso>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
            String[] data;
            int dia;
            int mes;
            int ano;
            if(dataNascimento.contains("-")){
                data = dataNascimento.split("-");
                dia = Integer.parseInt(data[2]);
                mes = Integer.parseInt(data[1]);
                ano = Integer.parseInt(data[0]);
            }else{
                data = dataNascimento.split("/");
                dia = Integer.parseInt(data[0]);
                mes = Integer.parseInt(data[1]);
                ano = Integer.parseInt(data[2]);
            }
            this.dataNascimento = LocalDate.of(ano, mes, dia);
	}
	public int getIdade() {
		int idade = -1;
		if(this.dataNascimento != null) {
                    idade = Period.between(dataNascimento, LocalDate.now()).getYears();
		}
		return idade;
	}
	public void addIngresso(Ingresso ing) {
		ingressosComprados.add(ing);
	}
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
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
        final Cliente other = (Cliente) obj;
        return this.id == other.id;
    }
}
