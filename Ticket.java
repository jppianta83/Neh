import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class Ticket {

	private String idParquimetro;
	private String endereco;
	private String serial;
	private LocalTime emissao;
	private LocalTime validade;
	
	private static List<Ticket> tickets = new LinkedList<Ticket>();
	private static TicketDao tk;
	
	public boolean salvarRelatorio()
	{
		boolean resposta = tk.salvarRelatorio(tickets) ;
	
	}	
	public Ticket(String idParquimetro, String endereco, String serial, LocalTime emissao, LocalTime validade) {
		this.idParquimetro = idParquimetro;
		this.endereco = endereco;
		this.serial = serial;
		this.emissao = emissao;
		this.validade = validade;
	}
	public String toString()
	{
		return idParquimetro + "-" + serial + " Endereço: " + endereco + " Emissão: " + emissao + " Validade: " + validade;
	}

	
}

