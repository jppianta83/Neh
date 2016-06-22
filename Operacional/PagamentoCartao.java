package Operacional;

import java.math.BigDecimal;

public class PagamentoCartao implements IPagamento{
	private Cartao car;
	
	public PagamentoCartao(Cartao umCartao){
		car = umCartao;
	}
	
	/* requires val != null
	 * ensures \result == false ==> car.getSaldo().compareTo(val)== -1
	 * ensures \result ==> !(car.getSaldo().compareTo(val)== -1)
	 * ensures car.getSaldo() == \old(car.getSaldo()).subtract(val)
	 */
	public boolean fazPagamento (BigDecimal val) {
		if (!(car.getSaldo().compareTo(val)== -1)){
			car.setSaldo(car.getSaldo().subtract(val));
			return true;
		}
		return false;
	}
}
