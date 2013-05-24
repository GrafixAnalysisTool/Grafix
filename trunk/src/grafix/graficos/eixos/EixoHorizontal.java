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



package grafix.graficos.eixos;

import grafix.telas.JanelaGraficos;
import java.text.SimpleDateFormat;
import org.jfree.chart.axis.DateAxis;

public class EixoHorizontal extends DateAxis{

    protected JanelaGraficos janela;
    
    public EixoHorizontal(JanelaGraficos janela) {
        this.janela = janela;
        this.setLowerMargin(0.01);
        this.setUpperMargin(0.01);
        this.setDateFormatOverride(new SimpleDateFormat("dd/MM/yy"));
    }
}
