package main.main;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Strecke {

	@Id
	@Column
	private long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name="start")
	private Bahnhof start;

	@NotNull
	@ManyToOne
	@JoinColumn(name="bahnhof")
	private Bahnhof bahnhof;

	@NotNull
	@ManyToOne
	@JoinColumn(name="ende")
	private Bahnhof ende;

}
