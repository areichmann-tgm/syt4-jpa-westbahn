package main.main;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Entity
public class Zug {

	@Id
	@Column
	private long id;

	@Column
	private Date startZeit;

	@Column
	private int sitzPlaetze = 500;

	@Column
	private int fahrradStellplaetze = 50;

	@Column
	private int rollStuhlPlaetze = 10;

	@NotNull
	@ManyToOne
	@JoinColumn(name="start")
	private Bahnhof start;

	@NotNull
	@ManyToOne
	@JoinColumn(name="ende")
	private Bahnhof ende;

}
