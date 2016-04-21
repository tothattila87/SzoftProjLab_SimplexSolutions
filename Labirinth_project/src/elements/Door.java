package elements;

import base.Coord;

public class Door extends Element{
	
	public Door(Coord c) throws Exception {
		super(c);
	}

	public Door(int x, int y) throws Exception {
		super(x, y);
	}
	private boolean isClosed = true;
	
	@Override
	public boolean allowStep() {
		return isClosed == true ? false : true;
	}
	
	@Override
	public boolean laserHitDetect(Coord c, Map map) {
		//apró megkötés a mapBuilderben: nem lehet majd ajtóban letenni a dobozt, mert hanem nemjó az alábbi logika 
		return isClosed == true ? true : false;
	}
	
	public void setState(){
		isClosed = !(isClosed);
	}
	
	public boolean allowBox()
	{
		return isClosed == true ? false : true;
	}
}