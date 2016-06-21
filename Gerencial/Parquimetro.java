package operacional;
import java.util.*;
import java.time.LocalDate;

public class Parquimetro{
	private String idParquimetro;	
	private List<Ticket> tickets;	


	
	public Parquimetro(String idParquimetro)
	{
		this.tickets = new LinkedList<Ticket>();
		this.idParquimetro = idParquimetro;
		
	}
	
	//Requer um Ticket não nulo
	//Garante que o ticket caso não tenha sido adicionado anteriormente seja adicionado a lista da Instancia tickets
	public boolean addTicket(Ticket t)
	{
		System.out.println("Ticket " + t.toString());
		if(!tickets.contains(t))tickets.add(t);;
		return true;
	}
	
	//Garante a String com o ID do Parquimetro
	public String getID()
	{
		return idParquimetro;
	}

	//Garante a Lista com todos os Tickets do Parquimetro
	public List<Ticket> getTickets()
	{
		return tickets;
	}
	
	//Garante uma lista com a Descricao de todos os tickets na lista de tickets da Instancia
	public List<String> getTicketsString()
	{
		List<String> resposta = new LinkedList<String>();
		for(Ticket t: tickets)
		{
			resposta.add(t.toString());
		}
		return resposta;
	}
	
	//Requer uma string representando um dia no formato "dd-mm-yyyy"
	//Garante uma lista de String com os logs dos tickets do dia especificado para o Parquimetro
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
	
	//Requer uma string representando um numero de 1 a 12 para cada mês do ano
	//garante uma lista de String com os logs dos tickets no mes especificado para o Parquimetro	
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
	
	//Requer uma string com o ID do parquimetro
	//Garante que caso exista o parquimetro retorne uma Lista de Strings com o total de vendas do parquimetro agrupado por mês
	//Garante que caso não exista o parquimetro que retorne uma Lista vazia
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
	
	//Requer uma string com o ID do parquimetro
	//Garante que caso exista o parquimetro retorne uma Lista de Strings com o total de vendas do parquimetro agrupado por ano
	//Garante que caso não exista o parquimetro que retorne uma Lista vazia
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
