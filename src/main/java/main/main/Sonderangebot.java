package main.main;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Sonderangebot {

	@Id
	@Column
	private long id;

	@NotNull
	@Column
	private int kontingent = 999;

	@NotNull
	@Column
	private Date startZeit;

	@NotNull
	@Column
	private int dauer = 12;

	@NotNull
	@Column
	private float preisNachlass = 0.5f;


	@OneToMany
	@JoinTable(
			name = "sonderangebot_tickets",
			joinColumns = {@JoinColumn(referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")}
	)
	private List<Ticket> tickets;

}
