import java.math.BigDecimal;

public class PagamentoCartao {
	public boolean DebitaCartao(Cartao c, BigDecimal val) {
		return c.setSaldo(c.getSaldo().subtract(val));
	}
}
