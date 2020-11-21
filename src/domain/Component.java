package domain;

public class Component {
	private int[] power; //array of power sources
	private int[] lights; //array of lights
	private int[][] table; //2D matrix for solution
	private int[] wantedLights; // array that has the lights we want
	private ARROWS[][] arrows; // table of enums to get our movement pattern in table
	
	public Component() 
	{
		power = new int[1];
		lights = new int[1];
		wantedLights = new int[1];
	}
	
	public Component(int[] power, int[] lights) {
		super();
		this.power = power;
		this.lights = lights;
	}
	
	public Component(int[] power, int[] lights,int[][] table,int[] wantedLights,ARROWS[][] arrows) {
		super();
		this.power = power;
		this.lights = lights;
		this.table = table;
		this.wantedLights = wantedLights;
		this.arrows = arrows;
	}

	public int[] getPower() {
		return power;
	}

	public void setPower(int[] power) {
		this.power = power;
	}

	public int[] getLights() {
		return lights;
	}

	public void setLights(int[] lights) {
		this.lights = lights;
	}

	public int[][] getTable() {
		return table;
	}

	public void setTable(int[][] table) {
		this.table = table;
	}

	public int[] getWantedLights() {
		return wantedLights;
	}

	public void setWantedLights(int[] wantedLights) {
		this.wantedLights = wantedLights;
	}

	public ARROWS[][] getArrows() {
		return arrows;
	}

	public void setArrows(ARROWS[][] arrows) {
		this.arrows = arrows;
	}
}
