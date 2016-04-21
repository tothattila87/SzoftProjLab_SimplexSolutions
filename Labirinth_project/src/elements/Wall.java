package elements;

import base.Coord;

public class Wall extends Element{
	
	public Wall(Coord c) throws Exception {
		super(c);
	}

	public Wall(int x, int y) throws Exception {
		super(x, y);
	}
	
	@Override
	public boolean allowStep() {
		return false;
	}
	
	@Override
	public boolean laserHitDetect(Coord portal, Map map) {
		return true;
	}
	@Override
	public boolean allowBox(){return false;}
}
