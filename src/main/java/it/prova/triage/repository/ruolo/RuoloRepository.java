package it.prova.triage.repository.ruolo;

import org.springframework.data.repository.CrudRepository;

import it.prova.triage.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long> {
	Ruolo findByDescrizioneAndCodice(String descrizione, String codice);
}
