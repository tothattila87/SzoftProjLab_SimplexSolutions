package base;

public class Coord {
	private int x;
	private int y;
	
	public Coord(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	
	public void setCoord(Coord c){
		if(c.isValid()){
			this.x = c.x;
			this.y = c.y;
		}
		else
			throw new ArrayIndexOutOfBoundsException("Bad index!");
	}
	
	public boolean isValid(){
		return !(x < 0 || y < 0 || x >= Constants.map_width || y >= Constants.map_height);
			
	}
	
	@Override
	public boolean equals(Object o){
		Coord c = (Coord)o;
		return this.x == c.x && this.y == c.y;
	}
	
}
