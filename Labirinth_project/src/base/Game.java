package base;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import elements.Map;

public class Game {
	
	private Map map;
	private List<Map> maplist;
	//TODO:
	private Timer timer;
	
	private String winner;											//HOZZÁADTA Attila, oka : TestResult
	
	public Game(){}
	
	public Map mapSelect(List<Map> maplist){
		this.maplist = maplist;
		return maplist.get(0);
		//...selecting map
	}
	
	public void gameEvent(){}
	//TODO:
	public void end(){
		
	}
	//TODO:
	public void endDeath() {
		
	}
	//TODO:
	public void randomZpmSpawner(){
		/*
		 * 7.1.2.1 ZPM spawn doksi
		 */
	}
	
	public String getWinner(){											//HOZZÁADTA Attila, oka : TestResult
		return winner;
	}
}
