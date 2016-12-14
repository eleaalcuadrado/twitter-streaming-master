package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Datos;

public class Servicio implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Column(name="key", nullable=false, length=45)
	private String key;

	@Column(name="nonStackable", nullable=false, length=45)
	private boolean nonStackable;

	@Column(name="values", nullable=false)
	private List<Datos> values;

	public Servicio() {
	}


		
	

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean getNonStackable() {
		return this.nonStackable;
	}

	public void setNonStackable(boolean nonStackable) {
		this.nonStackable = nonStackable;
	}

	public List<Datos> getValues() {
		return this.values;
	}

	public void setValues(List<Datos> values) {
		
		this.values = values;
	}

}