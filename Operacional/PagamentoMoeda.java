import java.math.BigDecimal;
import java.util.List;

public class PagamentoMoeda implements IPagamento {

	private List<Moeda> moedas;
	
	public PagamentoMoeda(List<Moeda> m){
		moedas = m;
	}
	
	@Override
	public boolean fazPagamento(BigDecimal val) {
		BigDecimal total = new BigDecimal(0.0);
		Repositorio rep = Repositorio.getRepositorio();
		for ( Moeda m : moedas ){
			if (m == null) {
				System.out.println("exceção moeda invalida!");
				return false;
			}
			total = total.add(m.valorMoeda());
		}
		if (total.compareTo(val)==(-1)) return false;
		for ( Moeda m : moedas ) rep.addMoeda(m);
		rep.troco(total.subtract(val));
		return true;
	}

}
