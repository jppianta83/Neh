package operacional;

import java.math.BigDecimal;
import java.util.List;

public class PagamentoMoeda implements IPagamento {

	private List<Moeda> moedas;
	private List<Moeda> troco;
	
	public PagamentoMoeda(List<Moeda> m){
		moedas = m;
	}
	
	public List<Moeda> mostrarTroco()
	{
		return troco;
	}
	
	/* requires val.compareTo(BigDecimal.ZERO) == 1
	 * garante se na lista moedas tem o valor de val, resultado true
	 * caso contrario false
	 */
	@Override
	public boolean fazPagamento(BigDecimal val) {
		BigDecimal total = new BigDecimal(0.0);
		Repositorio rep = Repositorio.getRepositorio();
		for ( Moeda m : moedas ){
			if (m == null) {
				return false;
			}
			total = total.add(m.valorMoeda());
		}
		if (total.compareTo(val)==(-1)) 
		{
		troco = moedas;
		return false;
		}
		for ( Moeda m : moedas ) rep.addMoeda(m);
		troco = rep.troco(total.subtract(val));
		return true;
	}

}
