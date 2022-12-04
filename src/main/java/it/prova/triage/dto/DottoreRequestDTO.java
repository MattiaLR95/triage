package it.prova.triage.dto;

public class DottoreRequestDTO {
	private String nome;
	private String cognome;
	private String codiceFiscale;

	public DottoreRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public DottoreRequestDTO(String nome, String cognome, String codiceFiscale) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodFiscalePazienteAttualmenteInVisita() {
		return codiceFiscale;
	}

	public void setCodFiscalePazienteAttualmenteInVisita(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

}