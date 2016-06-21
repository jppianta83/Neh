package Operacional;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class FacadeTest {

	private Facade fac = null;
	
	@Before
	public void setUp() throws Exception {
		if(fac == null)
		{
			fac = new Facade();
			fac.carregarArquivo("1.se");
		}
	}

	@Test
	public void testLogParquimetrosDia() {
		List<String> temp = fac.logParquimetrosDia("22-06-2016");
		for(String t: temp)
		{
			System.out.println("Tickets: "+ t);
		}
		System.out.println("\n");
		assertEquals(2, temp.size());
	}

	@Test
	public void testLogParquimetrosMes() {
		List<String> temp = fac.logParquimetrosMes("06");
		for(String t: temp)
		{
			System.out.println("Tickets: "+ t);
		}
		System.out.println("\n");
		assertEquals(4, temp.size());
	}

	@Test
	public void testRelatorioParquimetroMes() {
		List<String> temp = fac.relatorioParquimetroMes("00001");
		for(String t: temp)
		{
			System.out.println("Tickets: "+ t);
		}
		System.out.println("\n");
		assertEquals(1, temp.size());
	}

	@Test
	public void testRelatorioParquimetroAno() {
		List<String> temp = fac.relatorioParquimetroAno("00001");
		for(String t: temp)
		{
			System.out.println("Tickets: "+ t);
		}
		System.out.println("\n");
		assertEquals(1, temp.size());
	}

}
