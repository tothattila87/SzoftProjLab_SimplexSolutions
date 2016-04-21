package elements;

import base.Coord;

public class Hole extends Element{
	public Hole(Coord c) throws Exception {
		super(c);
	}

	public Hole(int x, int y) throws Exception {
		super(x, y);
	}
	
	@Override
	public boolean laserHitDetect(Coord portal, Map map) {
		return false;
	}
	
	@Override
	public void setBoxOnIt() {}
	
	//TODO: mi történjen ha rálépnek
	@Override
	public void onStep(Player player){
		
	}

}
