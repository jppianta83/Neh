package Operacional;

import java.util.List;
import java.util.LinkedList;
import java.io.*;

public class TicketDaoDerby implements TicketDao {
	private List<Ticket> tickets;
	
	
	private boolean carregar() {
		//ver se existe um arquivo, se n existir criar .se e inicializar lista d tickets, se n, carregar .se e salvar lista d tickets.
		File in = new File("tickets.se");
		if (in.exists()) {
			try {
				FileInputStream fis = new FileInputStream("tickets.se");
				ObjectInputStream ois = new ObjectInputStream(fis);
				this.tickets = (List<Ticket>) ois.readObject();
				fis.close();
				ois.close();
			} 
			catch(ClassNotFoundException c) {
				c.printStackTrace();
				return false;
			}catch (Exception i) {
				System.out.println(i.toString());
				return false;
			} 
		} else {
			try {
				this.tickets = new LinkedList<Ticket>();
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
		return true;
		
	}
	
	public boolean salvarTicket(Ticket t) {
		if (!carregar()) return false;
		tickets.add(t);
		if (!refresh()) return false;
		return true;
	}
	
	private boolean refresh() {
		//if (!carregar()) return false;
		try {
				
				FileOutputStream fis = new FileOutputStream("tickets.se");
				ObjectOutputStream ois = new ObjectOutputStream(fis);
				ois.writeObject(tickets);
				ois.close();
				fis.close();
			} catch (Exception i) {
				System.out.println(i.toString());
				i.printStackTrace();
				return false;
			}
		return true;
	}
	
}