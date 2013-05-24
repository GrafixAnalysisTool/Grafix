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

import grafix.principal.Acao;
import grafix.telas.JanelaGraficos;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.text.DateFormatter;
import org.jfree.chart.axis.*;
import org.jfree.ui.*;

public class EixoHorizontalComLabels extends EixoHorizontal {

    private Acao acao;
    private int QUANTIDADE_DATAS = 15;
    private int diasExibidosAnterior = 0;
    private List ticksAnteriores = null;
    private final long ref = 86400000;

    public EixoHorizontalComLabels(JanelaGraficos janela) {
        super(janela);
        this.acao = janela.getAcao();
        setTickLabelFont(new Font(Font.DIALOG, Font.PLAIN, 9));
        setAutoTickUnitSelection(true);
    }

    @Override
    public List refreshTicks(Graphics2D g2, AxisState state,
            Rectangle2D dataArea, RectangleEdge edge) {
        try {
            long l1 = this.getMaximumDate().getTime();
            long l2 = this.getMinimumDate().getTime();
            int diasExibidos = (int) ((l1 - l2) / ref);
            if (diasExibidos == diasExibidosAnterior && ticksAnteriores != null) {
                return ticksAnteriores; // otimizacao
            } else {
                List result = new ArrayList();
                int j = 0;
                for (int i = acao.getNumeroRegistros() - 1; i >= 0; i--) {
                    int n = (int) (diasExibidos / QUANTIDADE_DATAS);
                    if (j++ % n == 0) {
                        result.add(new DateTick(acao.getRegistro(i).getDataCorrida().getStart(),
                                new DateFormatter(new SimpleDateFormat("dd/MM/yy")).valueToString(acao.getRegistro(i).getData().getStart()),
                                TextAnchor.TOP_CENTER,
                                TextAnchor.TOP_RIGHT, 0));
                    } else {
                        result.add(new DateTick(acao.getRegistro(i).getDataCorrida().getStart(),
                                "",
                                TextAnchor.TOP_CENTER,
                                TextAnchor.TOP_RIGHT, 0));
                    }
                }
                ticksAnteriores = result;
                diasExibidosAnterior = diasExibidos;
                return result;
            }
        } catch (Exception e) {
            return new ArrayList();
        }
    }
}
