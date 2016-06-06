import java.math.BigDecimal;

class Cartao {
	private String code;
	private BigDecimal saldo;
	public Cartao(String x, double val) throws CartaoException {
		if(x.length()!=128) {
			throw new CartaoException("Codigo invalido");		
		}
		saldo=new BigDecimal(val);
		code=x;
	}
	public BigDecimal getSaldo() {return saldo;}
	public void setSaldo(BigDecimal x) {saldo=x;}
}
