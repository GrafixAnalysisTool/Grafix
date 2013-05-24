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

import grafix.graficos.eixos.Eixo;
import grafix.graficos.eixos.EixoCandles;
import grafix.graficos.eixos.EixoExtra;
import grafix.graficos.eixos.EixoVolume;
import grafix.telas.JanelaGraficos;
import java.awt.Rectangle;
import java.util.Vector;

public class ConfiguracoesJanela {
    
    private EixoCandles candles;
    private EixoVolume volume;
    private Vector<EixoExtra> eixosExtras;
    private Rectangle bounds;
    private String codAcao;
    
    public ConfiguracoesJanela() {
        // DEFAULT /////////////////////////////
        candles = new EixoCandles();
        volume = new EixoVolume();
        eixosExtras = new Vector<EixoExtra>();
        getEixosExtras().add(new EixoExtra("Default"));
        setBounds(new Rectangle());
        codAcao = null;
        ////////////////////////////////////////
    }
    
    public ConfiguracoesJanela(JanelaGraficos janela) {
        setCandles(janela.getConfiguracoesJanela().getCandles());
        setVolume(janela.getConfiguracoesJanela().getVolume());
        setEixosExtras(janela.getConfiguracoesJanela().getEixosExtras());
        setBounds(janela.getBounds());
        setCodAcao(janela.getAcao().getCodAcao());
    }
    
    public EixoCandles getCandles() {
        return candles;
    }
    
    public void setCandles(EixoCandles candles) {
        this.candles = candles;
    }
    
    public EixoVolume getVolume() {
        return volume;
    }
    
    public void setVolume(EixoVolume volume) {
        this.volume = volume;
    }
    
    public Vector<EixoExtra> getEixosExtras() {
        return eixosExtras;
    }
    
    public void setEixosExtras(Vector eixosExtras) {
        this.eixosExtras = eixosExtras;
    }
    
    public String getCodAcao() {
        return codAcao;
    }
    
    public void setCodAcao(String codAcao) {
        this.codAcao = codAcao;
    }
    
    public Rectangle getBounds() {
        return bounds;
    }
    
    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
    
    public Vector<Eixo> getEixos() {
        Vector<Eixo> resultado = new Vector<Eixo>();
        resultado.add(candles);
        resultado.add(volume);
        for (Eixo eixo : eixosExtras) {
            resultado.add(eixo);
        }
        return resultado;
    }
    
    public Vector<Eixo> getEixosVisiveis() {
        Vector<Eixo> resultado = new Vector<Eixo>();
        if(candles.isVisible()) {
            resultado.add(candles);
        }
        if(volume.isVisible()) {
            resultado.add(volume);
        }
        for (Eixo eixo : eixosExtras) {
            if(eixo.isVisible()) {
                resultado.add(eixo);
            }
        }
        return resultado;
    }
    
}
