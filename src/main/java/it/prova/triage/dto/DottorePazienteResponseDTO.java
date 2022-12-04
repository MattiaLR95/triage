package it.prova.triage.dto;

public class DottorePazienteResponseDTO {
	private String codiceDottore;
	private String codFiscalePazienteAttualmenteInVisita;

	public DottorePazienteResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public DottorePazienteResponseDTO(String codiceDottore, String codFiscalePazienteAttualmenteInVisita) {
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