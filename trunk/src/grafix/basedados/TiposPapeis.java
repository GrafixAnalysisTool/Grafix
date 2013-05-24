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



/*
 * TiposMercados.java
 *
 * Created on 13 de Maio de 2007, 02:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package grafix.basedados;

import grafix.auxiliar.*;

/**
 *
 * @author Administrador
 */
public class TiposPapeis {
    private boolean lotePadrao;

    private boolean concordatarias;

    private boolean direitoRecibo;

    private boolean cerDebDivBdr;

    private boolean bonus;

    private boolean outros;

    private boolean termo;

    private boolean opcCompIndice;

    private boolean opcVendaIndice;

    private boolean opcoesCompra;

    private boolean opcoesVenda;

    private boolean fracionario;

    private boolean recJudic;

    private boolean fundoImob;
    /** Creates a new instance of TipoMercados */
    public TiposPapeis() {
        setLotePadrao(true);
        setConcordatarias(true);
        setDireitoRecibo(false);
        setCerDebDivBdr(false);
        setBonus(true);
        setOutros(true);
        setTermo(false);
        setOpcCompIndice(true);
        setOpcVendaIndice(true);
        setOpcoesCompra(true);
        setOpcoesVenda(true);
        setFracionario(false);
        setRecJudic(true);
        setFundoImob(true);
        
    }

    public boolean isLotePadrao() {
        return lotePadrao;
    }

    public void setLotePadrao(boolean lotePadrao) {
        this.lotePadrao = lotePadrao;
    }

    public boolean isConcordatarias() {
        return concordatarias;
    }

    public void setConcordatarias(boolean concordatarias) {
        this.concordatarias = concordatarias;
    }

    public boolean isDireitoRecibo() {
        return direitoRecibo;
    }

    public void setDireitoRecibo(boolean direitoRecibo) {
        this.direitoRecibo = direitoRecibo;
    }

    public boolean isCerDebDivBdr() {
        return cerDebDivBdr;
    }

    public void setCerDebDivBdr(boolean cerDebDivBdr) {
        this.cerDebDivBdr = cerDebDivBdr;
    }

    public boolean isBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

    public boolean isOutros() {
        return outros;
    }

    public void setOutros(boolean outros) {
        this.outros = outros;
    }

    public boolean isTermo() {
        return termo;
    }

    public void setTermo(boolean termo) {
        this.termo = termo;
    }

    public boolean isOpcCompIndice() {
        return opcCompIndice;
    }

    public void setOpcCompIndice(boolean opcCompIndice) {
        this.opcCompIndice = opcCompIndice;
    }

    public boolean isOpcVendaIndice() {
        return opcVendaIndice;
    }

    public void setOpcVendaIndice(boolean opcVendaIndice) {
        this.opcVendaIndice = opcVendaIndice;
    }

    public boolean isOpcoesCompra() {
        return opcoesCompra;
    }

    public void setOpcoesCompra(boolean opcoesCompra) {
        this.opcoesCompra = opcoesCompra;
    }

    public boolean isOpcoesVenda() {
        return opcoesVenda;
    }

    public void setOpcoesVenda(boolean opcoesVenda) {
        this.opcoesVenda = opcoesVenda;
    }

    public boolean isFracionario() {
        return fracionario;
    }

    public void setFracionario(boolean fracionario) {
        this.fracionario = fracionario;
    }

    public boolean isRecJudic() {
        return recJudic;
    }

    public void setRecJudic(boolean recJudic) {
        this.recJudic = recJudic;
    }

    public boolean isFundoImob() {
        return fundoImob;
    }

    public void setFundoImob(boolean fundoImob) {
        this.fundoImob = fundoImob;
    }
    
}
