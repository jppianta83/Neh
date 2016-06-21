package operacional;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class FacadeTest {

	private Facade fac;
	
	@Before
	public void setUp() throws Exception {
		fac = new Facade();
		fac.carregarArquivo("1.se");
	}

	@Test
	public void testLogParquimetrosDia() {
		List<String> temp = fac.logParquimetrosDia("21-06-2016");
		assertEquals(2, temp.size());
	}

	@Test
	public void testLogParquimetrosMes() {
		List<String> temp = fac.logParquimetrosMes("06");
		assertEquals(8, temp.size());
	}

	@Test
	public void testRelatorioParquimetroMes() {
		List<String> temp = fac.relatorioParquimetroMes("00001");
		assertEquals(1, temp.size());
	}

	@Test
	public void testRelatorioParquimetroAno() {
		List<String> temp = fac.relatorioParquimetroAno("00001");
		assertEquals(1, temp.size());
	}

}
