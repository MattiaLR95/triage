package it.prova.triage.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.triage.model.Paziente;
import it.prova.triage.model.StatoPaziente;
import it.prova.triage.repository.paziente.PazienteRepository;
import it.prova.triage.web.api.exception.PazienteNotFoundException;
import it.prova.triage.web.api.exception.PazienteNotInVisitaException;

@Service
@Transactional
public class PazienteServiceImpl implements PazienteService {

	@Autowired
	private PazienteRepository repository;

	@Override
	public List<Paziente> listAllElements() {
		return (List<Paziente>) repository.findAll();
	}

	@Override
	public Paziente caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Paziente aggiorna(Paziente pazienteInstance) {
		return repository.save(pazienteInstance);
	}

	@Override
	public Paziente inserisciNuovo(Paziente pazienteInstance) {
		pazienteInstance.setDataRegistrazione(LocalDateTime.now());
		pazienteInstance.setStatoPaziente(StatoPaziente.IN_ATTESA_VISITA);
		return repository.save(pazienteInstance);
	}


	@Override
	public void rimuovi(Long idToRemove) {
		repository.deleteById(idToRemove);
	}

	@Override
	public Paziente findByCodiceFiscale(String codiceFiscaleInstance) {
		return repository.findByCodiceFiscale(codiceFiscaleInstance).orElse(null);
	}

	@Override
	public Paziente findByCodiceDottore(String codiceDottoreInstance) {
		return repository.findPazienteByCodiceDottore(codiceDottoreInstance);

	}
	
	@Override
	public void ricovera(Long id) {
		Paziente result = repository.findById(id).orElse(null);
		
		if(result == null)
			throw new PazienteNotFoundException("paziente non trovato");
		
		if(!result.getStatoPaziente().equals(StatoPaziente.IN_VISITA))
			throw new PazienteNotInVisitaException("Paziente non in visita");
		
		result.setStatoPaziente(StatoPaziente.RICOVERATO);
		result.setCodiceDottore(null);
		
		repository.save(result);
	}

	@Override
	public void impostaCodiceDottore(String cf, String cd) {
		Paziente result = repository.findByCodiceFiscale(cf).orElse(null);
		
		if(result == null)
			throw new PazienteNotFoundException("paziente non trovato");
		
		result.setCodiceDottore(cd);
		result.setStatoPaziente(StatoPaziente.IN_VISITA);
		repository.save(result);
	}

	@Override
	public void dimetti(Long id) {
		Paziente result = repository.findById(id).orElse(null);
		
		if(result == null)
			throw new PazienteNotFoundException("paziente non trovato");
		
		if(!result.getStatoPaziente().equals(StatoPaziente.IN_VISITA))
			throw new PazienteNotInVisitaException("Paziente non in visita");
		
		result.setStatoPaziente(StatoPaziente.DIMESSO);
		result.setCodiceDottore(null);
		
		repository.save(result);
	}


}