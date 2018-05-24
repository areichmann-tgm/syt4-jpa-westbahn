package main.main;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Entity;

@Entity
public class Bahnhof {
	
	@Id
	@Column
	private long id;
	
	@NotNull
	@Column
	private String name;

	@NotNull
	@Column
	private int absPreisEntfernung;

	@NotNull
	@Column
	private int absKmEntfernung;

	@NotNull
	@Column
	private int absZeitEntfernung;

	@NotNull
	@Column
	private boolean kopfBahnhof;

}
