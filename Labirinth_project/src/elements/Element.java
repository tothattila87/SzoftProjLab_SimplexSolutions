package elements;

import base.Coord;

public abstract class Element{
	
	protected Coord coord = new Coord(0, 0);
	
	public Element(Coord c) throws Exception{
		if(c.isValid())
			coord.setCoord(c);
		else throw new Exception("Bad coordinate!");
			
	}
	
	public Element(int x, int y) throws Exception{
		Coord c = new Coord(x, y);
		if(c.isValid())
			coord.setCoord(c);
		else
			throw new Exception("Bad coordinate!");
	}
	
	protected int boxOnIt = 0;
	protected boolean zpmOnIt = false;
	
	public int getBoxOnIt(){							//HOZZÁADTA Attila, oka : TestResult
		return boxOnIt;
	}
	
	public boolean getZpmOnIt(){						//HOZZÁADTA Attila, oka : TestResult
		return zpmOnIt;
	}
	
	public boolean allowStep(){return true;}
	
	public boolean allowBox(){return true;}
	
	//TODO: ha olyan field-et hitel amin replikator van akkor a replikatornak annyi
	public boolean laserHitDetect(Coord portal, Map map){return false;}

	public void setBoxOnIt() {
		++boxOnIt;
	}

	public void setZpmOnIt(boolean b) {
		zpmOnIt = b;
	}
	//TODO:
	public boolean spawnZpm(){
		/*
		 * mindenhol meg kell irni, hogy az adott elementre spawnolhat-e zpm
		 */
		return false;
	}

	public void removeBox() {
		if(boxOnIt > 0)
			--boxOnIt;
	}
	//TODO: mi történjen ha rálépnek
	public void onStep(Player player){
		//azert kell megkapja a playert hogy lehessen novelni a ZPM erteket
	}
	//TODO: mi történjen ha elhagyják az elementet
	public void leave(){
			
	}
		
	//TODO: mi történjen ha rálép a replikátor
	public void onStepReplicator(){
			
	}
	//TODO: mi történjen ha elhagyják az elementet a replikátor
	public void leaveReplicator(){
				
	}
	
	//TODO: szabad-e ide lépjen a replikator
	public boolean allowStepReplicator(){
		return true;
	}
}
