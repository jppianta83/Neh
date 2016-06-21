package Teste;

import Operacional.Cartao;
import Operacional.PagamentoCartao;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class PagamentoCartaoTest {

	    private PagamentoCartao pc;
	    private Cartao c;

	    @Before
	    public void setUp() throws Exception {
	    	String strValida = "";
	    	for (int i=0; i<128;i++)
	    		strValida += "1";
	    	BigDecimal bd = new BigDecimal(3.00);
	    	c = new Cartao (strValida, bd);
	    	
	    	pc = new PagamentoCartao(c); 
	    }

	    @Test
	    public void testPagamentoEmCartaoValorExato() {
	    	BigDecimal bd = new BigDecimal(3.00);
	    	assertTrue(pc.fazPagamento(bd));
	    	System.out.println("Teste1: " + c.toString());
	    }
	    
	    @Test
	    public void testPagamentoEmCartaoFalta() {
	    	BigDecimal bd = new BigDecimal(3.01);
	    	assertFalse(pc.fazPagamento(bd));
	    	System.out.println("Teste2: " + c.toString());
	    }
	    
	    @Test
	    public void testPagamentoEmCartaoValorMaior() {
	    	BigDecimal bd = BigDecimal.valueOf(2.99);
	    	assertTrue(pc.fazPagamento(bd));
	    	System.out.println("Teste3: " + c.toString());
	    }

}
