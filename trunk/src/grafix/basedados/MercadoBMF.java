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
 * MercadoBMF.java
 *
 * Created on 24 de Julho de 2007, 19:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package grafix.basedados;

import grafix.principal.Controle;
import grafix.telas.secundarias.FormAtualizacao;
import java.io.File;

/**
 *
 * @author joao
 */
public class MercadoBMF extends MercadoNacional {

    /** Creates a new instance of BMF */
    public MercadoBMF(ConfiguracaoBaseDados configuracao, FormAtualizacao formAtualizacao) {
        super(formAtualizacao);
        this.configuracao = configuracao;
        this.arquivoDatasJaRealizadas = Controle.getConfiguracoesGrafix().getPathBaseDados() + File.separator + "cfg" +
                File.separator + "datasbmf";
        this.boletim = new BoletimBMF(configuracao, formAtualizacao);
    }
}
