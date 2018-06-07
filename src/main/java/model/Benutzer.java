package model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Benutzer {
	public Benutzer(){

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	private String vorName;

	private String nachName;

	@Column(unique = true)
	@Email(message = "E-Mail Adresse muss korrekt sein")
	private String eMail;

	private String passwort;

	@Column(unique = true)
	private String smsNummer;

	private Long verbuchtePraemienMeilen;

	@OneToOne(cascade = CascadeType.ALL)
	private Ticket tickets;


	private List<Reservierung> reservierungen;

	public Benutzer(String vorName, String nachName, String eMail, String passwort, String smsNummer, Long verbuchtePraemienMeilen, Ticket tickets, List<Reservierung> reservierungen) {
		super();
		this.vorName = vorName;
		this.nachName = nachName;
		this.eMail = eMail;
		this.passwort = passwort;
		this.smsNummer = smsNummer;
		this.verbuchtePraemienMeilen = verbuchtePraemienMeilen;
		this.tickets = tickets;
		this.reservierungen = reservierungen;
	}

}
