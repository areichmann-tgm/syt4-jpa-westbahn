package main.main;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Reservierung {

	@Id
	@Column
	private long id;


	@NotNull
	@Column
	private Date datum;

	@NotNull
	@Column
	private int praemienMeilenBonus = 15;

	@NotNull
	@Column
	private int preis = 150;

	@NotNull
	@Column
	private StatusInfo status;

	@NotNull
	@Column
	private Zug zug;

	@NotNull
	@Column
	private Strecke strecke;

	@NotNull
	@Column
	private Benutzer benutzer;

	@NotNull
	@Column
	private Zahlung zahlung;

}
