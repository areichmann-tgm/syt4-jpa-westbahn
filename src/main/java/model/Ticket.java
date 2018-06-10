package model;

import javax.persistence.*;

@Entity
public abstract class Ticket {

	public Ticket(){

	}

	@Id
	@GeneratedValue
	protected Long ID;

	@ManyToOne
	protected Strecke strecke;

	@Transient
	protected Zahlung zahlung;

	public Ticket(Strecke strecke, Zahlung zahlung){
		this.strecke = strecke;
		this.zahlung = zahlung;
	}
}
