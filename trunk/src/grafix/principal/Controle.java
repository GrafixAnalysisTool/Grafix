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

import grafix.auxiliar.*;
import grafix.seguranca.ControleRegistro;
import grafix.telas.JanelaGraficos;
import grafix.telas.TelaGrafix;

import java.awt.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Controle {
    
    private static TelaGrafix telaGrafix;
    private static HistoricoPapeis historicoPapeis;
    private static CarteiraDeAcoes carteira;
    private static ConfiguracoesUsuario configuracoesUsuario;
    private static ConfiguracoesGrafix configuracoesGrafix;
    private static ConfiguracoesVolateis configuracoesVolateis;
    private static GeradorCriptografia criptografia;
    private static FormatadorPadrao formatador;
    
    public Controle() {
        // Registro liberado ***********
        ControleRegistro.setRegistrado(true); // ControleRegistro.avaliarRegistro();
        //******************************
        setConfiguracoesGrafix(ConfiguracoesGrafix.carregar());
        setConfiguracoesUsuario(ConfiguracoesUsuario.carregar());
        setConfiguracoesVolateis(new ConfiguracoesVolateis());
        setHistoricoPapeis(HistoricoPapeis.carregar());
        setFormatador(new FormatadorPadrao());
        checarPrimeiroAcesso();
        checarDefinicaoDaListaDePapeis();
        iniciarTeclasAtalho();
        setCarteira(new CarteiraDeAcoes());
        telaGrafix = new TelaGrafix();
        telaGrafix.exibir();
    }
    
    private void checarPrimeiroAcesso() {
       
        // Testa se usu�rio completou o processo
//        if(configuracoesGrafix.isPrimeiroAcesso()) {
//
//            Controle.exibirErro("O programa ser� finalizado pois n�o completou o processo de configura��o inicial da base de dados.");
//            System.exit(1);
//        }
    }
    
    private void iniciarTeclasAtalho() {
        Toolkit.getDefaultToolkit().addAWTEventListener(new TeclasAtalho(),
                AWTEvent.KEY_EVENT_MASK);
    }
    
    private void checarDefinicaoDaListaDePapeis() {
        // A lista definida n�o existe
       
            getConfiguracoesGrafix().setListaAcoes("basica.lst");
            
         
        
        if (!ManipuladorArquivos.existeArquivo(ConfiguracoesGrafix.PASTA_LISTAS + configuracoesGrafix.getListaAcoes())) {
            System.out.println("Lista de ac�es '" + configuracoesGrafix.getListaAcoes() + "' n�o existe!");
            getConfiguracoesGrafix().setListaAcoes("basica.lst");
        }
        if (configuracoesGrafix.getListaAcoes() == null) {
            getConfiguracoesGrafix().setListaAcoes("basica.lst");
        }
    }
    
//    private void prepararAmbienteInicial() {
//        if(getCarteira().getAcoes().size()==0) {
//            AjudaGrafix.exibirMensagem(AjudaGrafix.CARTEIRA_VAZIA);
//        }
//        else {
//            if(telaGrafix.getJanelasGraficos().size()==0) {
//                Comandos.cmdAddJanela();
//            } else {
//                telaGrafix.ativarPrimeiraJanela();
//            }
//        }
//    }
    public static CarteiraDeAcoes getCarteira() {
        return carteira;
    }
    
    public static TelaGrafix getTela() {
        return telaGrafix;
    }
    
    public static JanelaGraficos getJanelaAtiva() {
        return telaGrafix.getJanelaAtiva();
    }
    
    public static ConfiguracoesUsuario getConfiguracoesUsuario() {
        return configuracoesUsuario;
    }
    
    public static void setConfiguracoesUsuario(ConfiguracoesUsuario conf) {
        configuracoesUsuario = conf;
    }
    
    public static void salvarConfiguracoesUsuario(boolean restaurarAposSalvar) {
        //  Atualizar as configuracoesUsuario
        //configuracoesUsuario.setExibeFinaisDeSemana();
        configuracoesUsuario.setNumColunasLayout(telaGrafix.getGridLayoutJanelas().getColumns());
        configuracoesUsuario.setNumLinhasLayout(telaGrafix.getGridLayoutJanelas().getRows());
        configuracoesUsuario.setConfiguracoesJanelas(telaGrafix);
        configuracoesUsuario.salvar(restaurarAposSalvar);
    }
    
    private static void salvarConfiguracoesGrafix() {
        configuracoesGrafix.setLookAndFeel(UIManager.getLookAndFeel().getClass().getName());
        configuracoesGrafix.salvar();
    }
    
    public static void exibirErro(String mensagem) {
        JOptionPane.showMessageDialog(telaGrafix, mensagem, "Erro - Grafix", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void exibirInfo(String mensagem) {
        JOptionPane.showMessageDialog(telaGrafix, mensagem, "Info - Grafix", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void exibirAjuda(String mensagem) {
        JOptionPane.showMessageDialog(telaGrafix, mensagem, "Ajuda - Grafix", JOptionPane.QUESTION_MESSAGE);
    }
    
    public static String exibirInputBox(String mensagem) {
        return JOptionPane.showInputDialog(telaGrafix, mensagem, "Grafix", JOptionPane.QUESTION_MESSAGE);
    }
    
    public static ConfiguracoesGrafix getConfiguracoesGrafix() {
        return configuracoesGrafix;
    }
    
    public static void setConfiguracoesGrafix(ConfiguracoesGrafix aConfiguracoesGrafix) {
        configuracoesGrafix = aConfiguracoesGrafix;
    }
    
    public static void finalizarPrograma() {
        salvarConfiguracoesUsuario(false);
        salvarConfiguracoesGrafix();
    }
    
    public static void setCarteira(CarteiraDeAcoes aCarteira) {
        carteira = aCarteira;
    }
    
    public static void reiniciarModoSeguro() {/////////////////////////////////////////////////////////////////////////
        exibirErro("As configuracoes foram reiniciadas devido a um erro desconhecido. Reinicie a aplica��o.");
        //telaGrafix.fecharJanelas();
        configuracoesGrafix = ConfiguracoesGrafix.reiniciar();
        configuracoesUsuario = ConfiguracoesUsuario.reiniciar();
        configuracoesGrafix.salvar();
        configuracoesUsuario.salvar(false);
    }
    
    public static GeradorCriptografia getCriptografia() {
        return criptografia;
    }
    
    public static void setCriptografia(GeradorCriptografia aCriptografia) {
        criptografia = aCriptografia;
    }
    
    public static HistoricoPapeis getHistoricoPapeis() {
        return historicoPapeis;
    }
    
    public static void setHistoricoPapeis(HistoricoPapeis aHistoricoPapeis) {
        historicoPapeis = aHistoricoPapeis;
    }
    
    public static FormatadorPadrao getFormatador() {
        return formatador;
    }
    
    public static void setFormatador(FormatadorPadrao aFormatador) {
        formatador = aFormatador;
    }
    
//    private void criarListaDefault() {
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
    public static void apagarRegistros(Acao acaoAntiga) {
        Vector<JanelaGraficos> janelas = getTela().getJanelasGraficos();
        for (JanelaGraficos j : janelas) {
            if (j.getAcao().getCodAcao().equals(acaoAntiga.getCodAcao())) {
                return; // Existe alguma janela usando esta a��o.
            }
        }
        acaoAntiga.apagarRegistros();
    }
    
    public static void apagarTodosOsRegistros() {
        Vector<JanelaGraficos> janelas = Controle.getTela().getJanelasGraficos();
        for (JanelaGraficos j : janelas) {
            j.getAcao().apagarRegistros();
        }
    }
    
    static void reiniciarTodosOsIntervaloExibicao() {
        Vector<JanelaGraficos> janelas = Controle.getTela().getJanelasGraficos();
        for (JanelaGraficos j : janelas) {
            j.iniciarIntervaloExibicao();
        }
    }
    
    public static ConfiguracoesVolateis getConfiguracoesVolateis() {
        return configuracoesVolateis;
    }
    
    public static void setConfiguracoesVolateis(ConfiguracoesVolateis aConfiguracoesVolateis) {
        configuracoesVolateis = aConfiguracoesVolateis;
    }
}
