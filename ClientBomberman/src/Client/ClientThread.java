package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

public class ClientThread implements Runnable{
	private boolean running;
	
	DataInputStream entree;
	Socket so;
	ViewBombermanGame view;
	
	public ClientThread (DataInputStream entree,Socket so) {
		this.entree=entree;
		this.so=so;
		this.running=true;
	}
	
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			int i = 0;
			while (running) {
				String chaine = entree.readUTF();
				System.out.println(chaine.substring(0,6));
				if (chaine.substring(0,6).equals("/over ")) {
					running=false;
					so.close();
				}
				//la premier message reçu est l'initialisation du plateau
				else if (i==0) {
					Gson gson = new Gson();
					PanelBombermanJSON json = gson.fromJson(chaine,PanelBombermanJSON.class);
					PanelBomberman panel = new PanelBomberman(json.getSizeX(), json.getSizeY(), json.getWalls(), json.getBreakable_walls(), json.getListInfoAgents());
					view = new ViewBombermanGame(panel);
					++i;
				}
				else {
					Gson gson = new Gson();
					PanelBombermanJSON json = gson.fromJson(chaine,PanelBombermanJSON.class);
					view.getPanel().updateInfoGame(json.getBreakable_walls(), json.getListInfoAgents(), json.getListInfoItems(), json.getListInfoBombs());
					view.getjFrame().repaint();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
