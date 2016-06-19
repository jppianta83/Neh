import java.util.List;
import java.util.LinkedList;
import java.io.*;

public class TicketDaoDerby implements TicketDao {
	private List<Ticket> tickets=null;
	
	
	private boolean carregar() {
		//ver se existe um arquivo, se n existir criar .se e inicializar lista d tickets, se n, carregar .se e salvar lista d tickets.
		File in = new File("tickets.se");
		if (in.exists()) {
			try {
				FileInputStream fis = new FileInputStream("tickets.se");
				ObjectInputStream ois = new ObjectInputStream(fis);
				tickets = (List<Ticket>) ois.readObject();
				fis.close();
				ois.close();
			} catch (IOException i) {
				i.printStackTrace();
				return false;
			} catch(ClassNotFoundException c) {
				System.out.println("classe ticket n existe");
				c.printStackTrace();
				return false;
			}
		} else {
			try {
				FileOutputStream fis = new FileOutputStream("tickets.se");
				ObjectOutputStream ois = new ObjectOutputStream(fis);
				ois.writeObject(tickets);
				ois.close();
				fis.close();
			} catch (IOException i) {
				i.printStackTrace();
				return false;
			}
		}
		in.close();
		
	}
	
}