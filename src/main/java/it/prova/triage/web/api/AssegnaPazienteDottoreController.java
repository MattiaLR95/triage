package it.prova.triage.web.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import it.prova.triage.dto.DottorePazienteRequestDTO;
import it.prova.triage.dto.DottorePazienteResponseDTO;
import it.prova.triage.dto.DottoreResponseDTO;
import it.prova.triage.service.PazienteService;
import it.prova.triage.web.api.exception.DottoreNonDisponibileException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/assegnapazienteDottore")
public class AssegnaPazienteDottoreController {
	private static final Logger LOGGER = LogManager.getLogger(AssegnaPazienteDottoreController.class);

	@Autowired
	private WebClient webClient;
	@Autowired
	private PazienteService pazienteService;

	@GetMapping("/{cd}")
	public DottoreResponseDTO verificaDisponibilitaDottore(@PathVariable(required = true) String cd) {

		LOGGER.info(".........invocazione servizio esterno............");

		DottoreResponseDTO dottoreResponseDTO = webClient.get().uri("/verificaDisponibilitaDottore/" + cd).retrieve()
				.onStatus(HttpStatus::is4xxClientError, response -> {
					throw new DottoreNonDisponibileException("dottore non disponibile");
				}).bodyToMono(DottoreResponseDTO.class).block();

		LOGGER.info(".........invocazione servizio esterno completata............");

		return dottoreResponseDTO;
	}

	@PostMapping("/impostaVisita")
	public DottorePazienteResponseDTO impostaVisita(@RequestBody DottorePazienteRequestDTO dottore) {
		pazienteService.impostaCodiceDottore(dottore.getCodFiscalePazienteAttualmenteInVisita(),
				dottore.getCodiceDottore());
		LOGGER.info(".........invocazione servizio esterno............");

		ResponseEntity<DottorePazienteResponseDTO> response = webClient.post().uri("/impostaVisita")
				.body(Mono.just(new DottorePazienteRequestDTO(dottore.getCodiceDottore(),
						dottore.getCodFiscalePazienteAttualmenteInVisita())), DottorePazienteRequestDTO.class)
				.retrieve().toEntity(DottorePazienteResponseDTO.class).block();

		LOGGER.info(".........invocazione servizio esterno completata............");

		return new DottorePazienteResponseDTO(response.getBody().getCodiceDottore(),
				response.getBody().getCodFiscalePazienteAttualmenteInVisita());
	}

}