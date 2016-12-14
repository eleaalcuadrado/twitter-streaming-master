package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Coordenadas;

public class DatosCoordenadas implements Serializable {
	private static final long serialVersionUid = 1L;

	@Id
	@Column(name="id",  unique=true, nullable=false)
	private int id;

	@Column(name="coords", nullable=false)
	private Coordenadas coords;




	public DatosCoordenadas() {
	}


		
	

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Coordenadas getCoords() {
		return this.coords;
	}

	public void setCoords(Coordenadas coords) {
		
		this.coords = coords;
	}

}