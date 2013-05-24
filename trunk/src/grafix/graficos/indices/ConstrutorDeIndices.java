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


package grafix.graficos.indices;

public class ConstrutorDeIndices {

    public static final int INDICE_ADX = 1;
    public static final int INDICE_BOLLINGER = 2;
    public static final int INDICE_IFR = 3;
    public static final int INDICE_MACD = 4;
    public static final int INDICE_MM = 5;
    public static final int INDICE_MME = 6;
    public static final int INDICE_OBV = 7;
    public static final int INDICE_PO = 8;
    public static final int INDICE_RMI = 9;
    public static final int INDICE_ROC = 10;
    public static final int INDICE_SAR = 11;
    public static final int INDICE_TRIX = 12;
    public static final int INDICE_USER = 13;
    
    public static Indice novoIndice(ResumoIndice resumo) {
        Indice resultado = null;
        switch(resumo.getId()) {
            case INDICE_ADX:
                resultado = new IndiceADX(resumo.getCor(), resumo.getEspessura(), resumo.isFlag(), resumo.getParam1(), resumo.getParam2(), resumo.getParam3());
                break;
            case INDICE_BOLLINGER:
                resultado = new IndiceBollinger(resumo.getCor(), resumo.getEspessura(), resumo.isFlag(), resumo.getParam1(), resumo.getParam2(), resumo.getParam3());
                break;
            case INDICE_IFR:
                resultado = new IndiceIFR(resumo.getCor(), resumo.getEspessura(), resumo.isFlag(), resumo.getParam1(), resumo.getParam2(), resumo.getParam3());
                break;
            case INDICE_MACD:
                resultado = new IndiceMACD(resumo.getCor(), resumo.getEspessura(), resumo.isFlag(), resumo.getParam1(), resumo.getParam2(), resumo.getParam3());
                break;
            case INDICE_MM:
                resultado = new IndiceMM(resumo.getCor(), resumo.getEspessura(), resumo.isFlag(), resumo.getParam1(), resumo.getParam2(), resumo.getParam3());
                break;
            case INDICE_MME:
                resultado = new IndiceMME(resumo.getCor(), resumo.getEspessura(), resumo.isFlag(), resumo.getParam1(), resumo.getParam2(), resumo.getParam3());
                break;
            case INDICE_OBV:
                resultado = new IndiceOBV(resumo.getCor(), resumo.getEspessura(), resumo.isFlag(), resumo.getParam1(), resumo.getParam2(), resumo.getParam3());
                break;
            case INDICE_PO:
                resultado = new IndicePO(resumo.getCor(), resumo.getEspessura(), resumo.isFlag(), resumo.getParam1(), resumo.getParam2(), resumo.getParam3());
                break;
            case INDICE_RMI:
                resultado = new IndiceRMI(resumo.getCor(), resumo.getEspessura(), resumo.isFlag(), resumo.getParam1(), resumo.getParam2(), resumo.getParam3());
                break;
            case INDICE_ROC:
                resultado = new IndiceROC(resumo.getCor(), resumo.getEspessura(), resumo.isFlag(), resumo.getParam1(), resumo.getParam2(), resumo.getParam3());
                break;
            case INDICE_SAR:
                resultado = new IndiceSAR(resumo.getCor(), resumo.getEspessura(), resumo.isFlag(), resumo.getParam1(), resumo.getParam2(), resumo.getParam3());
                break;
            case INDICE_TRIX:
                resultado = new IndiceTRIX(resumo.getCor(), resumo.getEspessura(), resumo.isFlag(), resumo.getParam1(), resumo.getParam2(), resumo.getParam3());
                break;
            case INDICE_USER:
                resultado = new USERIndice(resumo.getCor(), resumo.getEspessura(), resumo.isFlag(), resumo.getParam1(), resumo.getParam2(), resumo.getParam3());
                break;
            default:
                break;
        }
        if(resultado != null) {
            resultado.setAbrevIndice(resumo.getAbrevIndice());
            resultado.setFileLua(resumo.getFileLua());
            resultado.setNomeFlag(resumo.getNomeFlag());
            resultado.setNomeIndice(resumo.getNomeIndice());
            resultado.setNomeParam1(resumo.getNomeParam1());
            resultado.setNomeParam2(resumo.getNomeParam2());
            resultado.setNomeParam3(resumo.getNomeParam3());
        }
        return resultado; 
    }
        
    
}
