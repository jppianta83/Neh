package operacional;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Properties;
import java.time.LocalDate;

public class ModuloOperacional {
	private static Properties prop;
	private static Mostrador most;
	private static int serial;
	public static long tempoEstadia;
	private static LocalDate dataUltimoPagamento = LocalDate.of(1990,1,1);
	private static TicketDao td = new TicketDaoDerby();
	
	

	/* garante false se der problema na leitura
	 * garante true se nao der problema na leitura
	 */
	private static boolean carregarConfiguracao()
	{
		prop = new Properties();
		String path = "config";
		File arq = new File(path);
		InputStream ip;
		try {
			ip = new FileInputStream(arq);
			prop.load(ip);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
			return true;	
	}
	
	private static boolean SalvarConfiguracao()
	{
		String path = "config";
		File arq = new File(path);
		OutputStream op;
		try {
			op = new FileOutputStream(arq);
			prop.store(op, "oi =)");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
			return true;
	}
	
	/*@ requires key != null
	 *@ requires val != null
	 *@ ensures \result ==> prop.containsKey(key)
	 *@ ensures \result == false ==> !prop.containsKey(key) 
	 */ 
	public static boolean alterarConfiguracao(String key, String val)
	{
		if (!prop.containsKey(key)) return false;
		prop.setProperty(key, val);
		return SalvarConfiguracao();
	}
	
	
	public static void main(String[] args)
	{
		prop = new Properties();
		prop.setProperty("InicioHorario", "8:30");
		prop.setProperty("FimHorario", "18:30");
		prop.setProperty("TempoMinimo", "30");
		prop.setProperty("TarifaInicial", "0,75");
		prop.setProperty("TempoMaximo", "120");
		prop.setProperty("Incremento", "10");
		prop.setProperty("TarifaIncremento", "0,25");
		SalvarConfiguracao();
	}
	
	/*@ requires str != null
	 */
	private static BigDecimal string2bigDec(String str){
		return new BigDecimal(str.replaceAll(",", "."));
	}
	
	/*@ requires str != null
	 *@ ensures \result == Long.parseLong(str)*60*1000 
	 */
	private static long string2duration(String str){
		long l = Long.parseLong(str);
		return l*60*1000;
	}
	//Apenas formato 00:00
	/*@ requires data != null
	 *@ requires data.charAt(2) == ':'
	 *@ ensures \result == LocalTime.of(Integer.parseInt(data.split(":")[0]), Integer.parseInt(data.split(":")[1]))
	 */
	private static LocalTime string2localTimeHrMin(String data){
		return LocalTime.of(Integer.parseInt(data.split(":")[0]), Integer.parseInt(data.split(":")[1]));	
	}
	
	// Apenas para String com o formato 00:00:00
	/*@ requires data != null
	 *@ requires data.charAt(2) == ':' && data.charAt(5) == ':'
	 *@ ensures \result == LocalTime.of(Integer.parseInt(data.split(":")[0]), Integer.parseInt(data.split(":")[1]), Integer.parseInt(data.split(":")[2]))
	 */
	private static LocalTime string2localTimeHrMinSeg(String data){
		return LocalTime.of(Integer.parseInt(data.split(":")[0]), Integer.parseInt(data.split(":")[1]), Integer.parseInt(data.split(":")[2]));
	}
	
	/*@ ensures serial == 0
	 *@ ensures \result
	 */
	private static boolean resetSerial()
	{
		serial = 0;
		return true;
	}
	
	/*@ ensures serial == 0
	 *@ ensures tempoEstadia == Long.parseLong(prop.getProperty("TempoMinimo")) 
	 */
	public static void inicializacao(){
		carregarConfiguracao();
		most = new Mostrador();
		serial = 0;
		tempoEstadia = Long.parseLong(prop.getProperty("TempoMinimo"));
	}
	
	
	public static boolean lerCartao(Cartao cartao)
	{
		return most.mostrarMensagem(cartao.toString(), 30);
		
	}
	/*@ requires validade >= 0
	 *@ requires p != null
	 *@ requires tarifa.compareTo(BigDecimal.ZERO) == 1  
	 */
	private static boolean criarTicket(long validade, IPagamento p, BigDecimal tarifa)
	{
		String pagamento = "";
		if(p instanceof PagamentoMoeda)pagamento = "Moeda";
		else pagamento = "Cart�o";
		td.salvarTicket(new Ticket(prop.getProperty("id"), prop.getProperty("Endereco"), Integer.toString(serial, 5), LocalTime.now(), (LocalTime.now().plusMinutes(validade)), pagamento, tarifa));
		
		return true;
	}
	
	/*@ ensures \result == false ==> (tempoEstadia + Long.parseLong(prop.getProperty("Incremento")) <= Long.parseLong(prop.getProperty("TempoMaximo"))) 
	 *@ ensures tempoEstadia == (\old(tempoEstadia) + Long.parseLong(prop.getProperty("Incremento"))) ==> \result
	 *@ ensures \result => !(tempoEstadia + Long.parseLong(prop.getProperty("Incremento")) <= Long.parseLong(prop.getProperty("TempoMaximo"))) 
	 */
	public static boolean botaoMais(){	
		if ((tempoEstadia+Long.parseLong(prop.getProperty("Incremento")) > Long.parseLong(prop.getProperty("TempoMaximo"))))
				return false;
		tempoEstadia+= Long.parseLong(prop.getProperty("Incremento"));
		return true;
	}
	
	/*@ ensures \result == false ==> (tempoEstadia - Long.parseLong(prop.getProperty("Incremento")) <= Long.parseLong(prop.getProperty("TempoMinimo"))) 
	 *@ ensures tempoEstadia == (\old(tempoEstadia) - Long.parseLong(prop.getProperty("Incremento"))) ==> \result
	 *ensures \result ==> !(tempoEstadia - Long.parseLong(prop.getProperty("Incremento")) <= Long.parseLong(prop.getProperty("TempoMinimo"))) 
	 */
	public static boolean botaoMenos(){
		if ( (tempoEstadia - Long.parseLong(prop.getProperty("Incremento"))) <= Long.parseLong(prop.getProperty("TempoMinimo"))) 
				return false;
		tempoEstadia -= Long.parseLong(prop.getProperty("Incremento"));
		return true;
	}
	
	// garante que o valor esta entre 0.75 e 3.00
	/*@ ensures \result.compareTo(BigDecimal.valueOf(0.74)) == 1
	 *@ ensures \result.compareTo(BigDecimal.valueOf(3.01)) == (-1) 
	 */
	public static BigDecimal calculaEstadia(){
		BigDecimal tarifa = BigDecimal.ZERO;
		long tempo = tempoEstadia;
		tempo -=  Long.parseLong(prop.getProperty("TempoMinimo"));
		tarifa = tarifa.add(string2bigDec(prop.getProperty("TarifaInicial")));
		while(true)
		{
			if(tempo <= 0)break;
			tempo -=  Long.parseLong(prop.getProperty("Incremento"));
			tarifa = tarifa.add(string2bigDec(prop.getProperty("TarifaIncremento")));
		}
		return tarifa;
	}
	
	// Precisa verificar quando � verdadeiro ?
	
	/*@ requires pagamento != null 
	 *@ ensures !(LocalDate.now().compareTo(dataUltimoPagamento) == 0) ==> serial == 1 
	 *@ ensures \result == false ==> (LocalTime.now().compareTo(string2localTimeHrMin(prop.getProperty("InicioHorario")))==-1
	 *@ 											|| LocalTime.now().compareTo(string2localTimeHrMin(prop.getProperty("FimHorario")))==1)
	 *@ ensures \result false ==> !pagamento.fazPagamento(calculaEstadia())
	 *@ ensures horaUltimoPagamento == LocalTime.now() ==> \result
	 *@ ensures serial == \old(serial+1) ==> (LocalDate.now().compareTo(dataUltimoPagamento) == 0)
	 *@ ensures tempoEstadia == Long.parseLong(prop.getProperty("TempoMinimo"))
	 */
	public static boolean botaoVerde(IPagamento pagamento){
		
		if(!(LocalDate.now().compareTo(dataUltimoPagamento) == 0)) resetSerial();
		
		if(LocalTime.now().compareTo(string2localTimeHrMin(prop.getProperty("InicioHorario")))==-1)return false;
		if(LocalTime.now().compareTo(string2localTimeHrMin(prop.getProperty("FimHorario")))==1)return false;
		BigDecimal tarifa = calculaEstadia();
//		System.out.println(tempoEstadia + "-" + tarifa);
		if(!pagamento.fazPagamento(tarifa))
			{
			tempoEstadia = Long.parseLong(prop.getProperty("TempoMinimo"));
			return false;
			}
		criarTicket(tempoEstadia, pagamento, tarifa);
		tempoEstadia = Long.parseLong(prop.getProperty("TempoMinimo"));
		dataUltimoPagamento = LocalDate.now();
		serial++;
		return true;
	}
	
	/* @ ensures \result
	 * @ ensures tempoEstadia == 0
	 */
	public static boolean botaoVermelho(){
		tempoEstadia = Long.parseLong(prop.getProperty("TempoMinimo"));
		return true;
	}

}
