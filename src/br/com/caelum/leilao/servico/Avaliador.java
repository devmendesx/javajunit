package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Avaliador {

    private double menorLance = Double.POSITIVE_INFINITY;
    private double maiorLance = Double.NEGATIVE_INFINITY;

    private List<Lance> maiores;

    public void avalia(Leilao leilao){
        for(Lance lance: leilao.getLances()){
            if(lance.getValor() > maiorLance ) maiorLance = lance.getValor();
            if(lance.getValor() < menorLance ) menorLance = lance.getValor();
        }

        maiores = new ArrayList<>(leilao.getLances());

        Collections.sort(maiores, (o1, o2) -> {
            if(o1.getValor() < o2.getValor()) return 1;
            if(o1.getValor() > o2.getValor()) return -1;
            return 0;
        });

        maiores = maiores.subList(0,maiores.size() >= 3 ? 3 : maiores.size()) ;

    }

    /**
     * It returns the cheap price offered.
     * @return menorLance
     */
    public double menorLance() {
        return menorLance;
    }

    /**
     * it returns the most expensive price offered.
     * @return
     */
    public double maiorLance() {
        return maiorLance;
    }

    public List<Lance> maiores() {
        return maiores;
    }
}
