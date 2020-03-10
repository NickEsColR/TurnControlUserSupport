package model;

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
