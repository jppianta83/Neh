package Operacional;
import java.util.List;

public interface TicketDao {
	public List<Ticket> carregarTickets(String path);
}
