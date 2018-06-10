package model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

public class Zeitkarte extends Ticket {


	private Date gueltigAb;

	@Enumerated(EnumType.ORDINAL)
	private ZeitkartenTyp typ;

	public Zeitkarte(){

	}
	public Zeitkarte(Strecke strecke, Zahlung zahlung, Date gueltigAb, ZeitkartenTyp typ) {
		super(strecke, zahlung);
		this.setGueltigAb(gueltigAb);
		this.setTyp(typ);
	}

	public Date getGueltigAb() {
		return gueltigAb;
	}

	public void setGueltigAb(Date gueltigAb) {
		this.gueltigAb = gueltigAb;
	}

	public ZeitkartenTyp getTyp() {
		return typ;
	}

	public void setTyp(ZeitkartenTyp typ) {
		this.typ = typ;
	}
}
