import java.util.*;
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
		if(!tickets.contains(t))tickets.add(t);;
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
	public List<Ticket> getTicketsLogDia(String data)
	{
		//formato dd-mm-yyyy
		String[] vetorString = data.split("-");
		int dia = Integer.parseInt(vetorString[0]);
		int mes = Integer.parseInt(vetorString[1]);
		int ano = Integer.parseInt(vetorString[2]);
		
		LocalDate dt = LocalDate.of(ano, mes, dia);
		
		
		List<Ticket> resposta = new LinkedList<Ticket>();
		for(Ticket t: tickets)
		{
			if(t.getData().isEqual(dt))resposta.add(t);
		}
		return resposta;
	}
	public List<Ticket> getTicketsLogMes(String mes)
	{
		int mesS = Integer.parseInt(mes);
		
		List<Ticket> resposta = new LinkedList<Ticket>();
		for(Ticket t: tickets)
		{
			if(t.getData().getMonthValue() == mesS)resposta.add(t);
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
		Map<Integer, Map<Integer,Float>> Ano = new HashMap<Integer, Map<Integer,Float>>();
		List<String> resposta = new LinkedList<String>();
		
		for(Ticket t: tickets)
		{
			LocalDate data = t.getData();
			if(Ano.get(data.getYear()) == null)
			{
				Ano.put(data.getYear(),new HashMap<Integer,Float>());
				Ano.get(data.getYear()).put(data.getMonthValue(), t.valor().floatValue());
				
			}
			else
			{
				if(Ano.get(data.getYear()).get(data.getMonthValue()) == null)
				{
					Ano.get(data.getYear()).put(data.getMonthValue(),t.valor().floatValue()); 
				}
				else
				{
					float temp = Ano.get(data.getYear()).get(data.getMonthValue());
					temp += t.valor().floatValue();
					Ano.get(data.getYear()).put(data.getMonthValue(),temp);
				}	
			}
		}
		for(int a : Ano.keySet())
		{
			Map<Integer,Float> h = Ano.get(a);
			for(int m: h.keySet())
			{
				resposta.add(Integer.toString(m)+"-"+Integer.toString(a)+": "+ Float.toString(h.get(m)));
			}			
		}
		return resposta;
	}


}
