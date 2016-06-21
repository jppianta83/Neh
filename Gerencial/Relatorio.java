import java.util.*;

public class Relatorio
{
	private Map<String, Parquimetro> parq;
	private TicketDao td;
	private static Relatorio rel= null;
	
	public static Relatorio get()
	{
		if(rel == null)Relatorio.rel = new Relatorio();
		return Relatorio.rel;
	}
	
	
	private  Relatorio()
	{
		this.td = new TicketDaoDerby();
		this.parq = new HashMap<String, Parquimetro>();
	}
	public boolean carregarArquivo(String path)
	{
		List<Ticket> tick = td.carregarTickets(path);
		if(tick.size() == 0)return false;
		
		for(Ticket t: tick)
		{
			if(parq.get(t.getIdParquimetro()) == null)
			{
				Parquimetro p = new Parquimetro(t.getIdParquimetro());
				p.addTicket(t);
				parq.put(t.getIdParquimetro(), p);
			}
			else
			{
				Parquimetro p = parq.get(t.getIdParquimetro());
				p.addTicket(t);
			}
		}
		return true;
	}
	public Parquimetro getParquimetro(String parq)
	{
		return this.parq.get(parq);
	}
	public List<String> getListaParquimetros()
	{
		List<String> resposta = new LinkedList<String>();
		resposta.addAll(this.parq.keySet());
		return resposta;
	}
}