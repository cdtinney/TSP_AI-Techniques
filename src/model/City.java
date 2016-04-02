package model;

public class City {
	
	private int x;
	private int y;
	
	private String name;
	
	public City(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public City(String name, int x, int y) {
		this(x, y);
		this.name = name;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getName() {
		return name;
	}

	public double distanceTo(City city) {
		
		int xDiff = Math.abs(x - city.getX());
	    int yDiff = Math.abs(y - city.getY());
	    return Math.sqrt((xDiff*xDiff) + (yDiff*yDiff));
	    
	}
    
    @Override
    public String toString(){
        return x + ", " + y;
    }

}
