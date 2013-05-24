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


package grafix.auxiliar;

import java.text.*;
import javax.swing.text.*;

public class FormatadorPadrao {

    private SimpleDateFormat dataFormatter;
    private NumberFormatter decimalFormatter;
    private NumberFormatter volumeFormatter;

    public FormatadorPadrao() {
        dataFormatter = new SimpleDateFormat("dd/MM/yy");
        decimalFormatter = new NumberFormatter(new DecimalFormat("0.00"));
        //volumeFormatter = new NumberFormatter(new DecimalFormat("0.00E0"));
        // Mostra o volume em R$ - O volume que est� sendo mostrado � o de dinheiro
        volumeFormatter = new NumberFormatter(new DecimalFormat("0,000.00"));
    }

    public SimpleDateFormat getDataFormatter() {
        return dataFormatter;
    }

    public NumberFormatter getDecimalFormatter() {
        return decimalFormatter;
    }

    public NumberFormatter getVolumeFormatter() {
        return volumeFormatter;
    }
}
