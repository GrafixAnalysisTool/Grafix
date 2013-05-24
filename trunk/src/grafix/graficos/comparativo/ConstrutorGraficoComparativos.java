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



package grafix.graficos.comparativo;

import java.awt.geom.GeneralPath;
import java.util.Vector;
import org.jfree.chart.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;


public class ConstrutorGraficoComparativos {
    
    private Vector<IndiceComparativo> indices;
    
    public ConstrutorGraficoComparativos(Vector<IndiceComparativo> indices) {
        this.indices = indices;
    }
    
    public JFreeChart criarJFreeChart() {
        XYDataset dataset = criarDataset();
        JFreeChart chart = criarChart(dataset);
        return chart;
    }

    private static JFreeChart criarChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Comparativo da Evolu��o de Pap�is", "Per�odo", "Evolu��o (%)", dataset, 
                true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer 
            = (XYLineAndShapeRenderer) plot.getRenderer();
        GeneralPath zigzag = new GeneralPath();
        zigzag.moveTo(-6.0f, 0.0f);
        zigzag.lineTo(-3.0f, 6.0f);
        zigzag.lineTo(3.0f, -6.0f);
        zigzag.lineTo(6.0f, 0.0f);
        renderer.setLegendLine(zigzag);
        return chart;
       
    }

    private XYDataset criarDataset() {
        TimeSeriesCollection tc = new TimeSeriesCollection();
        for (IndiceComparativo elem : indices) {
            TimeSeries tSerie = elem.getTimeSeries();
            tc.addSeries(tSerie);
        }
        return tc;
    }
}