package model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Sonderangebot {

	@Id
	private Long ID;

	private int kontingent = 999;

	private Date startZeit;

	private int dauer = 12;

	private float preisNachlass = 0.5f;

	private Ticket tickets;

}
