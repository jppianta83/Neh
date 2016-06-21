import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.LocalDate;

import java.io.Serializable;

public class Ticket implements Serializable {

	private String idParquimetro;
	private String endereco;
	private String serial;
	private String pagamento;
	private  BigDecimal  valor;
	private  LocalTime emissao;
	private  LocalTime validade;
	private  LocalDate data;
	
	public Ticket(String idParquimetro, String endereco, String serial, LocalTime emissao, LocalTime validade, String fpagamento, BigDecimal tarf) {
		this.idParquimetro = idParquimetro;
		this.endereco = endereco;
		this.serial = serial;
		this.emissao = emissao;
		this.validade = validade;
		this.pagamento = fpagamento;
		this.valor = tarf;
		data = LocalDate.now();
	}
	
	public LocalDate getData()
	{
		return data;
	}
	
	public String toString()
	{
		return idParquimetro + "-" + serial + " Endereco: " + endereco + " Emissão: " + emissao + " Validade: " + validade + " Pagamento: " + pagamento + " Data: " + data;
	}
	
	public String getIdParquimetro() {
		return idParquimetro;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public String getSerial() {
		return serial;
	}
	
	public String getPagamento() {
		return pagamento;
	}
	
	public LocalTime getEmissao() {
		return emissao;
	}
	
	public LocalTime getValidade() {
		return validade;
	}
	public BigDecimal valor()
	{
		return valor;
	}

}

