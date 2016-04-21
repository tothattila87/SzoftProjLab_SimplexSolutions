package elements;

import base.Coord;

public class SpecialWall extends Element{
	
	boolean isPortal;
	
	public SpecialWall(Coord c) throws Exception {
		super(c);
		isPortal = false;
		
	}

	public SpecialWall(int x, int y) throws Exception {
		super(x, y);
		isPortal = false;
	}
	
	@Override
	public boolean laserHitDetect(Coord c, Map map) // lehet hogy átkellene adni mind a két portál koordinátáját mert, hogyha nem valid valamelyik akkor nem szabad isPortal = true legyen
	{
		try {
			if(c.isValid()){
				map.setElement(c, new SpecialWall(c));
			}	 
			c.setX(this.coord.getX());
			c.setY(this.coord.getY());
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		return true;
	}
	
	@Override
	public boolean allowStep(){
		return isPortal == true ? true : false;
	}
	
	@Override
	public boolean allowBox(){return false;}
	
	//TODO: mi történjen ha rálépnek
	@Override
	public void onStep(Player player){
		
	}
	//TODO: mi történjen ha elhagyják az elementet
	@Override
	public void leave(){
			
	}
	
	//mi történjen ha rálép a replikátor
	@Override
	public void onStepReplicator(){
	}
	//mi történjen ha elhagyják az elementet a replikátor
	@Override
	public void leaveReplicator(){
			leave();
	}
}
