package model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class Zug {

	@Id
	private Long ID;

	private Date startZeit;

	private int sitzPlaetze = 500;

	private int fahrradStellplaetze = 50;

	private int rollStuhlPlaetze = 10;

	private Bahnhof start;

	private Bahnhof ende;

}