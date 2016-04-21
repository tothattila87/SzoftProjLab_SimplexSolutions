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
		//visszaad egy random irányt
		return Direction.left;
	}
	//TODO:
	@Override
	public void dieByHole(){
		//isReplicatorChangedToField
		// A replikátornak át lesz írva a dieByHole() metódusa hogy ahelyett, hogy kilépne a játék, csak az adott szakadékot cseréli le field-re.
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
