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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Linha extends ElementoGrafico {
    
    final private int SUPERIOR_ESQUERDA = 1;
    final private int SUPERIOR_DIREITA = 2;
    
    public Linha() {
        super();
    }
    
    public Linha(Color cor) {
        super(cor);
    }
    
    protected void configurarElemento() {
        setOpaque(false);
    }
    
    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.setColor(this.getCor());
        if(getDirecaoReta()==SUPERIOR_ESQUERDA) {
            g.drawLine(1,1,getWidth()-1, getHeight()-1);
        } else {
            g.drawLine(1, getHeight()-1, getWidth()-1, 1);
        }
    }
    
    public void desenharSelecao(MolduraAreaDados moldura, Graphics g, Point origemDoNovoElemento, Point finalDoNovoElemento) {
        setDirecaoReta(getOrientacaoDaReta(origemDoNovoElemento, finalDoNovoElemento));
        if(finalDoNovoElemento!=null) {
            g.setColor(getCor());
            Rectangle2D rect = ElementoGrafico.definirRetanguloAPartirDeDoisVerticesQuaisquer(origemDoNovoElemento, finalDoNovoElemento);
            if(getDirecaoReta()==SUPERIOR_ESQUERDA) {
                g.drawLine(arred(rect.getX()), arred(rect.getY()), arred(rect.getX() + rect.getWidth()), arred(rect.getY() + rect.getHeight()));
            } else {
                g.drawLine(arred(rect.getX()), arred(rect.getY() + rect.getHeight()), arred(rect.getX() + rect.getWidth()), arred(rect.getY()));
            }
        }
    }
    
    protected boolean eSobreALinha(Point2D p) {
        if(getWidth()<=MARGEM_CLIQUE || getHeight()<=MARGEM_CLIQUE) {
            return true;
        }
        double tan = (double)getHeight()/(double)getWidth();
        if(getDirecaoReta()==SUPERIOR_ESQUERDA) {
            double yReta = p.getX()*tan;
            if(Math.abs(yReta - p.getY()) <= MARGEM_CLIQUE) {
                return true;
            }
        } else {
            tan = (double)getHeight()/(double)getWidth();
            double yReta = p.getX()*tan;
            if(Math.abs(yReta - (getHeight() - p.getY())) <= MARGEM_CLIQUE) {
                return true;
            }
        }
        return false;
    }
    
    public void duplicarElemento() {
        Linha novoElemento = new Linha(this.getCor());
        novoElemento.setNomeEixo(this.getNomeEixo());
        Rectangle2D rect = (Rectangle2D) this.getPosicaoReal().clone();
        rect.setRect(rect.getX() + rect.getWidth()*.5,
                rect.getY(),
                rect.getWidth(), 
                rect.getHeight());

        novoElemento.setPosicaoReal(rect);
        novoElemento.setMoldura(this.getMoldura());
        novoElemento.setDirecaoReta(this.getDirecaoReta());
        this.getMoldura().getAnaliseAcao().addElemento(novoElemento);
        this.getMoldura().getPanelMolduras().repaint();
    }
    
    private int getOrientacaoDaReta(Point origemDoNovoElemento, Point finalDoNovoElemento) {
        try{
            if(origemDoNovoElemento.getX()<=finalDoNovoElemento.getX()) {
                if(origemDoNovoElemento.getY()<=finalDoNovoElemento.getY()) {
                    return SUPERIOR_ESQUERDA;
                } else {
                    return SUPERIOR_DIREITA;
                }
            } else {
                if(origemDoNovoElemento.getY()<=finalDoNovoElemento.getY()) {
                    return SUPERIOR_DIREITA;
                } else {
                    return SUPERIOR_ESQUERDA;
                }
            }
        } catch (Exception e) { return SUPERIOR_ESQUERDA; }
    }

    public boolean finalizarElemento() {
        return true;
    }
    
}
