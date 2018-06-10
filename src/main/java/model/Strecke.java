package model;

import javax.persistence.*;

@Entity
public class Strecke {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@ManyToOne(cascade = CascadeType.ALL)
	private Bahnhof start;

	@ManyToOne(cascade = CascadeType.ALL)
	private Bahnhof bahnhof;

	@ManyToOne(cascade = CascadeType.ALL)
	private Bahnhof ende;

}
