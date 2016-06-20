import java.util.List;
import java.util.LinkedList;
import java.io.*;

public class TicketDaoDerby implements TicketDao {
	private List<Ticket> tickets=null;
	
	
	private boolean carregar(String path) {
		//ver se existe um arquivo, se n existir criar .se e inicializar lista d tickets, se n, carregar .se e salvar lista d tickets.
		File in = new File(path);
			try {
				FileInputStream fis = new FileInputStream(path);
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
		return true;	
	}
	public List<Ticket> carregarTickets(String path)
	{
		this.carregar(path);
		return tickets;
	}	
}
