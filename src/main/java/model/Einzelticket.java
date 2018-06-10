package model;

import javax.persistence.Entity;

@Entity
public class Einzelticket extends Ticket {


	public TicketOption getTicketOption() {
		return ticketOption;
	}

	public void setTicketOption(TicketOption ticketOption) {
		this.ticketOption = ticketOption;
	}

	private TicketOption ticketOption;

	public Einzelticket(){

	}

	public Einzelticket(Strecke strecke, Zahlung zahlung, TicketOption ticketOption){
		super(strecke,zahlung);
		this.ticketOption= ticketOption;
	}

}
