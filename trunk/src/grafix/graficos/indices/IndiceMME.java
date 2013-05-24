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
import java.awt.Color;

public class IndiceMME extends Indice{
    
    public IndiceMME() {
        this(null, 2, false, 20, 0, 0);
    }
    
    public IndiceMME(Color cor, int espessura, boolean f, int p1, int p2, int p3) {
        super(ConstrutorDeIndices.INDICE_MME, "MME", cor, espessura, f,p1,p2,p3);
        criarParam1("Per�odo", p1);
    }
    
    protected void configurarIndice() {
        this.setNomeIndice("M�dia M�vel Exponencial");
    }
    
    protected void calcularValoresIndice(Acao acao) {
        double percent = 2 / ((double)getParam1() + 1);
        double[] dados = calcularMME(acao, percent);
        setValores(new ValoresIndice(this, acao, dados));
    }
    
    static public double[] calcularMME(final Acao acao, final double percent) {
        int N = acao.getNumeroRegistros();
        double[] mme = new double[N];
        // Primeiro MME
        mme[0] = acao.getRegistro(0).getClose();
        // Pr�ximos
        for (int i = 1; i < N; i++) {
            if(acao.getRegistro(i).getClose() == 0) {
                mme[i] = ValoresIndice.SEM_VALOR;
            } else {
                mme[i] = ( (acao.getRegistro(i).getClose() * percent) +
                        (mme[i-1]*(1-percent)) );
            }
        }
        return mme;
    }
}
