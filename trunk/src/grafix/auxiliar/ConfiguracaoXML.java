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

import java.io.*;
import com.thoughtworks.xstream.XStream;

/**
 * Este e um util componente que intermedia a grava��o e leitura de dados em arquivos XML
 * � utilizada a classe com.thoughtworks.xstream.XStream da biblioteca externa xstream-1.0.1.jar.
 * Este componente faz a grava��o e recupera��o de OBJETOS em arquivos XML.
 */
public class ConfiguracaoXML {

    XStream xstream = new XStream();
    String nomeArquivoXML;

    /**
     * No construtor � passado o nome do arquivo XML com o qual o componente vai trabalhar.
     */
    public ConfiguracaoXML(String nomeArquivoXML) {
        this.nomeArquivoXML = nomeArquivoXML;
    }

    /**
     * O m�todo guardaNoXML recebe um objeto e o guarda no arquivo XML.
     */
    public void guardaNoXML(Object objeto) {
        String novoXML = xstream.toXML(objeto);
        escreverNoArquivo(novoXML);
    }

    /**
     * O m�todo recuperaDoXML recupera o objeto do arquivo e o retorna como um Object.
     * Cabe a quem est� usando este componente, ao receber o Object, fazer a convers�o para o tipo original.
     */
    public Object recuperaDoXML() throws Exception {
        Object objeto;
        try {
            String atualXML = leDoArquivo();
            objeto = xstream.fromXML(atualXML);
        } catch (Exception e) {
            throw e;
        }
        return objeto;
    }

    private void escreverNoArquivo(String texto) {
        try {
            FileWriter writer = new FileWriter(nomeArquivoXML);
            PrintWriter saida = new PrintWriter(writer);
            saida.println(texto);
            saida.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private String leDoArquivo() throws Exception {
//        try {
//            return ManipuladorArquivos.lerConteudoDeArquivo(nomeArquivoXML);
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
    private String leDoArquivo() throws Exception{
        try {
            FileReader reader = new FileReader(nomeArquivoXML);
            BufferedReader leitor = new BufferedReader(reader,1*1024*1024);
            String linha = "";
            StringBuffer resultado = new StringBuffer("");
            linha = leitor.readLine();
            while(linha!=null) {
                resultado.append(linha);
                linha = leitor.readLine();
            }
            leitor.close();
            reader.close();
            return resultado.toString();
        } catch(Exception e) {
            throw e; 
        }
    }
    
/*    public static void main(String[] s) {
    System.out.println("Inicio");
    ConfiguracaoXML xml = new ConfiguracaoXML("/tmp/paulo/teste.xml");
    //        String t = xml.leDoArquivo();
    //        System.out.println(t);
    try {
    java.util.List list = (java.util.ArrayList)xml.recuperaDoXML();
    } catch(Exception e) {}
    System.out.println("Fim");
    }
     */
}
