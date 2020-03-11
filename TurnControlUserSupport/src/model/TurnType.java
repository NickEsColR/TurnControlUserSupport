package model;

/**
* NICOLAS ESTEBAN COLMENARES RUIZ
* UNIVERSIDAD ICESI
* ALGORITMOS Y PROGRAMACION 2
* LABORATORIO 1 TurnControl 
* 09/03/2020
*/

public class TurnType {
	
	//Attributes
	
	private String name;
	private float time;

	//methods
	
	public TurnType(String name, float time) {
		this.name = name;
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public float getTime() {
		return time;
	}
	
	
}
