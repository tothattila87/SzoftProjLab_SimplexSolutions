package elements;

import base.Coord;

public class Field extends Element{
	public Field(Coord c) throws Exception {
		super(c);
	}
	public Field(int x, int y) throws Exception {
		super(x, y);
	}
	@Override
	public boolean spawnZpm(){
		if(zpmOnIt)
			return false;
		return true;
	}
	@Override
	public boolean allowStep()
	{
		if(boxOnIt != 0)
			return false;
		return true;
	}
	
	//TODO: ZPM ++ eset
	@Override
	public void onStep(Player player){
		
	}
}
