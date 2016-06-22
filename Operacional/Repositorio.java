package Operacional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Repositorio {
	
	private Map< Moeda , Integer > dic;  
	private int totalMoedas;
	private BigDecimal totalValor;
	private static Repositorio rep;
	
	public Repositorio(){
		this.totalMoedas = 0;
		this.totalValor = BigDecimal.ZERO;
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
	
	/* ensures \result == false ==> m==null
	 * ensures \result ==> m!=null
	 * ensures \result ==> dic.get(m) == \old(dic.get(m))+1 
	 */
	public boolean addMoeda(Moeda m){
		if(m == null) return false;
		int qtd = dic.get(m);
		dic.put(m,(qtd + 1));
		totalMoedas++;
		totalValor = totalValor.add(m.valorMoeda());
//		System.out.println(m.valorMoeda()+ " ->" + totalValor);
		return true;
	}
	
	/* ensures \result == false ==> m==null && dic.get(m) <= 0
	 * ensures \result ==> m!=null
	 * ensures \result ==> dic.get(m) == \old(dic.get(m))-1 
	 */
	private boolean removerMoeda(Moeda m){
		if(m == null) return false;
		int qtd = dic.get(m);
		if (qtd <= 0) return false;
		dic.put(m,(qtd - 1));
		totalMoedas--;
		totalValor = totalValor.subtract(m.valorMoeda());
		return true;
	}
	
	/* garante que o resultado será a lista de moeda contendo o valor do troco
	 * ou menos
	 */
	public List<Moeda> troco(BigDecimal valorTroco){
		List<Moeda> m = new ArrayList<Moeda>();
		Moeda[] yourEnums = Moeda.class.getEnumConstants();
		for( Moeda i : yourEnums ){
			if(!removerMoeda(i)) continue;
			if(valorTroco.compareTo(i.valorMoeda()) == (-1) ) 
				{
					this.addMoeda(i); continue;
				}
			m.add(i);
			m.addAll(this.troco(valorTroco.subtract(i.valorMoeda())));
			break;
		}
		return m;
	}
	
	public int getTotalMoeda()
	{
		return totalMoedas;
	}
	
	public BigDecimal getTotalValor()
	{
		return totalValor;
	}

}
