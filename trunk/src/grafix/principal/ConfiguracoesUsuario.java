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



package grafix.principal;

import grafix.auxiliar.*;
import grafix.graficos.*;
import grafix.graficos.eixos.Eixo;
import grafix.telas.*;
import java.awt.Color;
import java.util.Vector;

/*
 *      Estas s�o as configura��es que s�o salvas como 'templates' ou perfis de usu�rio.
 */

public class ConfiguracoesUsuario {
    
    private String nome;
    private boolean exibeSomenteDiasUteis;
    private boolean exibeEixoX;
    private boolean cursorExtendido;
    private Vector<ConfiguracoesJanela> configuracoesJanelas;
    private int numLinhasLayout;
    private int numColunasLayout;
    private Color corCandlesAlta;
    private Color corCandlesBaixa;
    private Color corVolume;
    private int espacColunasVolume;

    static final public String ARQUIVO_CONFIGURACOES_USUARIO = "conf/configuracoesUsuario.xml";
    
    private ConfiguracoesUsuario() {
        nome = "DEFAULT";
        exibeSomenteDiasUteis = true;
        exibeEixoX = false;
        configuracoesJanelas = new Vector<ConfiguracoesJanela>();
        configuracoesJanelas.add(new ConfiguracoesJanela());
        numLinhasLayout = 1;
        numColunasLayout = 1;
        corCandlesAlta = Color.BLUE;
        corCandlesBaixa = new Color(128,128,0) ;
        corVolume = new Color(45,130,0);
        espacColunasVolume = 6;
    }
    
    public static ConfiguracoesUsuario carregar() {
        ConfiguracoesUsuario conf = LeitorArquivoConfiguracao.getInstance().lerConfiguracoesUsuario();
        if(conf==null) {
            return new ConfiguracoesUsuario();
        } else {
            conf.restaurarAposCarregamento();
            return conf;
        }
    }
    
    public static ConfiguracoesUsuario reiniciar() {
        return new ConfiguracoesUsuario();
    }

    public void restaurarAposCarregamento() {
        for (ConfiguracoesJanela cj : getConfiguracoesJanelas()) {
            for (Eixo eixo : cj.getEixos()) {
                eixo.restaurarAposPersistenciaEmXML();
            }
        }
    }

    private void prepararParaSalvar() {
        for (ConfiguracoesJanela cj : getConfiguracoesJanelas()) {
            for (Eixo eixo : cj.getEixos()) {
                eixo.prepararPersistenciaEmXML();
            }
        }
    }

    public void salvar(boolean restaurarAposSalvar) {
        prepararParaSalvar();
        new ConfiguracaoXML(ARQUIVO_CONFIGURACOES_USUARIO).guardaNoXML(this);
        if(restaurarAposSalvar) {
            restaurarAposCarregamento();
        }
    }
    
    public boolean isExibeSomenteDiasUteis() {
        return exibeSomenteDiasUteis;
    }
    
    public void setExibeSomenteDiasUteis(boolean exibeSomenteDiasUteis) {
        this.exibeSomenteDiasUteis = exibeSomenteDiasUteis;
    }
    
    public Vector<ConfiguracoesJanela> getConfiguracoesJanelas() {
        return configuracoesJanelas;
    }
    
    public void setConfiguracoesJanelas(TelaGrafix tela) {
        this.configuracoesJanelas = new Vector<ConfiguracoesJanela>();
        for (JanelaGraficos j : tela.getJanelasGraficos()) {
            ConfiguracoesJanela conf = new ConfiguracoesJanela(j);
            configuracoesJanelas.add(conf);
        }
    }

    public boolean isExibeEixoX() {
        return exibeEixoX;
    }

    public void setExibeEixoX(boolean exibeEixoX) {
        this.exibeEixoX = exibeEixoX;
    }

    public boolean isCursorExtendido() {
        return cursorExtendido;
    }

    public void setCursorExtendido(boolean cursorExtendido) {
        this.cursorExtendido = cursorExtendido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

    public int getEspacColunasVolume() {
        return espacColunasVolume;
    }

    public void setEspacColunasVolume(int espacColunasVolume) {
        this.espacColunasVolume = espacColunasVolume;
    }

    public int getNumLinhasLayout() {
        return numLinhasLayout;
    }

    public void setNumLinhasLayout(int numLinhasLayout) {
        this.numLinhasLayout = numLinhasLayout;
    }

    public int getNumColunasLayout() {
        return numColunasLayout;
    }

    public void setNumColunasLayout(int numColunasLayout) {
        this.numColunasLayout = numColunasLayout;
    }

    public Color getCorCandlesAlta() {
        return corCandlesAlta;
    }

    public void setCorCandlesAlta(Color corCandlesAlta) {
        this.corCandlesAlta = corCandlesAlta;
    }

    public Color getCorCandlesBaixa() {
        return corCandlesBaixa;
    }

    public void setCorCandlesBaixa(Color corCandlesBaixa) {
        this.corCandlesBaixa = corCandlesBaixa;
    }

    public Color getCorVolume() {
        return corVolume;
    }

    public void setCorVolume(Color corVolume) {
        this.corVolume = corVolume;
    }

}
