package it.prova.triage;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.triage.model.Paziente;
import it.prova.triage.model.Ruolo;
import it.prova.triage.model.Utente;
import it.prova.triage.service.PazienteService;
import it.prova.triage.service.RuoloService;
import it.prova.triage.service.UtenteService;

@SpringBootApplication
public class TriageApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;
	@Autowired
	private PazienteService pazienteServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(TriageApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// RUOLI
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", Ruolo.ROLE_ADMIN));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Sub Operator", Ruolo.ROLE_SUB_OPERATOR) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Sub Operator", Ruolo.ROLE_SUB_OPERATOR));
		}

		// DATE
		LocalDateTime oraInizio = LocalDateTime.now();
		LocalDateTime oraFine = LocalDateTime.of(2025, Month.JUNE, 29, 19, 30, 40);

		// UTENTI
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", new Date());
			admin.setEmail("a.admin@prova.it");
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN));
			utenteServiceInstance.inserisciNuovo(admin);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}

		if (utenteServiceInstance.findByUsername("user") == null) {
			Utente classicUser = new Utente("user", "user", "Antonio", "Verdi", new Date());
			classicUser.setEmail("u.user@prova.it");
			classicUser.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Sub Operator", Ruolo.ROLE_SUB_OPERATOR));
			utenteServiceInstance.inserisciNuovo(classicUser);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(classicUser.getId());

		}

		pazienteServiceInstance.inserisciNuovo(new Paziente("giorgio", "bianchi", "giorgioBianchi"));

		pazienteServiceInstance.inserisciNuovo(new Paziente("fabio", "bello", "fabioBello"));

	}

}