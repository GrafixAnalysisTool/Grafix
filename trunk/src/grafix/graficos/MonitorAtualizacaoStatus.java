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

import grafix.principal.*;
import grafix.telas.*;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Date;
import org.jfree.data.time.Day;

public class MonitorAtualizacaoStatus implements Runnable {

    private MouseEvent eventoMovimento;

    public MonitorAtualizacaoStatus(MouseEvent eventoMovimento) {
        this.eventoMovimento = eventoMovimento;
    }

    public void run() {
        try {
            PanelGraficos pg = (PanelGraficos) eventoMovimento.getSource();
            JanelaGraficos janela = pg.getJanela();
            PanelMolduras pm = janela.getPanelMolduras();
            int idMoldura = -1;

            // Determinar moldura
            for (int i = 0; i < pg.getNumPlots(); i++) {
                Rectangle2D ad = pg.getAreaData(i);
                if (eventoMovimento.getY() < ad.getY()) {
                    break;          // fora das molduras
                } else if (eventoMovimento.getY() < (ad.getY() + ad.getHeight())) {
                    idMoldura = i;  // moldura localizada
                    break;
                }
            }
            if (idMoldura == -1) {
                return;
            }

            // Determinar Ponto
            Rectangle2D areaDados = pg.getAreaData(idMoldura);
            double left = areaDados.getX();
            double bottom = areaDados.getY();
            double width = areaDados.getWidth();
            //double height = areaDados.getHeight();
            int x = eventoMovimento.getX() - (int) left;
            int y = eventoMovimento.getY() - (int) bottom;

            // Achar Ponto no plot
            MolduraAreaDados moldura = pm.getMolduras().get(idMoldura);
            Point2D pPlot = moldura.converterPontoNaMolduraParaPlot(new Point(x, y));

            // Achar Registro
            RegistroDiario reg = null;
            if (x < width && x >= 0 & y >= 0) { // dentro da area de dados
                Day dia = new Day(new Date((long) pPlot.getX()));
                int nReg = janela.getAcao().localizarRegistro(janela, dia);
                if (nReg != -1) { // registro encontrado
                    reg = janela.getAcao().getRegistro(nReg);
                }
            }

            // Valor Y atual
            double atual = pPlot.getY();

            //Atualizar barra status
            Controle.getTela().atualizarBarraStatus(reg, atual);
        } catch (Exception e) {
        }
    }
}

