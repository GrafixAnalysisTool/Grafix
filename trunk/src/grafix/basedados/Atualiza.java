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


package grafix.basedados;

import grafix.auxiliar.*;
import grafix.principal.*;
import grafix.telas.secundarias.FormAtualizacao;
import java.io.*;
import java.util.*;
import org.jfree.data.time.Day;

public class Atualiza {
    
//    private String linha = new String();
//    private Acao ibvsp;
//    private int codigoRegistro;
    private BufferedReader b = null;
//    private int dia, mes, ano;
    private static String urlYahoo = "http://table.finance.yahoo.com/table.csv?";
    public ConfiguracaoBaseDados configuracao;
    private FormAtualizacao formAtualizacao;
    
    public Atualiza(FormAtualizacao formAtualizacao) {
        this.formAtualizacao = formAtualizacao;
        File arquivo = new File(ConfiguracoesGrafix.ARQUIVO_CONFIGURACOES_BASEDADOS);
        
        if(arquivo.exists()) {
            ConfiguracaoXML configuracaoXML = new ConfiguracaoXML(ConfiguracoesGrafix.ARQUIVO_CONFIGURACOES_BASEDADOS);
            try {
                configuracao = (ConfiguracaoBaseDados) configuracaoXML.recuperaDoXML();
                configuracao.setSenhaProxy(Controle.getCriptografia().decifrarSenha(configuracao.getSenhaProxy()));
                //configuracao.diretorioCotacoes = System.getProperty("user.home");
                //  configuracao.diretorioCotacoes = "/home/joao/dados/";
                //               "D:/Documents and Settings/Joao/Meus documentos/arquivos/arquivos/";
                //  configuracao.setUrlDatas("http://www.grafix2.com/datas.dat");
                // configuracaoXML.guardaNoXML(configuracao);
                //configuracao.setAtualizaViaInternet(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            this.configuracao = new ConfiguracaoBaseDados();
            // this.configuracao.diretorioCotacoes = "/home/joao/dados/";
            ConfiguracaoXML configuracaoXML = new ConfiguracaoXML(ConfiguracoesGrafix.ARQUIVO_CONFIGURACOES_BASEDADOS);
            configuracaoXML.guardaNoXML(configuracao);
        }
    }
    
    public String fonteBaseDados() {
        String fonte=null;
        String arquivo = Controle.getConfiguracoesGrafix().getPathBaseDados()+"/cfg/fonte";
        fonte = ManipuladorArquivos.lerConteudoDeArquivo(arquivo);
        return fonte;
    }
    
    public int atualizadaDatas(Day dataHoje){
        int retorno=0;
        Day ultimaData= Cotacoes.converterEmData(
                Cotacoes.tail("datas.dat",1).substring(0,10));
        if(ultimaData.compareTo(dataHoje)<=0){
            Download download = new Download(this.configuracao.getUrlDatas(),"datas.dat", false, formAtualizacao);
            retorno = download.baixaArquivo();
        }
        return retorno;
    }
    
    public int atualizaInternacionais() {
        int retorno=0;
        BufferedReader brInternacionais=null;
        
        try {
            brInternacionais =new BufferedReader(new FileReader("internacionais.txt"));
        } catch (FileNotFoundException ex) {
            formAtualizacao.informarLog("Erro ao abrir internacionais.txt");
            ex.printStackTrace();
            return 1;
        }
        
        String linha="asd";
        
        Day dataInicial=null;
        Cotacoes cotas = new Cotacoes(Controle.getConfiguracoesGrafix().getPathBaseDados(), Controle.getConfiguracoesGrafix().getPeriodicidade());
        
        while(linha!=null) {
            try {
                linha = brInternacionais.readLine();
                if(linha!=null) {
                    StringTokenizer st = new StringTokenizer(linha);
                    String codGrafix = st.nextToken().toLowerCase();
                    String codYahoo = st.nextToken().toLowerCase();
                    formAtualizacao.informarLog("Atualizando " + linha);
                    cotas.setCodAcao(codGrafix);
                    if(cotas.existePapel(codGrafix)) {
                        dataInicial = (Day)cotas.dataUltimaCotacao().next();
                    } else {
                        dataInicial = new Day(1,9,1998);
                    }
                    Day dataFim = Uteis.hoje();
                    if(dataInicial.compareTo(dataFim)<=0) {
                        formAtualizacao.informarLog("Periodo " + dataInicial + " at� " + dataFim);
                        if(this.trazInternacional(dataInicial, dataFim,codYahoo, codGrafix)==0) {
                            this.converteYahooGrafix(codGrafix);
                        }
                    } else {
                        formAtualizacao.informarLog(codGrafix + " j� est� atualizado");
                    }
                    
                    
                    if(formAtualizacao.isPararAtualizacao()) {
                        formAtualizacao.informarLog("******* Atualiza��o cancelada pelo usu�rio *******");
                        formAtualizacao.habilitaBotao(true);
                        break;
                    }
                }
                
            } catch (IOException ex) {
                retorno = 1;
                ex.printStackTrace();
            }
            
        }
        return retorno;
    }
    
    private int converteYahooGrafix(String indicador) {
        ReverseFileReader reverse;
        int retorno=0;
        Cotacoes cotas = new Cotacoes(indicador, Controle.getConfiguracoesGrafix().getPathBaseDados(), 1 );
        int dia, mes, ano;
        try {
            reverse = new ReverseFileReader(indicador);
            String linha2 = "SD";
            cotas.abreArquivoParaEscrita(indicador);
            while(linha2!=null) {
                linha2 = reverse.readLine();
                //System.out.println(linha2);
                if((linha2!=null)) {
                    if(linha2.length()>0  && (!linha2.substring(0,4).equals("Date")) ){
                        cotas.setRegistro(cotas.decodificaLinhaYahoo(linha2));
                        cotas.escreveRegistro();
                    }
                }
            }
            cotas.fechaArquivoOut();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return retorno;
    }
    
    public int trazInternacional(Day data1, Day data2, String codYahoo, String codGrafix){
        String url;
        int diaI, diaF, mesI, mesF, anoI, anoF;
        
        diaI = data1.getDayOfMonth();
        diaF = data2.getDayOfMonth();
        
        mesI = data1.getMonth()-1;
        mesF = data2.getMonth();
        
        anoI = data1.getYear();
        anoF = data2.getYear();
        
        
        url = this.urlYahoo+"a="+mesI+"&b="+diaI+"&c="+anoI;//%5E
        //url = url +"&d="+mesF+"&e="+diaF+"&f="+anoF+"&s=%5Eixic&y=0&g=d&ignore=.csv";
        
        url = url +"&d="+mesF+"&e="+diaF+"&f="+anoF+"&s="+codYahoo+"&y=0&g=d&ignore=.csv";
        
        // substitue o ^ de alguns c�digos do yahoo pelo equivalente hex
        url = url.replaceAll("\\^","%5E");
        Download download = null;
        if(this.configuracao.isUsaProxy()) {
            download = new Download(url, codGrafix, this.configuracao.getProxy(),
                    this.configuracao.getPorta(), this.configuracao.getUsuarioProxy(),
                    this.configuracao.getSenhaProxy(), true, formAtualizacao);
        } else {
            download = new Download(url, codGrafix, true, formAtualizacao);
        }
       
        return download.baixaArquivo();
    }
    
    public void atualizaBovespa(){
        MercadoNacional bovespa = new MercadoBovespa(this.configuracao, formAtualizacao);
        bovespa.atualiza();
    }
    
    public String ultimaAtualizacaoBovespa() {
        MercadoNacional bovespa = new MercadoBovespa(this.configuracao, formAtualizacao);
        return bovespa.ultimaAtualizacaoString();
    }
    
    public String ultimaAtualizacaoBMF() {
        MercadoNacional bmf = new MercadoBMF(this.configuracao, formAtualizacao);
        return bmf.ultimaAtualizacaoString();
    }
    
    public void atualizaBMF(){
        MercadoNacional bmf = new MercadoBMF(this.configuracao, formAtualizacao);
        bmf.atualiza();
    }
    
    public void atualizaTudo(){
        this.atualizaBovespa();
        this.atualizaBMF();
        this.atualizaInternacionais();
    }
    
    
    
//    public static void main(String args[]) throws IOException{
//        
//        
//        Atualiza atualiza = new Atualiza();
//        // atualiza.atualiza("bovespa");
//        // atualiza.atualiza("bmf");
//        // atualiza.extraiDadosBMF("BD_Final.txt");
//        String lin = "Datesasd ada";
//        boolean amt = lin.substring(0,4).equals("Date");
//        //atualiza.atualizaInternacionais();
//        RedirectedFrame r = new RedirectedFrame(true);
//        atualiza.atualizaBovespa();
//        
//    }
}
