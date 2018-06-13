package model;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name="listTicketsForRoute", query = "select t from Ticket t LEFT JOIN Reservierung r on r.strecke.ID = t.strecke.ID  NOT IN WHERE r.strecke.start = :start AND r.strecke.ende = :ende ")
})
public abstract class Ticket {

	public Ticket(){

	}

	@Id
	@GeneratedValue
	private Long ID;

	@ManyToOne
	private Strecke strecke;

	@Transient
	private Zahlung zahlung;

	public Ticket(Strecke strecke, Zahlung zahlung){
		this.setStrecke(strecke);
		this.setZahlung(zahlung);
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public Strecke getStrecke() {
		return strecke;
	}

	public void setStrecke(Strecke strecke) {
		this.strecke = strecke;
	}

	public Zahlung getZahlung() {
		return zahlung;
	}

	public void setZahlung(Zahlung zahlung) {
		this.zahlung = zahlung;
	}
}
