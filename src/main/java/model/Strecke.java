package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Strecke {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	private Bahnhof start;

	private Bahnhof bahnhof;

	private Bahnhof ende;

}
