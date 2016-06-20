import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class Relatorios
{
	Map<String, Parquimetro> parq;
	TicketDao td;
	
	public  Relatorios()
	{
		this.td = new TicketDaoDerby();
		this.parq = new HashMap<String, Parquimetro>();
	}
	public boolean carregarArquivo(String path)
	{
		List<Ticket> tick = td.carregarTickets(path);
		if(tick.size() == 0)return false;
		
		
		
		
		return true;
	}
	
	
}