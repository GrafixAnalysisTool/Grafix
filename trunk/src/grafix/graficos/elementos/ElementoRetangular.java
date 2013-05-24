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



package grafix.graficos.elementos;

import grafix.telas.MolduraAreaDados;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class ElementoRetangular extends ElementoGrafico {
    
    public ElementoRetangular() {
        super();
    }
    
    public ElementoRetangular(Color cor) {
        super(cor);
    }
    
    public void desenharSelecao(MolduraAreaDados moldura, Graphics g, Point origemDoNovoElemento, Point finalDoNovoElemento) {
        if(finalDoNovoElemento!=null) {
            g.setColor(getCor());
            Rectangle2D rect = ElementoGrafico.definirRetanguloAPartirDeDoisVerticesQuaisquer(origemDoNovoElemento, finalDoNovoElemento);
            g.drawRect(arred(rect.getX()), arred(rect.getY()), arred(rect.getWidth()), arred(rect.getHeight()));
        }
    }

}
