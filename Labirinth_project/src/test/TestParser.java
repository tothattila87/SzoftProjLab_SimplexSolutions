package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import base.Coord;
import base.Game;
import elements.Element;
import elements.Field;
import elements.Map;
import elements.Player;
import elements.Scale;
import elements.SpecialWall;
import elements.Wall;
import elements.Replicator;

public class TestParser {
	
	public static final String MOVE_RIGHT = "moveRight";
	public static final String MOVE_LEFT = "moveLeft";
	public static final String MOVE_UP = "moveUp";
	public static final String MOVE_DOWN = "moveDown";
	public static final String MAP_SELECT = "mapSelect";
	public static final String GRAB_BOX = "grabBox";
	public static final String DROP_BOX = "dropDown";
	public static final String PLAYER_1 = "player1";
	public static final String PLAYER_2 = "player2";
	public static final String SHOOT = "shoot";
	
	public static String testCases = "testfiles/cases";
	public static String testMapFile = "testfiles/testmaps.txt";
	public static Game game = new Game();
	
	public static List<Map> maplist;
	public static Map testmap, tempMap;
	public static Map resultmap;
	
	public static void main(String[] args){
		
		maplist = load_maplist(testMapFile);
		
		File folder = new File(testCases);
		File[] listOfFiles = folder.listFiles();
		
		for(int i = 0; i < listOfFiles.length; ++i)
			solveFile(listOfFiles[i]);

	}
	
	public static void solveFile(File f){
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			System.out.println("Testfile: " + f.getName());
			String line = br.readLine();
			System.out.println("case title: " + line);
			br.readLine();
			boolean finish = false;
			
			//reading the commands line by line
			while(!(line = br.readLine()).equals("---")){
					parse(line);
				//System.out.println("cmd:" + line);
			}

			ArrayList<String> expectedOutput = new ArrayList<String>();
			System.out.println("\nExpected output:");
			while(!(line = br.readLine()).equals("---"))
			{
					expectedOutput.add(line);
					System.out.println("   " + line);
			}
			
			TestResults ts = new TestResults(game,testmap);   //testmap???
			ArrayList<String> output = ts.getResults();
			
			System.out.println("Results output:");
			for(int i = 0; i<output.size();i++){
				System.out.println("   " + output.get(i));
			}
			
			boolean testok = false;
			for(int i=0 ; i < expectedOutput.size() ; i++){
				for(int j = 0 ; j < output.size() ; j++){
					if(expectedOutput.get(i).equals(output.get(j)))
						testok=true;
				}
			}
			
			if(testok)
				System.out.println("Test OK.");
			else
				System.out.println("Test Failed. :(");
			
			System.out.println("");
			System.out.println("");
			/*
			 * TODO: tesztesetek kimeneteinek kezelése
			 */
			  //asd
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//gets one line of command and resolves it
	private static void parse(String line) {
		StringTokenizer st = new StringTokenizer(line, " ");
		String arg1 = st.nextToken();
		String arg2 = st.nextToken();
		if(arg1.equals(MAP_SELECT))
			testmap = mapselect(arg2, maplist);
		else{
			Player player;
			if(arg2.equals(PLAYER_1))   //TODO REPLICATOR IS LEHET "Player"
				player = testmap.oneil;
			else
				player = testmap.jaffa;
			
			switch(arg1){
				case MOVE_RIGHT: player.moveRight(); /*System.out.println(MOVE_RIGHT); */break;
				case MOVE_LEFT: player.moveLeft(); /*System.out.println(MOVE_LEFT);*/ break;
				case MOVE_UP: player.moveUp(); /*System.out.println(MOVE_UP);*/ break;
				case MOVE_DOWN: player.moveDown(); /*System.out.println(MOVE_DOWN);*/ break;
				case SHOOT: player.shoot(); /*System.out.println(SHOOT);*/ break;
				case DROP_BOX: player.dropBox(); /*System.out.println(DROP_BOX); */break;
				case GRAB_BOX: player.grabBox();/* System.out.println(GRAB_BOX); */break;
				
				//TODO:random mozgás kikapcsolása replikátornak
				default: break;
			}
		}
	}
	
	private static Map mapselect(String arg2, List<Map> maplist) {
		int idx = Integer.parseInt(arg2);
		if(idx > maplist.size() || idx < 0)
		{
			System.out.println("TestMap doesn't exists.");
			return null;
		}
		return maplist.get(idx);//változik e az eredeti map??????????
	}
	
	@SuppressWarnings("unused")
	private static void printMap(String[] map, int rows){
		for(int i = 0; i < rows; ++i)
			System.out.println(map[i] + "\n");
	}
	
	private static List<Map> load_maplist(String filename){
		List<Map> list = new ArrayList<>();
		String[] map = new String[20]; 
		int row = 0;
		String line = "";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File(filename)));
			while((line = br.readLine()) != null){
				
				if(!line.equals(""))
					map[row++] = line;
				else{
					//printMap(map, row);
					list.add(getMap(map, row));
					map = new String[20];
					row = 0;
				}
			}
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//transforms the string array (2d char array) to map
	private static Map getMap(String[] map, int rows) {
		int width = map[0].length();
		int height = rows;
		//System.out.println("width: " + width);
		//System.out.println("height: " + height);
		resultmap = new Map(width, height);
		
		for(int i = 0; i < height; ++i){
			char[] row = map[i].toCharArray();
			for(int j = 0; j < width; ++j)
			{
				Coord pos = new Coord(i, j);
				//System.out.println("wow: " + pos.getX() + " " + pos.getY());
				resultmap.setElement(pos, translate(row[j], pos, resultmap));
			}
		}		
		return resultmap;
	}
	
	private static Element translate(char c, Coord pos, Map map) {
		Element element = null;
		try{
		switch(c){
			case 'W': 
				element = new Wall(pos);
				break;
			case 'V':
				element = new SpecialWall(pos);
				break;
			case 'B':
				element = new Field(pos);
				element.setBoxOnIt();
				break;
			case 'S':
				element = new Scale(pos);
				break;
			case 'O':
				element = new Field(pos);
				map.oneil = new Player(pos, map, game);
				break;
			case 'J':
				element = new Field(pos); 
				map.jaffa = new Player(pos, map, game);
				break;
			case 'R':
				element = new Field(pos); 
				map.replicator = new Replicator(pos, map, game);
				break;
			case 'Z':
				element = new Field(pos);
				element.setZpmOnIt(true);
				break;
			case ' ':
				element = new Field(pos);
				break;
			default: 
				element = new Field(pos);
				break;
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return element;
	}
}
