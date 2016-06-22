package Teste;

import Operacional.Cartao;
import Operacional.ModuloOperacional;
import Operacional.PagamentoCartao;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class ModuloOperacionalTest {

    private PagamentoCartao pc;
    private Cartao c;
    private PagamentoCartao pc2;
    private Cartao c2;
    private PagamentoCartao pc3;
    private Cartao c3;
    private PagamentoCartao pc4;
    private Cartao c4;
    
	    @Before
	    public void setUp() throws Exception {
	        ModuloOperacional.inicializacao();
	        String strValida = "";
	    	for (int i=0; i<128;i++)
	    		strValida += "1";
	    	BigDecimal bd = BigDecimal.valueOf(20.00);
	    	c = new Cartao (strValida, bd);
	    	pc = new PagamentoCartao(c);
	    	strValida = strValida.replace('1', '2');
	    	BigDecimal bd2 = BigDecimal.valueOf(30.00);
	    	c2 = new Cartao (strValida, bd2);
	    	pc2 = new PagamentoCartao(c2);
	    	strValida = strValida.replace('2', '3');
	    	BigDecimal bd3 = BigDecimal.valueOf(10.00);
	    	c3 = new Cartao (strValida, bd3);
	    	pc3 = new PagamentoCartao(c3); 
	    	strValida = strValida.replace('3', '4');
	    	BigDecimal bd4 = BigDecimal.valueOf(15.00);
	    	c4 = new Cartao (strValida, bd4);
	    	pc4 = new PagamentoCartao(c4);
	    }

	    @Test
	    public void testTempoEstadiaMinimo1() {
	    	ModuloOperacional.botaoMais();
	    	System.out.println("TempoEstadiaMinimo1:" + ModuloOperacional.getTempoEstadia());
	    	assertTrue(ModuloOperacional.getTempoEstadia() == 40);
	    }
	    
	    @Test
	    public void testTempoEstadiaMinimo2() {
	    	ModuloOperacional.botaoMenos();
	    	System.out.println("TempoEstadiaMinimo2:" + ModuloOperacional.getTempoEstadia());
	    	assertTrue(ModuloOperacional.getTempoEstadia() == 30);
	    }
	    
	    //adicionar até 12
	    
	    @Test
	    public void testTempoEstadiaMaximo1() {
	    	for (int i = 0; i<9;i++) 
	    		ModuloOperacional.botaoMais();
	    	System.out.println("TempoEstadiaMaximo1:" + ModuloOperacional.getTempoEstadia());
	    	assertTrue(ModuloOperacional.getTempoEstadia() == 120);
	    }
	    
	    //adicionar > 120
	    @Test
	    public void testTempoEstadiaMaximo2() {
	    	for (int i = 0; i<10;i++) 
	    		ModuloOperacional.botaoMais();
	    	System.out.println("TempoEstadiaMaximo2:" + ModuloOperacional.getTempoEstadia());
	    	assertTrue(ModuloOperacional.getTempoEstadia() == 120);
	    }
	    
	    @Test
	    public void testBotaoVerde() throws InterruptedException
	    {
	    	
	    	for(int i = 0; i<5 ; i++)
	    		ModuloOperacional.botaoMais();
	    	System.out.println(ModuloOperacional.getTempoEstadia());
	    	ModuloOperacional.botaoVerde(pc);
	    	System.out.println(ModuloOperacional.getTempoEstadia());
//	    	System.out.println("Muda hora");
//	    	TimeUnit.SECONDS.sleep(10);
	    	for(int i = 0; i<2 ; i++)
	    		ModuloOperacional.botaoMais();
	    	ModuloOperacional.botaoVerde(pc2);
//	    	System.out.println("Muda dia");
//	    	TimeUnit.SECONDS.sleep(10);
	    	for(int i = 0; i<3 ; i++)
	    		ModuloOperacional.botaoMais();
	    	ModuloOperacional.botaoVerde(pc3);
//	    	System.out.println("Muda hora");
//	    	TimeUnit.SECONDS.sleep(10);
	    	assertTrue(ModuloOperacional.botaoVerde(pc4));
	    }
	    

}
