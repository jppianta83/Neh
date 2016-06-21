package teste;

import operacional.Moeda;
import operacional.PagamentoMoeda;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PagamentoMoedaTest {

	    private PagamentoMoeda pm;

	    @Before
	    public void setUp() throws Exception {
	    	List<Moeda> moedasPag = new ArrayList<Moeda>();
	    	for(int i = 0; i < 2; i++)moedasPag.add(Moeda.UM);
	    	for(int i = 0; i < 3; i++)moedasPag.add(Moeda.DEZ);
	    	for(int i = 0; i < 1; i++)moedasPag.add(Moeda.CINQUENTA);
	    	// moedasPag = 2,70
	    	pm = new PagamentoMoeda(moedasPag); 
	    }

	    @Test
	    public void testPagamentoEmMoedaValorExato() {
	    	BigDecimal bd = new BigDecimal(2.70);
	    	assertTrue(pm.fazPagamento(bd));
	    }
	    
	    @Test
	    public void testPagamentoEmMoedaFalta() {
	    	BigDecimal bd = new BigDecimal(2.90);
	    	assertFalse(pm.fazPagamento(bd));
	    }
	    
	    @Test
	    public void testPagamentoEmMoedaValorMaior() {
	    	BigDecimal bd = new BigDecimal(2.50);
	    	assertTrue(pm.fazPagamento(bd));
	    }

}
