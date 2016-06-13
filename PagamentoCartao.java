import java.math.BigDecimal;

public class PagamentoCartao implements IPagamento{
	private Cartao car;
	
	public PagamentoCartao(Cartao umCartao){
		car = umCartao;
	}
	
	public boolean fazPagamento (BigDecimal val) {
		if (car.getSaldo().compareTo(val)== -1){
			car.setSaldo(car.getSaldo().subtract(val));
			return true;
		}
		return false;
	}
}
