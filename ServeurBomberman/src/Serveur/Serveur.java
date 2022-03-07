package Serveur;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Serveur {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		Socket so;
		ServerSocket ecoute;
		
		try {
			ecoute = new ServerSocket (1080);
			System.out.println("serveur mis en place");
			int i = 0;
			while (i<1) {
				so=ecoute.accept();
				Thread thread = new Thread(new ServeurThread(so));
				ServeurThread.clients.put(i,so);
				++i;
				thread.start();
			}
			
			ServeurThread.startGame();
			
		} catch (IOException e){
			e.getMessage(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
