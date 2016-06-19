import java.io.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Properties;

public class ModuloOperacional {
	private static Properties prop;
	private static Mostrador most;
	private static int serial;
	private static long tempoEstadia;
	private static LocalTime horaUltimoPagamento = LocalTime.of(0,0);
	
	


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
	private static boolean alterarConfiguracao(String key, String val)
	{
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
	private static BigDecimal string2bigDec(String str){
		return new BigDecimal(str.replaceAll(",", "."));
	}
	
	private static long string2duration(String str){
		long l = Long.parseLong(str);
		return l*60*1000;
	}
	
	private static LocalTime string2localTimeHrMin(String data){
		return LocalTime.of(Integer.parseInt(data.split(":")[0]), Integer.parseInt(data.split(":")[1]));	
	}
	// Apenas para String com o formato 00:00:00
	private static LocalTime string2localTimeHrMinSeg(String data){
		return LocalTime.of(Integer.parseInt(data.split(":")[0]), Integer.parseInt(data.split(":")[1]), Integer.parseInt(data.split(":")[2]));
	}
	
	private static boolean resetSerial()
	{
		serial = 0;
		return true;
	}
	
	
	public static void inicializacao(){
		carregarConfiguracao();
		most = new Mostrador();
		serial = 0;
		tempoEstadia = Long.parseLong(prop.getProperty("TempoMinimo"));
	}
	
	private static boolean lerCartão(Cartao cartao)
	{
		return most.mostrarMensagem(cartao.toString(), 30);
		
	}
	private static boolean criarTicket(long validade)
	{
		new Ticket(prop.getProperty("id"), prop.getProperty("Endereco"), Integer.toString(serial, 5), LocalTime.now(), (LocalTime.now().plusMinutes(validade)));
		return true;
	}
	//Cuidado para nao explodir o tempo
	private static boolean botaoMais(){
		
		if ((tempoEstadia+Long.parseLong(prop.getProperty("Incremento")) > Long.parseLong(prop.getProperty("TempoMaximo"))))
				return false;
		tempoEstadia+= Long.parseLong(prop.getProperty("Incremento"));
		return true;
	}
	
	private static boolean botaoMenos(){
		if ( (tempoEstadia - Long.parseLong(prop.getProperty("Incremento")))  <= Long.parseLong(prop.getProperty("TempoMinimo"))) 
				return false;
		tempoEstadia -= Long.parseLong(prop.getProperty("Incremento"));
		return true;
	}
	
	private static BigDecimal calculaEstadia(){
		BigDecimal tarifa = new BigDecimal(0);
		long tempo = tempoEstadia;
		tempo -=  string2duration(prop.getProperty("TempoMinimo"));
		tarifa.add(string2bigDec(prop.getProperty("TarifaInicial")));
		
	}
	
	
	private static boolean botaoVerde(IPagamento pagamento){
		if(LocalTime.now().compareTo(horaUltimoPagamento) == (-1)) resetSerial();
		//if ..
		
		pagamento.fazPagamento(val);
		
		return true;
	}
	
	
	

	
	
}