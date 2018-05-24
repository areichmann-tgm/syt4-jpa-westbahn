package main.main;

import javax.persistence.Column;
import javax.persistence.Id;

public class Einzelticket extends Ticket {

	@Id
	@Column
	private TicketOption ticketOption;

}
