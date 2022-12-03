package it.prova.triage.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paziente")
public class Paziente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "codiceFiscale")
	private String codiceFiscale;
	@Column(name = "dataRegistrazione")
	private LocalDateTime dataRegistrazione;

	@Enumerated(EnumType.STRING)
	private StatoPaziente statoPaziente;
	@Column(name = "codiceDottore")
	private String codiceDottore;

	public Paziente() {
		// TODO Auto-generated constructor stub
	}

	public Paziente(Long id, String nome, String cognome, String codiceFiscale, LocalDateTime dataRegistrazione,
			StatoPaziente statoPaziente) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataRegistrazione = dataRegistrazione;
		this.statoPaziente = statoPaziente;
	}

	public Paziente(Long id, String nome, String cognome, String codiceFiscale, LocalDateTime dataRegistrazione,
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

	public Paziente(String nome, String cognome, String codiceFiscale) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
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

	public boolean isInAttesa() {
		return this.statoPaziente != null && this.statoPaziente.equals(StatoPaziente.IN_ATTESA_VISITA);
	}

	public boolean isInVisita() {
		return this.statoPaziente != null && this.statoPaziente.equals(StatoPaziente.IN_VISITA);
	}

	public boolean isRicoverato() {
		return this.statoPaziente != null && this.statoPaziente.equals(StatoPaziente.RICOVERATO);
	}

	public boolean isDimesso() {
		return this.statoPaziente != null && this.statoPaziente.equals(StatoPaziente.DIMESSO);
	}
}