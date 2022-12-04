package it.prova.triage.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.triage.model.Paziente;
import it.prova.triage.model.StatoPaziente;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PazienteDTO {

	private Long id;

	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;

	@NotBlank(message = "{codiceFiscale.notblank}")
	private String codiceFiscale;
	@NotNull(message = "{dataRegistrazione.notnull}")
	private LocalDateTime dataRegistrazione;
	private StatoPaziente statoPaziente;
	private String codiceDottore;

	public PazienteDTO() {
		// TODO Auto-generated constructor stub
	}

	public PazienteDTO(String codiceFiscale) {
		super();
		this.codiceFiscale = codiceFiscale;
	}

	public PazienteDTO(Long id) {
		super();
		this.id = id;
	}

	public PazienteDTO(Long id, String nome, String cognome, String codiceFiscale, LocalDateTime dataRegistrazione,
			StatoPaziente statoPaziente, String codiceDottore) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataRegistrazione = dataRegistrazione;
		this.statoPaziente = statoPaziente;
		this.codiceDottore = codiceDottore;
	}

	public PazienteDTO(Long id, String nome, String cognome, String codiceFiscale, LocalDateTime dataRegistrazione,
			StatoPaziente statoPaziente) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataRegistrazione = dataRegistrazione;
		this.statoPaziente = statoPaziente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public LocalDateTime getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(LocalDateTime dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public StatoPaziente getStatoPaziente() {
		return statoPaziente;
	}

	public void setStatoPaziente(StatoPaziente statoPaziente) {
		this.statoPaziente = statoPaziente;
	}

	public String getCodiceDottore() {
		return codiceDottore;
	}

	public void setCodiceDottore(String codiceDottore) {
		this.codiceDottore = codiceDottore;
	}

//Aggiungere boolean tavolo presente
	public Paziente buildPazienteModel() {
		Paziente result = new Paziente(this.id, this.nome, this.cognome, this.codiceFiscale, this.dataRegistrazione,
				this.statoPaziente, this.codiceDottore);
		return result;
	}

	public static PazienteDTO buildPazienteDTOFromModel(Paziente pazienteModel) {
		PazienteDTO result = new PazienteDTO(pazienteModel.getId(), pazienteModel.getNome(), pazienteModel.getCognome(),
				pazienteModel.getCodiceFiscale(), pazienteModel.getDataRegistrazione(),
				pazienteModel.getStatoPaziente(), pazienteModel.getCodiceDottore());

		return result;
	}

	public static List<PazienteDTO> createPazienteDTOListFromModelList(List<Paziente> modelListInput) {
		return modelListInput.stream().map(pazienteEntity -> {
			return PazienteDTO.buildPazienteDTOFromModel(pazienteEntity);
		}).collect(Collectors.toList());
	}

	public static Set<PazienteDTO> createPazienteDTOSetFromModelSet(Set<Paziente> modelListInput) {
		return modelListInput.stream().map(pazienteEntity -> {
			return PazienteDTO.buildPazienteDTOFromModel(pazienteEntity);
		}).collect(Collectors.toSet());
	}

}