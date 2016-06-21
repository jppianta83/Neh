package operacional;
import java.util.*;
import java.io.*;

public class Facade{

//Requer uma string representando um dia no formato "dd-mm-yyyy"
//Garante uma lista de String com os logs dos tickets do dia especificado para todos Parquimetros	
public List<String> logParquimetrosDia(String dia)
{
	List<Ticket> tk = new LinkedList<Ticket>();
	for(String id: Relatorio.get().getListaParquimetros() )
	{
		Parquimetro p = Relatorio.get().getParquimetro(id);
		tk.addAll(p.getTicketsLogDia(dia));
		
	}
	
	tk.sort(new Comparator<Ticket>(){
		public int compare(Ticket t1, Ticket t2)
		{
			return t1.getData().compareTo(t2.getData());			
		}
	});
	List<String> resposta = new LinkedList<String>();
	
	for(Ticket t: tk)
	{
		resposta.add(t.toString());
	}
	return resposta;
}

//Requer uma string representando um numero de 1 a 12 para cada mês do ano
//garante uma lista de String com os logs dos tickets no mes especificado para todos Parquimetros	
public List<String> logParquimetrosMes(String mes)
{
	List<Ticket> tk = new LinkedList<Ticket>();
	for(String id: Relatorio.get().getListaParquimetros() )
	{
		Parquimetro p = Relatorio.get().getParquimetro(id);
		tk.addAll(p.getTicketsLogMes(mes));
		
	}
	
	tk.sort(new Comparator<Ticket>(){
		public int compare(Ticket t1, Ticket t2)
		{
			return t1.getData().compareTo(t2.getData());			
		}
	});
	List<String> resposta = new LinkedList<String>();
	
	for(Ticket t: tk)
	{
		resposta.add(t.toString());
	}
	return resposta;
}

//Requer uma string com o ID do parquimetro
//Garante que caso exista o parquimetro retorne uma Lista de Strings com o total de vendas do parquimetro agrupado por mês
//Garante que caso não exista o parquimetro que retorne uma Lista vazia
public List<String> relatorioParquimetroMes(String parquimetro) 
{
	List<String> resposta = new LinkedList<String>();
	Parquimetro p = Relatorio.get().getParquimetro(parquimetro);
	if(p == null)return resposta;
	resposta.addAll(p.getTicketsMes());
	return resposta;
}

//Requer uma string com o ID do parquimetro
//Garante que caso exista o parquimetro retorne uma Lista de Strings com o total de vendas do parquimetro agrupado por ano
//Garante que caso não exista o parquimetro que retorne uma Lista vazia
public List<String> relatorioParquimetroAno(String parquimetro) 
{
	List<String> resposta = new LinkedList<String>();
	Parquimetro p = Relatorio.get().getParquimetro(parquimetro);
	if(p == null)return resposta;
	resposta.addAll(p.getTicketsAno());
	return resposta;
}

//Requer uma string com o caminho de um arquivo
//Caso consiga carregar o arquivo garante true
//Caso não consiga carregar o arquivo garante false
public boolean carregarArquivo(String path) 
{
	return Relatorio.get().carregarArquivo(path);
}

}
