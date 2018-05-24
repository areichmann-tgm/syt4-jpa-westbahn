package main;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Strecke {

	@Id
	@Column
	private long id;

	@NotNull
	@Column
	private Bahnhof start;

	@NotNull
	@Column
	private Bahnhof bahnhof;

	@NotNull
	@Column
	private Bahnhof ende;

}
