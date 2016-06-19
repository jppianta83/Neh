
public class Mostrador {
	private String display;
	
	public boolean mostrarMensagem(String str, int tempo)
	{
		this.display = str;
		//Thread.currentThread().sleep(temp*1000);
		return true;
	}	
	public boolean mostrarMensagem(String str)
	{
		this.display = str;
		return true;
	}	
}
