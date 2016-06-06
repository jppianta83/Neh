import java.math.BigDecimal;

public class PagamentoCartao {
	public void DebitaCartao(Cartao c, BigDecimal val) {
		c.setSaldo(c.getSaldo().subtract(val));
	}
}
