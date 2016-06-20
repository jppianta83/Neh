import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class Parquimetro{
	private String idParquimetro;	
	private List<Ticket> tickets;	


	public Parquimetro(String idParquimetro)
	{
		this.idParquimetro = idParquimetro;
	}
	public boolean addTicket(Ticket t)
	{
		tickets.add(t);
		return true;
	}
	
	public String getID()
	{
		return idParquimetro;
	}

	public List<Ticket> getTickets()
	{
		return tickets;
	}
	public List<String> getTicketsString()
	{
		List<String> resposta = new LinkedList<String>();
		for(Ticket t: tickets)
		{
			resposta.add(t.toString());
		}
		return resposta;
	}

	public List<String> getTicketsAno()
	{
		Map<Integer, Float> Ano = new HashMap<Integer, Float>();
		List<String> resposta = new LinkedList<String>();
		
		for(Ticket t: tickets)
		{
			LocalDate data = t.getData();
			if(Ano.get(data.getYear()) == null)
			{
				Ano.put(data.getYear(),t.valor().floatValue());
			}
			else
			{
				float temp = Ano.get(data.getYear());
				temp += t.valor().floatValue();
				Ano.put(data.getYear(),temp);	
			}
		}
		for(int i : Ano.keySet())
		{
			resposta.add(Integer.toString(i)+": "+ Float.toString(Ano.get(i)));			
		}
		return resposta;
	}

	public List<String> getTicketsMes()
	{
		Map<LocalDate, Float> Mes = new HashMap<LocalDate, Float>();
		List<String> resposta = new LinkedList<String>();
		
		for(Ticket t: tickets)
		{
			LocalDate data = t.getData();
			if(Mes.get(data.getMonth()) == null)
			{
				Mes.put(data.getMonth(),t.valor().floatValue());
			}
			else
			{
				float temp = Mes.get(data.getYear());
				temp += t.valor().floatValue();
				Mes.put(data.getYear(),temp);	
			}
		}
		for(int i : Mes.keySet())
		{
			resposta.add(Integer.toString(i)+": "+ Float.toString(Mes.get(i)));			
		}
		return resposta;
	}


}
