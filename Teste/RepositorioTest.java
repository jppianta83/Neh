package Teste;

import Operacional.Repositorio;
import Operacional.Moeda;
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
    	assertTrue(rep.getTotalValor().compareTo(BigDecimal.valueOf(4.25))== 0);
    }

    @Test
    public void testTrocoCentavosMaximo() {
    	for(int i = 0; i < 8; i++)rep.addMoeda(Moeda.VINTECINCO);
    	for(int i = 0; i < 5; i++)rep.addMoeda(Moeda.CINCO);
    	for(int i = 0; i < 2; i++)rep.addMoeda(Moeda.UM);
    	BigDecimal bd = new BigDecimal(3.19);
    	List<Moeda> lista = rep.troco(bd);
    	BigDecimal totalTroco = BigDecimal.ZERO;
    	for ( Moeda m : lista )
    		totalTroco = totalTroco.add(m.valorMoeda());
        assertTrue( totalTroco.compareTo(BigDecimal.valueOf(3.15))== 0 );
    }
    @Test
    public void testTrocoCentavosMinimo() {
    	for(int i = 0; i < 8; i++)rep.addMoeda(Moeda.VINTECINCO);
    	for(int i = 0; i < 5; i++)rep.addMoeda(Moeda.CINCO);
    	for(int i = 0; i < 2; i++)rep.addMoeda(Moeda.UM);
    	BigDecimal bd = new BigDecimal(3.21);
    	List<Moeda> lista = rep.troco(bd);
    	BigDecimal totalTroco = BigDecimal.ZERO;
    	for ( Moeda m : lista )
    		totalTroco = totalTroco.add(m.valorMoeda());
        assertTrue( totalTroco.compareTo(BigDecimal.valueOf(3.20))== 0 );
    }
    
    
}
