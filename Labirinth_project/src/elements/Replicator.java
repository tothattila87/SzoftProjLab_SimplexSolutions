package elements;

import base.Coord;
import base.Game;
//TODO:
public class Replicator extends Player{
	
	private boolean isReplicatorDead = false;
	private boolean isReplicatorChangedToField = false;
	
	public Replicator(Coord entryCoord, Map map, Game game) {
		super(entryCoord, map, game);
		// TODO Auto-generated constructor stub
	}
	//TODO: 
	Direction randDirection(){
		//visszaad egy random ir�nyt
		return Direction.left;
	}
	//TODO:
	@Override
	public void dieByHole(){
		//isReplicatorChangedToField
		// A replik�tornak �t lesz �rva a dieByHole() met�dusa hogy ahelyett, hogy kil�pne a j�t�k, csak az adott szakad�kot cser�li le field-re.
	}
	//TODO:
	public void dieByLaser(){
		//isReplicatorDead
	}
	
	public boolean getIsReplicatorDead(){
		return isReplicatorDead;
	}
	
	public boolean getIsReplicatorChangedToField(){
		return isReplicatorChangedToField;
	}
}
