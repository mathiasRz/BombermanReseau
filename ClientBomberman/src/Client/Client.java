package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	private Socket so;
	private String s; // le serveur
	private int p; // le port de connexion
	
	
	
	public Socket getSo() {
		return so;
	}

	public void setSo(Socket so) {
		this.so = so;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public Client(String s, int p) {
		this.s = s;
		this.p = p;
	}
	
	public void runClient() throws InterruptedException{
	{ 
		
		try{// on connecte un socket
			so = new Socket(s, p);
			System.out.println("vous êtes bien connectés au serveur, la partie va commencer !");
			
			PrintWriter sortie = new PrintWriter(so.getOutputStream(), true);
			DataInputStream entree = new DataInputStream(so.getInputStream());
			
			ClientThread client = new ClientThread(entree,so);
			Thread thread = new Thread(client);
			thread.start();
			
			while (client.isRunning()) {
				if (client.view!=null) {
					char c = client.view.getMove();
					client.view.setMove(' ');
					sortie.println(c);
				}
				Thread.sleep(500);
			}
		} catch(UnknownHostException e) {System.out.println(e);}
		catch (IOException e) {System.out.println("Aucun serveur n’est rattaché au port ");}
	}
	}
}
