package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



public class Coordenadas implements Serializable {
	private static final long serialVersionUID = 1L;



	@Column(name="latitude", nullable=false, length=45)
	private double latitude;

	@Column(name="longitude", nullable=false, length=45)
	private double longitude;

	public Coordenadas() {
	}


	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	

}