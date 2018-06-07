package model;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
public class Benutzer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	private String vorName;

	private String nachName;

	@Column(unique = true)
	@Email(message = "E-Mail Adresse muss korrekt sein")
	private String eMail;

	private String passwort;

	private String smsNummer;

	private Long verbuchtePraemienMeilen;

	private Ticket tickets;

	private Reservierung[] reservierungen;

	public Benutzer(String vorName, String nachName, String eMail, String passwort, String smsNummer, Long verbuchtePraemienMeilen, Ticket tickets) {
		super();
		this.vorName = vorName;
		this.nachName = nachName;
		this.eMail = eMail;
		this.passwort = passwort;
		this.smsNummer = smsNummer;
		this.verbuchtePraemienMeilen = verbuchtePraemienMeilen;
		this.tickets = tickets;
	}

}
