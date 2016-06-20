import java.math.BigDecimal;

class Cartao {
	private String code;
	
	private BigDecimal saldo;
	/* requer x != null && val != null
	 * se x.length() != 128 ==> Codigo Invalido
	 * saldo == new BigDecimal(val)
	 * code == x
	 */
	public Cartao(String x, double val) throws CartaoException {
		if(x.length()!=128) {
			throw new CartaoException("Codigo invalido");		
		}
		saldo=new BigDecimal(val);
		code=x;
	}
	
	public BigDecimal getSaldo() {return saldo;}
	
	/* requires x.compareTo(BigDecimal.ZERO) != -1
	 * ensures saldo == x
	 */
	public void setSaldo(BigDecimal x) {saldo=x;}
}
