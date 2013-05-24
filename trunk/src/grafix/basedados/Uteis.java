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
 * Uteis.java
 *
 * Created on 24 de Julho de 2007, 16:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package grafix.basedados;

import java.util.Calendar;
import org.jfree.data.time.Day;

/**
 *
 * @author joao
 */
public class Uteis {
    
    /** Creates a new instance of Uteis */
    public Uteis() {
    }
    
    public static String removeBrancos(String nome) {
        int i, j,tamanho;
        char frase[] = new char[100];
        char c;
        tamanho=nome.length();
        for(i=0,j=0;i<tamanho;++i) {
            if(nome.charAt(i)!=' '){
                frase[j]=nome.charAt(i);
                ++j;
            }
        }
        return new String(frase).trim();
    }
    
     public static String converteCString2Java(byte []b, int ini, int comp) {
        char palavra[] = new char[comp];
        int j=0;
        for(int i=ini;i<ini+comp;++i,++j) {
            palavra[j] = (char)b[i];
            if(palavra[j]=='\0') break;
            
        }
        return new String(palavra,0,j);
    
    }
    
     public static Day hoje() {
        Calendar calendar = Calendar.getInstance();
        Day dataHoje = new Day(calendar.get(Calendar.DAY_OF_MONTH),
                1+calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR));
        return dataHoje;
    }
    
}
