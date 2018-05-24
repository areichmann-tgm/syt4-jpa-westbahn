package main;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Benutzer {

	@Id
	@Column
	private long id;

	@NotNull
	@Column
	private String vorName;

	@NotNull
	@Column
	private String nachName;

	@NotNull
	@Column
	private String eMail;

	@NotNull
	@Column
	private String passwort;

	@NotNull
	@Column
	private String smsNummer;

	@NotNull
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
