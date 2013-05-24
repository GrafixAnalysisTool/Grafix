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

import grafix.auxiliar.ConfiguracaoXML;
import grafix.auxiliar.Utils;
import grafix.basedados.ConfiguracaoBaseDados;
import java.io.File;

public class ConfiguracoesVolateis {
    
    private boolean log = false;
    private boolean intraday = false;
    private String vendor="";
    private String usuarioVendor="";
    private String senhaVendor="";
    
/*
 *      Estas s�o as configura��es que n�o s�o salvas ao se fechar o programa.
 */

    public ConfiguracoesVolateis() {
        File arquivo = new File(ConfiguracoesGrafix.ARQUIVO_CONFIGURACOES_BASEDADOS);
        ConfiguracaoBaseDados configuracao = null;
        ConfiguracaoXML configuracaoXML = null;
        if(arquivo.exists()) {
            configuracaoXML = new ConfiguracaoXML(ConfiguracoesGrafix.ARQUIVO_CONFIGURACOES_BASEDADOS);
            try {
                configuracao = (ConfiguracaoBaseDados) configuracaoXML.recuperaDoXML();
                vendor = configuracao.getVendor();
                usuarioVendor = configuracao.getUsuarioVendor();
                senhaVendor = configuracao.getSenhaVendor();
            } catch (Exception ex) {
                Utils.exibeMsgErro("Erro ao ler aqrquivo " +
                        ConfiguracoesGrafix.ARQUIVO_CONFIGURACOES_BASEDADOS);
                ex.printStackTrace();
            }
        }
        
    }

    public boolean isLog() {
        return log;
    }

    public void setLog(boolean log) {
        this.log = log;
    }

    public boolean isIntraday() {
        return intraday;
    }

    public void setIntraday(boolean intraday) {
        this.intraday = intraday;
    }

    public String getUsuarioVendor() {
        return usuarioVendor;
    }

    public void setUsuarioVendor(String usuarioVendor) {
        this.usuarioVendor = usuarioVendor;
    }

    public String getSenhaVendor() {
        return senhaVendor;
    }

    public void setSenhaVendor(String senhaVEndor) {
        this.senhaVendor = senhaVEndor;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    
}
