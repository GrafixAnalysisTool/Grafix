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



package grafix.graficos;

import grafix.graficos.indices.Indice;
import grafix.principal.*;
import grafix.telas.JanelaGraficos;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.data.xy.XYDataset;

public class IndiceToolTipGenerator implements XYToolTipGenerator {
    
    private Indice indice;
    
    public IndiceToolTipGenerator(Indice indice) {
        this.indice = indice;
    }
    
    public String generateToolTip(XYDataset xYDataset, int serie, int item) {
        return indice.getTagIndice() + " :  " + (float)xYDataset.getYValue(serie, item);
    }
    
}
