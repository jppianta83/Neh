package operacional;
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
	
	//Requer uma string com o caminho de um arquivo
	//Caso consiga carregar o arquivo garante true
	//Caso não consiga carregar o arquivo garante false
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
	
	//Requer uma String com o ID de um parquimetro
	//Garante caso exista o Parquimetro na CLasse relatorio a referencia para ele
	//Caso não exista garante null
	public Parquimetro getParquimetro(String parq)
	{
		return this.parq.get(parq);
	}
	
	//Garante uma Lista com Strings do ID de todos os Parquimetros
	public List<String> getListaParquimetros()
	{
		List<String> resposta = new LinkedList<String>();
		resposta.addAll(this.parq.keySet());
		return resposta;
	}
}