package main;
import javax.persistence.*;
import java.util.List;

public class Benutzer {

	@Id
	@Column
	private long id;

	@Column
	private String vorName;

	@Column
	private String nachName;

	@Column
	private String eMail;

	@Column
	private String passwort;

	@Column
	private String smsNummer;

	@Column
	private long verbuchtePraemienMeilen;

	@OneToMany
	@JoinTable(
			name = "benutzer_tickets",
			joinColumns = {@JoinColumn(referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")}
	)
	private List<Ticket> tickets;

	@OneToMany
	@JoinTable(
			name = "benutzer_reservierungen",
			joinColumns = {@JoinColumn(referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")}
	)
	private List<Reservierung> reservierungen;

}
