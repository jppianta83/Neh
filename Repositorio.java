
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Repositorio {
	
	private Map< Moeda , Integer > dic;  
	private int totalMoedas;
	private int totalValor;
	private static Repositorio rep;
	
	private Repositorio(){
		this.totalMoedas = 0;
		this.totalValor = 0;
		dic = new HashMap< Moeda , Integer>();
		Moeda[] Valor = Moeda.values();
		for(Moeda i : Valor){
			dic.put(i, 0);
		}
	}
	
	public static Repositorio getRepositorio()
	{
		if(rep == null)
		{
			rep = new Repositorio();
		}
		return rep;
	}
	
	public boolean addMoeda(Moeda m){
		if(m == null) return false;
		int qtd = dic.get(m);
		dic.put(m,(qtd + 1));
		return true;
	}
	
	private boolean removerMoeda(Moeda m){
		if(m == null) return false;
		int qtd = dic.get(m);
		if (qtd <= 0) return false;
		dic.put(m,(qtd - 1));
		return true;
	}
	
	public List<Moeda> troco(BigDecimal valorTroco){
		List<Moeda> m = new ArrayList<Moeda>();
		for( Moeda i : dic.keySet() ){
			//System.out.println(removerMoeda(i) + " Moeda1 " + i.toString());
			if(!removerMoeda(i)) continue;
			//System.out.println(removerMoeda(i) + " Moeda2 " + i.toString());
			if(valorTroco.compareTo(i.valorMoeda()) == (-1) ) 
				{
					this.addMoeda(i);
					continue;
				}
			m.add(i);
			m.addAll(this.troco(valorTroco.subtract(i.valorMoeda())));
			break;
		}
		return m;
	}
	
	public static void main(String[] args){
	
		Repositorio temp = Repositorio.getRepositorio();
		for(int i = 0; i < 8; i++)temp.addMoeda(Moeda.VINTECINCO);
		for(int i = 0; i < 5; i++)temp.addMoeda(Moeda.CINCO);
		for(int i = 0; i < 2; i++)temp.addMoeda(Moeda.UM);
		System.out.println(temp.dic);
		BigDecimal bd = new BigDecimal(1.58);
		List<Moeda> lista = temp.troco(bd);
		System.out.println(temp.dic);
		System.out.println(lista);
	}
	

}
