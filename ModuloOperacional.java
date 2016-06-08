import java.io.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Properties;

public class ModuloOperacional {
	private static Properties prop;
	private static Mostrador most;
	
	
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
	
	
	
	public static void inicialização(){
		carregarConfiguracao();
		most = new Mostrador();
	}
	
	public lerCartão(Cartao cartao)
	{
		most.mostrarMensagem(cartao.toString(), 30);
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
	public static BigDecimal string2bigDec(String str){
		return new BigDecimal(str.replaceAll(",", "."));
	}
	public static long string2duration(String str){
		long l = Long.parseLong(str);
		return l*60*1000;
	}
	
	
	
	
}
