package teste;

import operacional.Repositorio;
import operacional.Moeda;
import static org.junit.Assert.*;

import java.util.List;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;


public class RepositorioTest {
    private Repositorio rep;

    @Before
    public void setUp() throws Exception {
        rep = new Repositorio();
    }
    
    @Test
    public void testQuatidadeMoeda() {
    	for(int i = 0; i < 8; i++)rep.addMoeda(Moeda.VINTECINCO);
    	for(int i = 0; i < 5; i++)rep.addMoeda(Moeda.CINCO);
    	for(int i = 0; i < 2; i++)rep.addMoeda(Moeda.UM);
        assertEquals(15, rep.getTotalMoeda());
    }

    @Test
    public void testTotalValor() {
    	for(int i = 0; i < 8; i++)rep.addMoeda(Moeda.VINTECINCO);
    	for(int i = 0; i < 5; i++)rep.addMoeda(Moeda.CINCO);
    	for(int i = 0; i < 2; i++)rep.addMoeda(Moeda.UM);
    	System.out.println(rep.getTotalValor());
    	assertTrue(rep.getTotalValor().compareTo(BigDecimal.valueOf(4.25))== 0);
    }

    @Test
    public void testTroco() {
    	for(int i = 0; i < 8; i++)rep.addMoeda(Moeda.VINTECINCO);
    	for(int i = 0; i < 5; i++)rep.addMoeda(Moeda.CINCO);
    	for(int i = 0; i < 2; i++)rep.addMoeda(Moeda.UM);
    	BigDecimal bd = new BigDecimal(3.22);
    	List<Moeda> lista = rep.troco(bd);
    	System.out.println(lista);
    	BigDecimal totalTroco = BigDecimal.ZERO;
    	for ( Moeda m : lista )
    		totalTroco = totalTroco.add(m.valorMoeda());
    	System.out.println(totalTroco);
        assertTrue( totalTroco.compareTo(BigDecimal.valueOf(3.20))== 0 );
    }
    
    
}
