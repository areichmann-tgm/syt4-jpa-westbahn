package main;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.List;

public class Benutzer {

	private Long id;

	private String vorName;

	private String nachName;

	private String eMail;

	private String passwort;

	private String smsNummer;

	private Long verbuchtePraemienMeilen;

	@OneToMany
	@JoinTable(
			name = "benutzer_tickets",
			joinColumns = {@JoinColumn(referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")}
	)
	private List<Ticket> tickets;

	private Reservierung[] reservierungen;

}
