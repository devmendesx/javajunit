package br.com.caelum.leilao.teste;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeilaoTeste {
    @Test
    public void deveReceberApenasUmLance(){
        Leilao leilao = new Leilao("Macbook Pro 15");
        assertEquals(0,leilao.getLances().size());
        leilao.propoe(new Lance(new Usuario("Carlos"),2000));
        assertEquals(1,leilao.getLances().size());
        assertEquals(2000.0,leilao.getLances().get(0).getValor(), 0.00001);
    }
    @Test
    public void deveReceberVariosLances(){
        Leilao leilao = new Leilao("Macbook Pro 15");
        leilao.propoe(new Lance(new Usuario("Steve Jobs"),2005));
        leilao.propoe(new Lance(new Usuario("Steve Hawking"),2010));
        leilao.propoe(new Lance(new Usuario("Bill Gates"),2060));

        assertEquals(3,leilao.getLances().size());
        assertEquals(2005,leilao.getLances().get(0).getValor(),0.00001);
        assertEquals(2010,leilao.getLances().get(1).getValor(),0.00001);
        assertEquals(2060,leilao.getLances().get(2).getValor(),0.00001);
    }
    @Test
    public void naoDeveReceberDoisLancesEmSequencia(){
        Leilao leilao = new Leilao("Macbook Pro 19");
        Usuario felipe = new Usuario("Felipe");
        Usuario carlos = new Usuario("Felipe");

        leilao.propoe(new Lance(felipe,2000));
        leilao.propoe(new Lance(felipe,2060));

        assertEquals(1,leilao.getLances().size());
        assertEquals(2000,leilao.getLances().get(0).getValor(),0.00001);
    }
    @Test
    public void naoDeveReceberMaisQueCincoLancesPorUsuario(){
        Leilao leilao = new Leilao("Macbook Pro 19");
        Usuario felipe = new Usuario("Felipe");
        Usuario carlos = new Usuario("Carlos");

        leilao.propoe(new Lance(carlos, 1500));
        leilao.propoe(new Lance(felipe, 2550));
        leilao.propoe(new Lance(carlos, 1505));
        leilao.propoe(new Lance(felipe, 2504));
        leilao.propoe(new Lance(carlos, 1600));
        leilao.propoe(new Lance(felipe, 9000));
        leilao.propoe(new Lance(carlos, 2501));
        leilao.propoe(new Lance(felipe, 3605));
        leilao.propoe(new Lance(carlos, 7845));
        leilao.propoe(new Lance(felipe, 1511));

        leilao.propoe(new Lance(felipe, 3600));
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(9000,leiloeiro.maiorLance(), 0.00001);
        assertEquals(1500,leiloeiro.menorLance(), 0.00001);
        assertEquals(10, leilao.getLances().size());
    }
}
