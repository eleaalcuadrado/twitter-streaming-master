package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



public class Datos implements Serializable {
	private static final long serialVersionUID = 1L;



	@Column(name="x", nullable=false, length=45)
	private int x;

	@Column(name="y", nullable=false, length=45)
	private int y;

	public Datos() {
	}


	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	

}