package teste;

import operacional.Cartao;
import operacional.ModuloOperacional;
import operacional.PagamentoCartao;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class ModuloOperacionalTest {

    private PagamentoCartao pc;
    private Cartao c;
    
	    @Before
	    public void setUp() throws Exception {
	        ModuloOperacional.inicializacao();
	        String strValida = "";
	    	for (int i=0; i<128;i++)
	    		strValida += "1";
	    	BigDecimal bd = new BigDecimal(3.00);
	    	c = new Cartao (strValida, bd);
	    	
	    	pc = new PagamentoCartao(c); 
	    }

	    @Test
	    public void testTempoEstadiaMinimo1() {
	    	ModuloOperacional.botaoMais();
	    	System.out.println("TempoEstadiaMinimo1:" + ModuloOperacional.tempoEstadia);
	    	assertTrue(ModuloOperacional.tempoEstadia == 40);
	    }
	    
	    @Test
	    public void testTempoEstadiaMinimo2() {
	    	ModuloOperacional.botaoMenos();
	    	System.out.println("TempoEstadiaMinimo2:" + ModuloOperacional.tempoEstadia);
	    	assertTrue(ModuloOperacional.tempoEstadia == 30);
	    }
	    
	    //adicionar até 120
	    @Test
	    public void testTempoEstadiaMaximo1() {
	    	for (int i = 0; i<9;i++) 
	    		ModuloOperacional.botaoMais();
	    	System.out.println("TempoEstadiaMaximo1:" + ModuloOperacional.tempoEstadia);
	    	assertTrue(ModuloOperacional.tempoEstadia == 120);
	    }
	    
	    //adicionar > 120
	    @Test
	    public void testTempoEstadiaMaximo2() {
	    	for (int i = 0; i<10;i++) 
	    		ModuloOperacional.botaoMais();
	    	System.out.println("TempoEstadiaMaximo2:" + ModuloOperacional.tempoEstadia);
	    	assertTrue(ModuloOperacional.tempoEstadia == 120);
	    }
	    
	    @Test
	    public void testBotaoVerde()
	    {
	    	assertTrue(ModuloOperacional.botaoVerde(pc));
	    }
	    

}
