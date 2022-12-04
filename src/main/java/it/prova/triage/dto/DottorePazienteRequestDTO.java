package it.prova.triage.dto;

public class DottorePazienteRequestDTO {
	private String codiceDottore;
	private String codFiscalePazienteAttualmenteInVisita;

	public DottorePazienteRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public DottorePazienteRequestDTO(String codiceDottore, String codFiscalePazienteAttualmenteInVisita) {
		super();
		this.codiceDottore = codiceDottore;
		this.codFiscalePazienteAttualmenteInVisita = codFiscalePazienteAttualmenteInVisita;
	}

	public String getCodiceDottore() {
		return codiceDottore;
	}

	public void setCodiceDottore(String codiceDottore) {
		this.codiceDottore = codiceDottore;
	}

	public String getCodFiscalePazienteAttualmenteInVisita() {
		return codFiscalePazienteAttualmenteInVisita;
	}

	public void setCodFiscalePazienteAttualmenteInVisita(String codFiscalePazienteAttualmenteInVisita) {
		this.codFiscalePazienteAttualmenteInVisita = codFiscalePazienteAttualmenteInVisita;
	}

}