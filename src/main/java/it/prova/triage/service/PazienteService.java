package it.prova.triage.service;

import java.util.List;

import it.prova.triage.model.Paziente;

public interface PazienteService {
	List<Paziente> listAllElements();

	Paziente caricaSingoloElemento(Long id);

	Paziente aggiorna(Paziente pazienteInstance);

	Paziente inserisciNuovo(Paziente pazienteInstance);

	void rimuovi(Long idToRemove);

	Paziente findByCodiceFiscale(String codiceFiscaleInstance);

	Paziente findByCodiceDottore(String codiceDottoreInstance);

	public void ricovera(Long id);

	public void impostaCodiceDottore(String codiceFiscaleInstance, String codiceDottoreInstance);

	public void dimetti(Long id);

}