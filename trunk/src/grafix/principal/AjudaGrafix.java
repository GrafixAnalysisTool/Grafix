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

public class AjudaGrafix {

    public static String CARTEIRA_VAZIA = "\n" +
                    "A carteira de a��es est� vazia. Pode estar acontecendo uma das seguintes situa��es:        \n\n" +
                    "- A pasta da dados n�o est� corretamente apontada.\n" +
                    "    . Clique em 'Ferramentas > Op��es', e configure a pasta correta.\n\n" +
                    "- A lista de a��es est� vazia, n�o inclui nenhuma das a��es da pasta de dados.\n" +
                    "    . Clique em Ferramentas > Lista de Pap�is e escolha uma lista apropriada.\n\n" +
                    "- A pasta de dados est� vazia.\n" +
                    "    . Atualizar a base de dados clicando em Arquivo > Atualizar.\n\n" +
                    "\n";

    public static String LISTA_INEXISTENTE = "" +
                    "Nenhuma lista de a��es personalizada foi definida!           \n" +
                    "Todas as a��es est�o sendo mostradas para sele��o. \n\n" +
                    "    . Clique em  'Ferramentas > Lista de Pap�is'  \n" +
                    "      e escolha uma lista com os pap�is do seu interesse. \n" +
                    "      Voc� pode criar novas listas!\n" +
                    "\n";
    
    public static String ERRO_MARCAS = "" +
                    "Ocorreu um erro ao adicionar as marcas ao gr�fico.           \n" +
                    "Todas as marcas foram exclu�das! \n\n";
    
    public AjudaGrafix() {
    }

    public static void exibirMensagem(String mensagem) {
        Controle.exibirAjuda(mensagem);
    }
    
}
