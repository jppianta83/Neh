import java.time.LocalTime;

public class Ticket {

	private String idParquimetro;
	
	private String endereco;
	
	private String serial;
	
	private LocalTime emissao;
	
	private LocalTime validade;
	
	public Ticket(String idParquimetro, String endereco, String serial, LocalTime emissao, LocalTime validade) {
		this.idParquimetro = idParquimetro;
		this.endereco = endereco;
		this.serial = serial;
		this.emissao = emissao;
		this.validade = validade;
	}

	
}

