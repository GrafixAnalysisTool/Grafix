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

import grafix.principal.*;
import org.jfree.data.time.*;

public class IndiceComparativo {
    
    private Day dataInicio = null;
    private Acao acao = null;
    
    public IndiceComparativo(Acao acao, Day dataInicio) {
        setAcao(acao);
        setDataInicio(dataInicio);
    }
    
    public void setDataInicio(Day dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Day getDataInicio() {
        return dataInicio;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public TimeSeries getTimeSeries() {
        TimeSeries ts = new TimeSeries(acao.getCodAcao());
        double referencia = 0;
        boolean definiuReferencia = false;
        for (int i = 0; i < acao.getNumeroRegistros(); i++) {
            RegistroDiario reg = acao.getRegistro(i);
            if(reg.getData().compareTo(dataInicio) > 0) {
                if(!definiuReferencia) {
                    referencia = reg.getClose();
                    definiuReferencia = true;
                }
                ts.add(reg.getData(), new Double((reg.getClose()/referencia - 1)*100));
            }
        }
        return ts;
    }
}
