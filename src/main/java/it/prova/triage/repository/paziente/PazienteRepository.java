package it.prova.triage.repository.paziente;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.triage.model.Paziente;

public interface PazienteRepository extends CrudRepository<Paziente, Long> {

	Optional<Paziente> findByCodiceFiscale(String codiceFiscaleInput);

	@Query("select p from Paziente p where p.codiceDottore=?1")
	Paziente findPazienteByCodiceDottore(String codiceDottoreInput);

}