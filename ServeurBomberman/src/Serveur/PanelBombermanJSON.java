package Serveur;

import java.util.ArrayList;

import fr.bomberman.mathias.utils.InfoAgent;
import fr.bomberman.mathias.utils.InfoBomb;
import fr.bomberman.mathias.utils.InfoItem;

public class PanelBombermanJSON {

	private int sizeX;
	private int sizeY;
	private boolean[][] walls;
	private ArrayList<InfoAgent> listInfoAgents;
	private boolean[][] breakable_walls;
	private ArrayList<InfoItem> listInfoItems;
	private ArrayList<InfoBomb> listInfoBombs;

	public PanelBombermanJSON(int sizeX, int sizeY, boolean[][] walls, boolean[][] breakable_walls, ArrayList<InfoAgent> listInfoAgents,ArrayList<InfoItem> listInfoItems, ArrayList<InfoBomb> listInfoBombs) {

		this.setSizeX(sizeX);
		this.setSizeY(sizeY);
		
		
		
		
		this.setWalls(walls);
		
		this.setListInfoAgents(listInfoAgents);
		this.setBreakable_walls(breakable_walls);
		
		this.setListInfoItems(listInfoItems);
		this.setListInfoBombs(listInfoBombs);
		
		
	}
	
	public ArrayList<InfoBomb> getListInfoBombs() {
		return listInfoBombs;
	}

	public void setListInfoBombs(ArrayList<InfoBomb> listInfoBombs) {
		this.listInfoBombs = listInfoBombs;
	}

	public ArrayList<InfoItem> getListInfoItems() {
		return listInfoItems;
	}

	public void setListInfoItems(ArrayList<InfoItem> listInfoItems) {
		this.listInfoItems = listInfoItems;
	}

	public boolean[][] getBreakable_walls() {
		return breakable_walls;
	}

	public void setBreakable_walls(boolean[][] breakable_walls) {
		this.breakable_walls = breakable_walls;
	}

	public ArrayList<InfoAgent> getListInfoAgents() {
		return listInfoAgents;
	}

	public void setListInfoAgents(ArrayList<InfoAgent> listInfoAgents) {
		this.listInfoAgents = listInfoAgents;
	}

	public boolean[][] getWalls() {
		return walls;
	}

	public void setWalls(boolean[][] walls) {
		this.walls = walls;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}
}
