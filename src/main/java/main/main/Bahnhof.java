package main;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Entity;

@Entity
public class Bahnhof {
	
	@Id
	private Long ID;
	
	@NotNull
	private String name;

	@Id
	private int absPreisEntfernung;

	@Id
	private int absKmEntfernung;

	@Id
	private int absZeitEntfernung;

	@Id
	private boolean kopfBahnhof;

}
