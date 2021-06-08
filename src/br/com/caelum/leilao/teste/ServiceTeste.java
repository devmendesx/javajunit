package br.com.caelum.leilao.teste;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ServiceTeste {

    @Test
    public void deveEntenderVariosLances() {
        Usuario joao = new Usuario("Joao");
        Usuario maria = new Usuario("Maria");
        Usuario pedro = new Usuario("Pedro");

        Leilao leilao = new Leilao("PS5");
        leilao.propoe(new Lance(joao,3500));
        leilao.propoe(new Lance(maria,2500));
        leilao.propoe(new Lance(pedro,1500));

        Avaliador leiloeiro = new Avaliador();

        leiloeiro.avalia(leilao);
        assertEquals(3500,leiloeiro.maiorLance(),0.00001);
        assertEquals(1500,leiloeiro.menorLance(),0.00001);
    }
    @Test
    public void deveEntenderLeilaoComApenasUmLance(){
        Usuario joao = new Usuario("Joao");

        Leilao leilao = new Leilao("PS4");
        leilao.propoe(new Lance(joao,1500));

        Avaliador leiloeiro = new Avaliador();

        leiloeiro.avalia(leilao);

        assertEquals(1500,leiloeiro.menorLance(), 0.00001);
        assertEquals(1500,leiloeiro.maiorLance(),0.00001);
    }

    @Test
    public void devePegarOsTresMaioresLances(){
        Usuario joao = new Usuario("Joao");
        Usuario maria = new Usuario("Maria");
        Usuario pedro = new Usuario("Pedro");

        Leilao leilao = new Leilao("PS5");
        leilao.propoe(new Lance(joao,1500));
        leilao.propoe(new Lance(maria,2500));
        leilao.propoe(new Lance(pedro,3200));
        leilao.propoe(new Lance(pedro,2200));

        Avaliador leiloeiro = new Avaliador();

        leiloeiro.avalia(leilao);
        List<Lance> maiores = leiloeiro.maiores();

        assertEquals(3,maiores.size(),0.00001);
        assertEquals(3200,maiores.get(0).getValor(),0.00001);
        assertEquals(2500,maiores.get(1).getValor(),0.00001);
        assertEquals(2200,maiores.get(2).getValor(),0.00001);
    }
    @Test
    public void variosLancesVariados(){
        Usuario joao = new Usuario("Joao");
        Usuario maria = new Usuario("Maria");
        Usuario felipe = new Usuario("Felipe");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(maria,450.0));
        leilao.propoe(new Lance(joao,120.0));
        leilao.propoe(new Lance(maria,700.0));
        leilao.propoe(new Lance(joao,630.0));
        leilao.propoe(new Lance(maria,230.0));
        leilao.propoe(new Lance(felipe,172.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(700.0, leiloeiro.maiorLance(), 0.0001);
        assertEquals(120.0, leiloeiro.menorLance(), 0.0001);
    }
}
