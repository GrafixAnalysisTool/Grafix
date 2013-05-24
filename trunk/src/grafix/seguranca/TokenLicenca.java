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


package grafix.seguranca;

import grafix.principal.Controle;
import java.text.ParseException;

public class TokenLicenca {
    
    private String token1;
    private String token2;
    
    public TokenLicenca(String tk1, String tk2) {
        token1 = tk1;
        token2 = tk2;
    }

    public TokenLicenca(RegistroLicenca reg) {
        token1 = Controle.getCriptografia().cifrarSenha(reg.getEmail());
        token2 = Controle.getCriptografia().cifrarSenha(Controle.getFormatador().getDataFormatter().format(reg.getDataExpiracao()));
    }

    public RegistroLicenca getRegistro() throws ParseException {
        String s1 = Controle.getCriptografia().decifrarSenha(token1);
        String s2 = Controle.getCriptografia().decifrarSenha(token2);
        return new RegistroLicenca(s1, s2);
    }
    


}
