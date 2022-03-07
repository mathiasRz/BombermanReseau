package Client;

public class Main {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (args.length==2) {
			String s=args[0];
			int p=Integer.parseInt(args[1]);
			
			Client client = new Client(s,p);
			
			try {
				client.runClient();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("syntaxe d'appel : java Client.Main serveur port");
		}
	}
}

