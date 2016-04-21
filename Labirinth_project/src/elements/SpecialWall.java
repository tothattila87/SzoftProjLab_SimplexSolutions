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
	public boolean laserHitDetect(Coord c, Map map) // lehet hogy �tkellene adni mind a k�t port�l koordin�t�j�t mert, hogyha nem valid valamelyik akkor nem szabad isPortal = true legyen
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
	
	//TODO: mi t�rt�njen ha r�l�pnek
	@Override
	public void onStep(Player player){
		
	}
	//TODO: mi t�rt�njen ha elhagyj�k az elementet
	@Override
	public void leave(){
			
	}
	
	//mi t�rt�njen ha r�l�p a replik�tor
	@Override
	public void onStepReplicator(){
	}
	//mi t�rt�njen ha elhagyj�k az elementet a replik�tor
	@Override
	public void leaveReplicator(){
			leave();
	}
}
