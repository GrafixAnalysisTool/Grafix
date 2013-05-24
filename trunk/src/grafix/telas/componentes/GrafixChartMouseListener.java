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


package grafix.telas.componentes;

import grafix.principal.*;
import grafix.telas.*;
import grafix.telas.secundarias.FormConfiguracaoJanela;
import grafix.telas.secundarias.FormIndices;
import java.awt.event.MouseEvent;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.entity.*;
import org.jfree.chart.plot.*;

public class GrafixChartMouseListener implements ChartMouseListener {

    private PanelGraficos panel;
    private JPopupMenu popup;
    private int plotClicado = -1;

    public GrafixChartMouseListener(PanelGraficos panel) {
        this.panel = panel;
        criarPopupMenu();
    }

    public void chartMouseClicked(ChartMouseEvent evt) {
        if (evt.getTrigger().getButton() == MouseEvent.BUTTON3) {
            int clickY = evt.getTrigger().getY();
            for (int i = 0; i < panel.getNumPlots(); i++) {
                int finalPlot = (int) panel.getAreaData(i).getY() + (int) panel.getAreaData(i).getHeight();
                if (clickY <= finalPlot) {
                    plotClicado = i;
                    popup.show(evt.getTrigger().getComponent(),
                            evt.getTrigger().getX(), evt.getTrigger().getY());
                    System.out.println("plot " + i);
                    return;
                }
            }
        }
    }

    private void abrirFormConfiguracoes(int i) {
        new FormConfiguracaoJanela(Controle.getTela(), i).setVisible(true);
    }

    private void abrirFormIndices(int i) {
        new FormIndices(Controle.getTela(), i).setVisible(true);
    }

    public void chartMouseMoved(ChartMouseEvent arg0) {
    }

    private void criarPopupMenu() {
        popup = new JPopupMenu();
        JMenuItem menuItem;

        menuItem = new JMenuItem("Configurar janela...");
        menuItem.setIcon(new javax.swing.ImageIcon(ConfiguracoesGrafix.PASTA_ICONES + "config.gif"));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirFormConfiguracoes(-1);
            }
        });
        popup.add(menuItem);

        menuItem = new JMenuItem("Configurar gr�fico...");
        menuItem.setIcon(new javax.swing.ImageIcon(ConfiguracoesGrafix.PASTA_ICONES + "configurar3.gif"));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirFormConfiguracoes(plotClicado);
            }
        });
        popup.add(menuItem);

        menuItem = new JMenuItem("�ndices...");
        menuItem.setIcon(new javax.swing.ImageIcon(ConfiguracoesGrafix.PASTA_ICONES + "indices.gif"));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirFormIndices(plotClicado);
            }
        });
        popup.add(menuItem);
    }
}
