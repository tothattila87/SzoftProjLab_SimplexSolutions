package elements;

import java.util.ArrayList;
import java.util.List;

import base.Constants;
import base.Coord;

public class Map {
	
	private String name;
	private List<String> toplist;
	private Element [][] listElements;
	public Player oneil;
	public Player jaffa;
	public Replicator replicator=new Replicator(new Coord(-1, -1), this, null);
	private int mapHeight,mapWidth;							//HOZZÁADTA Attila, oka : TestResult

	public Map(int h, int w)
	{
		Constants.map_height = h;
		Constants.map_width = w;
		listElements = new Element[w][h];
		//toplist = new ArrayList<String>();
		//init(h, w);
		mapHeight = h;										//HOZZÁADTA Attila, oka : TestResult
		mapWidth = w;
	}
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Map copiedMap = new Map(mapWidth,mapHeight);
		copiedMap.listElements = this.listElements.clone();
		copiedMap.name = this.name;
		copiedMap.toplist = this.toplist;
		copiedMap.oneil = this.oneil.clone();
		copiedMap.jaffa = this.jaffa.clone();
		copiedMap.replicator = this.replicator.clone();
		
		return copiedMap;
	}


	public int getHeight(){
		return mapHeight; //Constants.map_height;			//VÁLTOZTATVA Attila, oka : Testmapoknál mindet betöltöd és végül a constants-ban nem csak az utolsó map szélessége / magassége lesz???
	}
	public int getWidth(){
		return mapWidth; //Constants.map_width;
	}
	public Element getElement(int x, int y){
		return listElements[x][y];
	}
	public Element getElement(Coord c){
		return listElements[c.getX()][c.getY()];
	}
	public void setElement(Coord c, Element e){
		if(c.isValid())
			listElements[c.getX()][c.getY()] = e;
		else
			throw new ArrayIndexOutOfBoundsException("Bad indexing!");
	}
	//mapbuildernek alap felallas
	public void init(){
		try {
			
			for(int i = 0; i < Constants.map_height; ++i)
				for(int j = 0; j < Constants.map_width; ++j)
					listElements[i][j] = new Field(i, j);
			
			for(int i = 0; i < Constants.map_width; ++i){
				listElements[0][i] = new Wall(0, i);
				listElements[Constants.map_height][i] = new Wall(Constants.map_height, i);
			}
			
			for(int i = 0; i < Constants.map_height; ++i){
				listElements[i][0] = new Wall(i, 0);
				listElements[i][Constants.map_width] = new Wall(i, Constants.map_width);
			}
			
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
}
