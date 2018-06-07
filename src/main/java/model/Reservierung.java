package model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Reservierung {

	@Id
	private Long ID;

	private Date datum;

	private int praemienMeilenBonus = 15;

	private int preis = 150;

	private StatusInfo status;

	private Zug zug;

	private Strecke strecke;

	private Benutzer benutzer;

	private Zahlung zahlung;

}
