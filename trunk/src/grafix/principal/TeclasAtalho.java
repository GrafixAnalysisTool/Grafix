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

import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

public class TeclasAtalho
        implements AWTEventListener {

    public void eventDispatched(AWTEvent event) {
        try {
            if (event instanceof KeyEvent) {
                KeyEvent keyEvent = (KeyEvent) event;
                if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
                    int keyCode = keyEvent.getKeyCode();
                    if (keyCode == KeyEvent.VK_PAGE_DOWN) {
                        Comandos.cmdPassarAcao(false);
                    }
                    if (keyCode == KeyEvent.VK_PAGE_UP) {
                        Comandos.cmdPassarAcao(true);
                    }
                    if (keyEvent.isControlDown() && keyCode == KeyEvent.VK_H) {
                        Comandos.cmdZoomHistorico();
                    }
                    if (keyEvent.isControlDown() && keyCode == KeyEvent.VK_N) {
                        Comandos.cmdZoomNormal();
                    }
                    if (keyEvent.isControlDown() && keyCode == KeyEvent.VK_RIGHT) {
                        Comandos.cmdMoveDireita();
                    }
                    if (keyEvent.isControlDown() && keyCode == KeyEvent.VK_LEFT) {
                        Comandos.cmdMoveEsquerda();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
