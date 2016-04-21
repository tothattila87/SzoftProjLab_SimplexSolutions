package elements;

import base.Constants;
import base.Coord;

public class Scale extends Element{
	
	private boolean isPressed = false;
	private Door door;
	
	public Scale(Coord c, Door door) throws Exception {
		super(c);
		this.door = door;
	}

	public Scale(int x, int y, Door door) throws Exception {
		super(x, y);
		this.door = door;
	}
	
	public Scale(Coord c) throws Exception{
		super(c);
		door = null;
	}
	
	public void changeDoor(){
		if(door != null)
			door.setState();
	}
	
	public boolean getState(){
		return isPressed;
	}
	
	@Override
	public void setBoxOnIt() {
		++boxOnIt;
		if(boxOnIt * Constants.boxWeight >= Constants.scaleWeightLimit){
			isPressed = true;
			changeDoor();
		}
			
	}
	
	public boolean allowStep()
	{
		if(boxOnIt != 0)
			return false;
		return true;
	}
	
	public void onStep(){
		isPressed = true;
		changeDoor();
	}
	
	public void leave(){
		isPressed = false;
		changeDoor();
	}

}
