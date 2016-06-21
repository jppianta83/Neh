package Teste;

import Operacional.Moeda;
import Operacional.PagamentoMoeda;

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
	    	// moedasPag = 2,80
	    	pm = new PagamentoMoeda(moedasPag); 
	    }

	    @Test
	    public void testPagamentoEmMoedaValorExato() {
	    	BigDecimal bd = BigDecimal.valueOf(2.00);
	    	assertTrue(pm.fazPagamento(bd));
	    	System.out.println("Sucesso!"+" Valor Moedas Inseridas: 2.80" + " Pagamento: "+ bd + " Troco:" + pm.mostrarTroco());
	    }
	    
	    @Test
	    public void testPagamentoEmMoedaFalta() {
	    	BigDecimal bd = BigDecimal.valueOf(2.90);
	    	assertFalse(pm.fazPagamento(bd));
	    	System.out.println("Valor Insuficiente!" + " Valor Moedas Inseridas: 2.80" + " Pagamento: "+ bd + " Troco:" + pm.mostrarTroco());
	    }
	    
	    @Test
	    public void testPagamentoEmMoedaValorMaior() {
	    	BigDecimal bd = BigDecimal.valueOf(2.50);
	    	assertTrue(pm.fazPagamento(bd));
	    	System.out.println("Sucesso!"+" Valor Moedas Inseridas: 2.80" + " Pagamento: "+ bd + " Troco:" + pm.mostrarTroco());
	    }

}
