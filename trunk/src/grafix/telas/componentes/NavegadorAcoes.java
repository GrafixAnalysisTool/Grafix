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


package grafix.telas.componentes;

import grafix.principal.Comandos;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NavegadorAcoes extends JPanel {
    
    private JButton botaoAcima = new JButton();
    private JButton botaoAbaixo = new JButton();
    
    /** Creates a new instance of NavegadorAcoes */
    public NavegadorAcoes() {
        this.setLayout(new java.awt.GridLayout(0, 1, 0, 1));
        this.setPreferredSize(new java.awt.Dimension(25, 25));
        botaoAcima.setIcon(new javax.swing.ImageIcon("resource/icones/acaoAcima.gif"));
        botaoAcima.setToolTipText("(Page Up)");
        botaoAcima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAcimaActionPerformed();
            }
        });
        this.add(botaoAcima);
        
        botaoAbaixo.setIcon(new javax.swing.ImageIcon("resource/icones/acaoAbaixo.gif"));
        botaoAbaixo.setToolTipText("(Page Down)");
        botaoAbaixo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAbaixoActionPerformed();
            }
        });
        this.add(botaoAbaixo);
    }
    
    private void botaoAcimaActionPerformed() {
        Comandos.cmdPassarAcao(true);
    }

    private void botaoAbaixoActionPerformed() {
        Comandos.cmdPassarAcao(false);
    }
    
}
