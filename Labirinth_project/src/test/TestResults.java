package test;

import java.util.ArrayList;

import elements.Map;
import base.Game;

public class TestResults {

	private Map map;
	private ArrayList<String> results=new ArrayList<String>();
	private Game game;
	
	public TestResults(Game g,Map m){
		map = m;
		game = g;
	}
	
	public ArrayList<String> getResults(){
		
		listPlayerCoords();
		boxCounts();
		zpmCounts();
		checkDoorState();
		listPortalsCoords();
		isHoleChangedToField();
		whoWon();
		isReplicatorAlive();
		
		return results;
	}
	
	private void listPlayerCoords(){
		String listplayercoords;
		listplayercoords = "listPlayerCoords" + " " + map.oneil.getPlayerCoord().getX() + " " + map.oneil.getPlayerCoord().getY() + " " + map.jaffa.getPlayerCoord().getX() + " " + map.jaffa.getPlayerCoord().getY();
		//System.out.println(listplayercoords);
		results.add(listplayercoords);
	}
	
	private void boxCounts(){
		String boxcounts;
		String oneilHasBox;
		String jaffaHasBox;
		int boxesOnMap=0;
		
		if(map.oneil.getPlayerHasBox())
			oneilHasBox="1";
		else
			oneilHasBox="0";
		
		if(map.jaffa.getPlayerHasBox())
			jaffaHasBox="1";
		else
			jaffaHasBox="0";
		
		for(int i = 0; i < map.getWidth() ; i++){
			for(int j = 0; j < map.getHeight() ; j++){
				boxesOnMap += map.getElement(i, j).getBoxOnIt();
			}
		}
		
		boxcounts="boxCounts" + " " + boxesOnMap + " " + oneilHasBox + " " + jaffaHasBox;
		results.add(boxcounts);
	}
	
	private void zpmCounts(){
		int oneilZpm=map.oneil.getPlayerZpm();
		int jaffaZpm=map.jaffa.getPlayerZpm();
		int allZpm=0;
		
		for(int i = 0; i < map.getWidth() ; i++){
			for(int j = 0; j < map.getHeight() ; j++){
				if(map.getElement(i, j).getZpmOnIt())
					allZpm ++;
			}
		}
		
		String zpmcounts="zpmCounts" + " " + oneilZpm + " " + jaffaZpm + " " + allZpm;
		results.add(zpmcounts);
	}
	
	//TODO:
	private void checkDoorState(){
		//checkDoorState <door_id> <state>

		//DOOR ID ??????????
	}
	
	private void listPortalsCoords(){
		String listportalscoords;
		String oneilPortals = map.oneil.getBluePortalCoord().getX() + " " + map.oneil.getBluePortalCoord().getY() + " " + map.oneil.getYellowPortalCoord().getX() + " " + map.oneil.getYellowPortalCoord().getY();
		String jaffaPortals = map.jaffa.getBluePortalCoord().getX() + " " + map.jaffa.getBluePortalCoord().getY() + " " + map.jaffa.getYellowPortalCoord().getX() + " " + map.jaffa.getYellowPortalCoord().getY();
		
		listportalscoords = "listPortalsCoords" + " " + oneilPortals + " " + jaffaPortals;
		results.add(listportalscoords);
	}
	
	private void isHoleChangedToField(){
		String isRepDead;
		String isholechangedtofield;
		
		if(map.replicator.getIsReplicatorDead())
			isRepDead = "true";
		else
			isRepDead = "false";
		
		isholechangedtofield = "isHoleChangedToField" + " " + isRepDead;
		results.add(isholechangedtofield);		
	}
	
	private void whoWon(){
		String whowon;
		String winner=game.getWinner();
		
		whowon = "whoWon" + " " + winner;
		results.add(whowon);
	}
	
	private void isReplicatorAlive(){
		String isRepDead;
		String isreplicatoralive;
		if(map.replicator.getIsReplicatorChangedToField())
			isRepDead = "false";
		else
			isRepDead = "true";
		
		isreplicatoralive = "isReplicatorAlive" + " " + isRepDead;
		results.add(isreplicatoralive);
	}
	/*
	listPlayerCoords <pos_p1_x> <pos_p1_y> <pos_p2_x> <pos_p2_y>
	
	boxCounts <box_count> <Player1_hasBox> <Player2_hasBox>
	
	zpmCounts <zpm_count_p1> <zpm_count_p2> <all_zpm>
	
	checkDoorState <door_id> <state>
	
	listPortalsCoords <pos_p1_x> <pos_p1_y> <pos_p2_x> <pos_p2_y>
	
	isHoleChangedToField <string>
	
	whoWon <string>
	
	isReplicatorAlive <false>

	*/
	
}
