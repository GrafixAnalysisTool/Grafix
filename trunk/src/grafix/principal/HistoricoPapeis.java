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
import grafix.basedados.CadastroPapeis;
import java.util.ArrayList;
import java.util.HashMap;

public class HistoricoPapeis {
    
    private HashMap<String, CadastroPapeis> papeis;
    
    private HistoricoPapeis() {
        setPapeis(new HashMap<String, CadastroPapeis>());
        gravar();
    }
    
    public static HistoricoPapeis carregar() {
        try {
            return (HistoricoPapeis)(new ConfiguracaoXML(ConfiguracoesGrafix.ARQUIVO_HISTORICO_PAPEIS).recuperaDoXML());
        } catch (Exception e) {
            e.printStackTrace();
            return new HistoricoPapeis();
        }
    }
    
    public String getNomeAcao(String cod) {
        try {
            return getPapeis().get(cod).getNome();
        } catch (Exception e) {
            return null;
        }
    }
    
    public double getLiquidezAcao(String cod) {
        try {
            return getPapeis().get(cod).getLiquidez();
        } catch (Exception e) {
            return -1;
        }
    }
    
    public HashMap<String, CadastroPapeis> getPapeis() {
        return papeis;
    }
    
    public void setPapeis(HashMap<String, CadastroPapeis> papeis) {
        this.papeis = papeis;
    }
    
    public void atualizar(ArrayList lista) {  // OTIMIZAR
        try {
         //   List lista = (List)new ConfiguracaoXML(ConfiguracoesGrafix.ARQUIVO_CADASTRO_PAPEIS).recuperaDoXML();
            for (int i = 0; i < lista.size(); i++) {
                CadastroPapeis c = (CadastroPapeis)lista.get(i);
                getPapeis().put(c.getCodigo(), c);
            }
        } catch (Exception ex) {
            System.out.println("Arquivo '" + ConfiguracoesGrafix.ARQUIVO_CADASTRO_PAPEIS + "' n�o encontrado!"); 
        }
        gravar();
    }
    
    private void gravar() {
        new ConfiguracaoXML(ConfiguracoesGrafix.ARQUIVO_HISTORICO_PAPEIS).guardaNoXML(this);
    }
}
