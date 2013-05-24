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

public class IndiceIFR extends Indice {
    
    public IndiceIFR() {
        this(null, 2, true, 14, 0, 0);
    }
    
    public IndiceIFR(Color cor, int espessura, boolean f, int p1, int p2, int p3) {
        super(ConstrutorDeIndices.INDICE_IFR, "IFR", cor, espessura, f,p1,p2,p3);
        criarFlag("Suavizar c�lculo (****)", f);
        criarParam1("Per�odo", p1);
    }
    
    protected void configurarIndice() {
        this.setNomeIndice("�ndice de For�a Relativa");
    }
    
    //  Implementar SEM SUAVIZA��O!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    protected void calcularValoresIndice(Acao acao) {
        double[] dados = IndiceRMI.calcularRMI(acao, getParam1(), 1);
        setValores(new ValoresIndice(this, acao, dados));
    }
    
}



////////////////////////////////////////////////////
//            javax.swing.text.NumberFormatter decimalFormatter = new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0.0000"));
//            //decimalFormatter.valueToString(getValorO()));
//
//        for (int i = 0; i < N; i++) {
//            try {
//                System.out.println(decimalFormatter.valueToString(valorFechamento[i]) +
//                        " - " + decimalFormatter.valueToString(incrFechamento[i]) +
//                        " - " + decimalFormatter.valueToString(smoothIncr[i]) +
//                        " - " + decimalFormatter.valueToString(decrFechamento[i]) +
//                        " - " + decimalFormatter.valueToString(smoothDecr[i]) +
//                        " - " + decimalFormatter.valueToString(dados[i]));
//            } catch (ParseException ex) {
//                ex.printStackTrace();
//            }
//        }


