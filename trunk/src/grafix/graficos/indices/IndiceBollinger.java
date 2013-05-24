/*
  Copyright (C) 2001-2012, Joao Medeiros, Paulo Vilela (grafix2.com)
  
  Este arquivo � parte do programa Grafix2.com
  
  Grafix2.com � um software livre; voc� pode redistribui-lo e/ou 
  modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como 
  publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da 
  Licen�a.

  Este programa � distribuido na esperan�a que possa ser �til, 
  mas SEM NENHUMA GARANTIA; sem uma garantia implicita de ADEQUA��O a qualquer
  MERCADO ou APLICA��O EM PARTICULAR. Veja a
  Licen�a P�blica Geral GNU para maiores detalhes.

  Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
  junto com este programa, se n�o, veja uma c�pia em
  <http://www.gnu.org/licenses/>
  
 */



package grafix.graficos.indices;

import grafix.principal.*;
import grafix.telas.JanelaGraficos;
import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDifferenceRenderer;

public class IndiceBollinger extends Indice{
    
    public IndiceBollinger() {
        this(null, 2, false, 5, 2, 0);
    }
    
    public IndiceBollinger(Color cor, int espessura, boolean f, int p1, int p2, int p3) {
        super(ConstrutorDeIndices.INDICE_BOLLINGER, "Bollinger", cor, espessura, f,p1,p2,p3);
        criarParam1("Per�odo", p1);
        criarParam2("Desvios", p2); // "Desvios ????"
    }
    
    protected void configurarIndice() {
        this.setNomeIndice("Bandas de Bollinger");
    }
    
    @Override
    public void plotar(final XYPlot plot, final JanelaGraficos janela, final int contador) {
        float[] color = getCor().getComponents(null);
        XYDifferenceRenderer r = new XYDifferenceRenderer(new Color(color[0],color[1],color[2],.1f), Color.red, false);
        r.setStroke(new BasicStroke(.75f));
        r.setPaint(getCor());
        plot.setRenderer(contador, r);
        plot.setDataset(contador, getDataSet(janela));
    }
    
    protected void calcularValoresIndice(Acao acao) {
        int N = acao.getNumeroRegistros();
        int periodo = getParam1();
        int desvios = getParam2();
        double[] mm = IndiceMM.calcularMM(acao, periodo);
        double[] desvPad = calcularDesvioPadrao(acao, mm, periodo);
        double[] bandaSuperior = new double[N];
        double[] bandaInferior = new double[N];
        if(periodo >= N) {
            setValores(new ValoresIndice(this, acao, dadosZerados(N), dadosZerados(N)));
            return;
        }
        for (int i = 0; i < periodo; i++) {
            bandaSuperior[i] = ValoresIndice.SEM_VALOR;
            bandaInferior[i] = ValoresIndice.SEM_VALOR;
        }
        for (int i = periodo; i < N; i++) {
            if(acao.getRegistro(i).getClose() == 0) {
                bandaSuperior[i] = ValoresIndice.SEM_VALOR;
                bandaInferior[i] = ValoresIndice.SEM_VALOR;
            } else {
                bandaSuperior[i] = mm[i] + (desvPad[i] * desvios);
                bandaInferior[i] = mm[i] - (desvPad[i] * desvios);
            }
        }
        setValores(new ValoresIndice(this, acao, bandaSuperior, bandaInferior));
    }
    
    static public double[] calcularDesvioPadrao(final Acao acao, final double[] mm, final int periodo) {
        double[] desv = new double[mm.length];
        for (int i = periodo; i < mm.length; i++) {
            double soma = 0;
            for (int j = 0; j < periodo; j++) {
                soma += Math.pow(mm[i] - acao.getRegistro(i-j).getClose(), 2);
            }
            desv[i] = Math.sqrt(soma/periodo);
        }
        return desv;
    }
}
