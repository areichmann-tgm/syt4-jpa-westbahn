package main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Preisstaffelung {

	@Id
	@Column
	private static long serialVersionUID;

	@NotNull
	@Column
	private float grossGepaeck = (1.02f);

	@NotNull
	@Column
	private float fahrrad = 1.05f;

	@NotNull
	@Column
	private int zeitkarteWoche = 8;

	@NotNull
	@Column
	private int zeitkarteMonat = 25;

	@NotNull
	@Column
	private int zeitkarteJahr = 250;

	@NotNull
	@Column
	private static Preisstaffelung instance;

	@NotNull
	@Column
	public static Preisstaffelung getInstance() {
		return null;
	}

	private Preisstaffelung() {

	}

}
