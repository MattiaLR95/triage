package it.prova.triage.web.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import it.prova.triage.dto.DottorePazienteRequestDTO;
import it.prova.triage.dto.DottorePazienteResponseDTO;
import it.prova.triage.dto.PazienteDTO;
import it.prova.triage.model.Paziente;
import it.prova.triage.model.StatoPaziente;
import it.prova.triage.service.PazienteService;
import it.prova.triage.web.api.exception.IdNotNullForInsertException;
import it.prova.triage.web.api.exception.IdNullUpdateException;
import it.prova.triage.web.api.exception.PazienteNonDimessoException;
import it.prova.triage.web.api.exception.PazienteNotFoundException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/paziente")
public class PazienteController {
	private static final Logger LOGGER = LogManager.getLogger(AssegnaPazienteDottoreController.class);

	@Autowired
	private PazienteService pazienteService;

	@Autowired
	private WebClient webClient;

	@GetMapping
	public List<PazienteDTO> listAlList() {
		return PazienteDTO.createPazienteDTOListFromModelList(pazienteService.listAllElements());
	}

	@GetMapping("/{id}")
	public PazienteDTO findById(@PathVariable(required = true) Long id) {
		Paziente pazienteDaCaricare = pazienteService.caricaSingoloElemento(id);

		if (pazienteDaCaricare == null)
			throw new PazienteNotFoundException("paziente non trovato");

		return PazienteDTO.buildPazienteDTOFromModel(pazienteDaCaricare);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PazienteDTO inserisci(@RequestBody PazienteDTO paziente) {
		if (paziente.getId() != null)
			throw new IdNotNullForInsertException("impossibile aggiornare un record se viene inserito anche l'id");

		return PazienteDTO.buildPazienteDTOFromModel(pazienteService.inserisciNuovo(paziente.buildPazienteModel()));
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody PazienteDTO paziente) {
		if (paziente.getId() == null)
			throw new IdNullUpdateException("impossibile aggiornare un campo se non si inserisce l'id");

		pazienteService.aggiorna(paziente.buildPazienteModel());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		Paziente pazienteDaEliminare = pazienteService.caricaSingoloElemento(id);

		if (pazienteDaEliminare == null)
			throw new PazienteNotFoundException("paziente non trovato");
		if (!pazienteDaEliminare.getStatoPaziente().equals(StatoPaziente.DIMESSO))
			throw new PazienteNonDimessoException("impossibile eliminare un paziente non dimesso");

		pazienteService.rimuovi(id);
	}

	@PostMapping("/ricovera/{id}")
	public DottorePazienteResponseDTO ricoveraPaziente(@PathVariable(required = true) Long id,
			@RequestBody DottorePazienteRequestDTO dottore) {
		LOGGER.info(".........invocazione servizio esterno............");

		pazienteService.ricovera(id);

		ResponseEntity<DottorePazienteResponseDTO> response = webClient.post().uri("/ricovera")
				.body(Mono.just(new DottorePazienteRequestDTO(dottore.getCodiceDottore(),
						dottore.getCodFiscalePazienteAttualmenteInVisita())), DottorePazienteRequestDTO.class)
				.retrieve().toEntity(DottorePazienteResponseDTO.class).block();

		LOGGER.info(".........invocazione servizio esterno completata............");

		return new DottorePazienteResponseDTO(response.getBody().getCodiceDottore(),
				response.getBody().getCodFiscalePazienteAttualmenteInVisita());
	}

	@PostMapping("/dimetti/{id}")
	public DottorePazienteResponseDTO dimettiPaziente(@PathVariable(required = true) Long id,
			@RequestBody DottorePazienteRequestDTO dottore) {
		LOGGER.info(".........invocazione servizio esterno............");

		pazienteService.dimetti(id);

		ResponseEntity<DottorePazienteResponseDTO> response = webClient.post().uri("/ricovera")
				.body(Mono.just(new DottorePazienteRequestDTO(dottore.getCodiceDottore(),
						dottore.getCodFiscalePazienteAttualmenteInVisita())), DottorePazienteRequestDTO.class)
				.retrieve().toEntity(DottorePazienteResponseDTO.class).block();

		LOGGER.info(".........invocazione servizio esterno completata............");

		return new DottorePazienteResponseDTO(response.getBody().getCodiceDottore(),
				response.getBody().getCodFiscalePazienteAttualmenteInVisita());
	}

}