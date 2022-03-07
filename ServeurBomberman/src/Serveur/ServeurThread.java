package Serveur;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.Gson;

import fr.bomberman.mathias.modele.BombermanGame;
import fr.bomberman.mathias.modele.InputMap;

public class ServeurThread implements Runnable{
	
	Socket so;
	static BombermanGame bomberman;
	static ConcurrentHashMap<Integer, Socket> clients = new ConcurrentHashMap<>();
	
	public ServeurThread (Socket so) {
		this.so=so;
	}
	@Override
	public void run() {
		try {
			BufferedReader entree = new BufferedReader(new InputStreamReader(so.getInputStream()));
			while (!so.isClosed()) {// le thread reste actif tant que le client est connecté
				
				String c = entree.readLine(); // on lit ce qui arrive
				
				System.out.println("on a reçu : |"+c+"|");
				if (c!=null) {
					bomberman.setActionJoueur1(c.charAt(0));
					for (Socket s : clients.values()) {
						PanelBombermanJSON pb = new PanelBombermanJSON(
								bomberman.getMap().getSizeX(),
								bomberman.getMap().getSizeY(),
								bomberman.getMap().get_walls(),
								bomberman.GetBreakableWalls(), 
								bomberman.get_agents(),
								bomberman.GetListInfoItems(),
								bomberman.GetListInfoBombs());
						Gson gson = new Gson();
						String json = gson.toJson(pb);
						DataOutputStream sortie = new DataOutputStream (s.getOutputStream());
						sortie.writeUTF(json);
					
					}
					
					//gerer les fins de parties
					if (!bomberman.getMessageGameOver().equals("")) {
						System.out.println(bomberman.getMessageGameOver().substring(0, 16));
						for (Socket s : clients.values()) {
							DataOutputStream sortie = new DataOutputStream (s.getOutputStream());
						
							sortie.writeUTF("/over "+bomberman.getMessageGameOver());
						}
					}
				}
				else {
					so.close();
				}
			}
			System.out.println("connexion fermée");
		} catch (IOException e) { System.out.println("problème\n"+e); }
	}
	
	public static void startGame() throws Exception {
		bomberman = new BombermanGame(50000,new InputMap("./layouts-20211021/niveau2.lay"));
		bomberman.init();
		for (Socket s : clients.values()) {
			PanelBombermanJSON pb = new PanelBombermanJSON(
					bomberman.getMap().getSizeX(),
					bomberman.getMap().getSizeY(), 
					bomberman.getMap().get_walls(), 
					bomberman.getMap().getStart_breakable_walls(), 
					bomberman.getMap().getStart_agents(),
					bomberman.GetListInfoItems(),
					bomberman.GetListInfoBombs());
			Gson gson = new Gson();
			String json = gson.toJson(pb);
			DataOutputStream sortie = new DataOutputStream (s.getOutputStream());
			sortie.writeUTF(json); // on renvoie la chaîne
		}
		bomberman.launch();
		System.out.println("on a envoyé le start");
	}

}
