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

import grafix.graficos.*;
import grafix.graficos.indices.*;
import grafix.principal.*;
import grafix.telas.*;
import java.awt.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.*;
import org.jfree.data.time.ohlc.*;
import org.jfree.data.xy.*;

public class EixoCandles extends Eixo {

    public EixoCandles() {
        this.setTamanho(3);
        this.setNomeEixo("candles");
        this.setGradeHorizontal(false);
    }

    protected void completarPlot(final XYPlot plot, final JanelaGraficos janela) {
        OHLCDataset dataCandles = criarOHLCDataset(janela);
        CandlestickRenderer candRenderer = new CandlestickRenderer();
        candRenderer.setUpPaint(Controle.getConfiguracoesUsuario().getCorCandlesAlta());
        candRenderer.setDownPaint(Controle.getConfiguracoesUsuario().getCorCandlesBaixa());
        candRenderer.setSeriesPaint(0, Color.black);
        candRenderer.setStroke(new BasicStroke(.75f));
        candRenderer.setToolTipGenerator(new CandlesToolTipGenerator(janela));
        plot.setDataset(indices.size(), dataCandles);
        plot.setRenderer(indices.size(), candRenderer);
        incluirMarcaIntraday(plot, janela);
    }

    @Override
    protected NumberAxis definirEixoVertical() {
        if (!Controle.getConfiguracoesVolateis().isLog()) {
            return new NumberAxis();
        } else {
            NumberAxis n = new LogarithmicAxis("Eixo logar�tmico");
            return n;
        }
    }

    private OHLCDataset criarOHLCDataset(JanelaGraficos janela) {
        //*debug*/ System.out.println("[CHAMADA] criarOHLCDataset()");
        OHLCSeries serie = new OHLCSeries(janela.getAcao().getCodAcao()); 
        IntervaloExibicao intervalo = janela.getIntervaloExibicao();
        for (int i = intervalo.getInicio(); i < intervalo.getFim(); i++) {
            RegistroDiario reg = janela.getAcao().getRegistro(i);
            serie.add(reg.getData(Controle.getConfiguracoesUsuario().isExibeSomenteDiasUteis()),
                    reg.getOpen(), reg.getHigh(), reg.getLow(), reg.getClose());
        }
        OHLCSeriesCollection dataset = new OHLCSeriesCollection();
        dataset.addSeries(serie);
        return dataset;
    }

//    private void incluirMarcaIntraday(final XYPlot plot, final JanelaGraficos janela) {
//        if (Controle.getConfiguracoesVolateis().isIntraday()) {
//            MarcaGrafica m = new MarcaGrafica("", "INTRADAY",
//                    janela.getAcao().getRegistro(janela.getAcao().getNumeroRegistros() - 1).getData(), Color.BLUE);
//            if (m.isRangeMarker()) {
//                if (getNomeEixo().equals(m.getNomeEixo())) {
//                    plot.addRangeMarker(gerarRangeMarker(m));
//                }
//            } else {
//                plot.addDomainMarker(gerarIntervalMarker(m, janela));
//            }
//        }
//    }
}
