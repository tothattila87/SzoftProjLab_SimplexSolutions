package elements;

import base.Constants;
import base.Coord;
import base.Game;

public class Player{
	
	public enum Direction{
		up, down, left, right;
	}

	private int zpm = 0;
	private int weight = Constants.scaleWeightLimit;
	private Coord yellowPortalCoord;
	private Coord bluePortalCoord;
	private Coord pos; 
	private Game game;
	boolean has_box = false;
	private boolean laser_color = false;
	private Direction dir;
	private Map map;
	
	public Player(Coord entryCoord, Map map, Game game){
		pos = entryCoord;
		yellowPortalCoord = new Coord(-1, -1);
		bluePortalCoord = new Coord(-1, -1);
		this.map = map;
		this.game = game;
		
	}

	public void moveUp()
	{
		dir = Direction.up;
		Coord c = new Coord(pos.getX(), pos.getY() - 1);
		if(map.getElement(c).allowStep())
			goThere(c);
	}
	public void moveDown()
	{
		dir = Direction.down;
		Coord c = new Coord(pos.getX(), pos.getY() + 1);
		if(map.getElement(c).allowStep())
			goThere(c);
	}
	
	public void moveLeft()
	{
		dir = Direction.left;
		Coord c = new Coord(pos.getX() - 1, pos.getY());
		if(map.getElement(c).allowStep())
			goThere(c);
	}
	
	public void moveRight()
	{
		dir = Direction.right;
		Coord c = new Coord(pos.getX() + 1, pos.getY());
		if(map.getElement(c).allowStep())
			goThere(c);
	}
	

	
	public void shoot()
	{
		int x = pos.getX();
		int y = pos.getY();
		
		//konvencio: ha portalban allok nem lohetek portalokat
		if(pos.equals(yellowPortalCoord) || pos.equals(bluePortalCoord))
			return;
		
		int i;
		Coord portal = laser_color == true ? yellowPortalCoord : bluePortalCoord;
		
		switch(this.dir){
			case up: for(i = y;  !map.getElement(i, x).laserHitDetect(portal, map); --i); break;
			case down: for(i = y;  !map.getElement(i, x).laserHitDetect(portal, map) || i < map.getHeight(); ++i); break;
			case right: for(i = x;  !map.getElement(y, i).laserHitDetect(portal, map) || i < map.getWidth(); ++i); break;
			case left: for(i = x;  !map.getElement(y, i).laserHitDetect(portal, map) || i > 0; --i); break;
		}
		changeLaserColor();
	}
	
	public void dropBox(){
		Coord c = inFrontOfMe();
		if(map.getElement(c).allowBox()){
			map.getElement(c).setBoxOnIt();
			has_box = false;
		}
			
		
	}
	
	//visszatér azzal a koordinátával ami elõttünk van
	public Coord inFrontOfMe(){
		Coord c = new Coord(-1, -1);
		switch(dir){
			case up: c = new Coord(pos.getX(), pos.getY() - 1); break;
			case down: c = new Coord(pos.getX(), pos.getY() + 1);  break;
			case left: c = new Coord(pos.getX() - 1, pos.getY()); break;
			case right: c = new Coord(pos.getX() + 1, pos.getY()); break;
			default: break;
		}
		
		return c;
	}
	
	public void setBluePortalCoord(Coord c)
	{
		if(!c.isValid())
			throw new ArrayIndexOutOfBoundsException("Bad indexing!");
		bluePortalCoord = c;
	}
	public void setYellowPortalCoord(Coord c)
	{
		if(!c.isValid())
			throw new ArrayIndexOutOfBoundsException("Bad indexing!");
		yellowPortalCoord = c;
	}
	
	public Coord getBluePortalCoord(){ return bluePortalCoord; }
	
	public Coord getYellowPortalCoord(){ return yellowPortalCoord; }
	
	//round-robin direction changing: right->down->left->up->right....
	public void changeDir(){
		
		Direction dirs[] = Direction.values();
		
		int i;
		for(i = 0; i < dirs.length || this.dir == dirs[i]; ++i);
		
		if(i == dirs.length)
			this.dir = dirs[0];
		else
			this.dir = dirs[i];	
	}
	
	public void changeLaserColor(){
		this.laser_color = !(this.laser_color);
	}
	
	public boolean getLaserColor(){
		return laser_color;
	}
	//TODO: 
	private void goThere(Coord c) {
		pos = c;
		//Graphical stuff here, eg.: transition between two field
		//onStep + leave
	}
	
	public void dieByHole(){
		game.endDeath();
	}

	public void grabBox() {
		Element e = map.getElement(inFrontOfMe());
		if(e.boxOnIt > 0 && has_box == false)
		{
			e.removeBox();
			has_box = true;
		}
	}
	
	public Coord getPlayerCoord(){												//HOZZÁADTA Attila, oka : TestResult
		return pos;
	}
	
	public boolean getPlayerHasBox(){											//HOZZÁADTA Attila, oka : TestResult
		return has_box;
	}
	
	public int getPlayerZpm(){													//HOZZÁADTA Attila, oka : TestResult
		return zpm;
	}
}
