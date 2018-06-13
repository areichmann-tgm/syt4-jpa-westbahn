package model;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;

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

	@AssertFalse(message="Start und Ende dürfen nicht gleich sein!")
	private boolean endeIsStart;

	public Strecke(Bahnhof start, Bahnhof bahnhof, Bahnhof ende) {
		super();
		this.start = start;
		this.bahnhof = bahnhof;
		this.ende = ende;
		this.endeIsStart = this.ende.getName().equals(this.start.getName());
	}
	public Strecke(Bahnhof start, Bahnhof ende) {
		super();
		this.start = start;
		this.ende = ende;
	}
	public Strecke(){

	}
	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public Bahnhof getStart() {
		return start;
	}

	public void setStart(Bahnhof start) {
		this.start = start;
	}

	public Bahnhof getBahnhof() {
		return bahnhof;
	}

	public void setBahnhof(Bahnhof bahnhof) {
		this.bahnhof = bahnhof;
	}

	public Bahnhof getEnde() {
		return ende;
	}

	public void setEnde(Bahnhof ende) {
		this.ende = ende;
	}
}
