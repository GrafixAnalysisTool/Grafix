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

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.security.Provider;
import java.security.Security;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import org.jfree.data.time.Day;

public class Utils {
  private static String tituloJanelaMsg = "???";
  private static Frame framePrincipal = null;

  // Separador de linha padr�o do sistema
  public static final String SEPLINHA =
    System.getProperty("line.separator", "\r\n");

  // Objetos para formata��o de n�meros
  private static DecimalFormat decFormat;
  private static String ultFormato = "";

  public static final DecimalFormat DECFORMAT_FIXO = new DecimalFormat("0.##");

  // Objetos para formata��o de data
  public static final SimpleDateFormat FORMATO_DATA_DMA =
    new SimpleDateFormat("dd/MM/yyyy");

  public static final SimpleDateFormat FORMATO_DATA_DMAHORA =
    new SimpleDateFormat("dd/MM/yyyy HH:mm");

  // Construtor padr�o oculto - a classe oferece apenas membros est�ticos
  private Utils() { }

  // Formata <numero> segundo <formato>
  public static String formataNum(double numero, String formato) {
    if (decFormat == null) {
      decFormat = new DecimalFormat();
    }

    if (!ultFormato.equals(formato)) {
      decFormat.applyPattern(formato);
      ultFormato = formato;
    }

    return decFormat.format(numero);
  }

  // Formata <numero> segundo um formato padr�o
  public static String formataNum(double numero) {
    return formataNum(numero, "0.##");
  }

  // Formato <numero> segundo um formato fixo
  public static String formataNumFixo(double numero) {
    return DECFORMAT_FIXO.format(numero);
  }

  // Define <f> como o frame principal do sistema. �til para
  // fixar o frame raiz de frames modais
  public static void setFramePrincipal(Frame f) {
    framePrincipal = f;
  }

  // Obt�m o frame principal do sistema
  public static Frame getFramePrincipal() {
    return framePrincipal;
  }

  // ToDo: ... documentar ...
  public static String getTituloJanelaMsg() {
    return tituloJanelaMsg;
  }

  // ToDo: ... documentar ...
  public static void setTituloJanelaMsg(String TituloJanelaMsg) {
    tituloJanelaMsg = TituloJanelaMsg;
  }

  // Muda o cursor do frame principal do sistema para um cursor gen�rico
  public static void setCursorFramePrincipal(java.awt.Cursor novoCursor) {
    if (framePrincipal != null) {
      framePrincipal.setCursor(novoCursor);
    }
  }

  // Muda o cursor do frame principal do sistema para um dos cursores
  // predefinidos
  public static void setCursorFramePrincipal(int tipo) {
    setCursorFramePrincipal(Cursor.getPredefinedCursor(tipo));
  }

  // Exibe uma mensagem na tela. <tipoMsg> pode ser uma combina��o
  // dos flags normalmente passados para JOptionPane.showMessageDialog()
  public static void exibeMsg(String msg, int tipoMsg) {
    JOptionPane.showMessageDialog(framePrincipal, msg, tituloJanelaMsg,
      tipoMsg);
  }

  // Exibe uma mensagem de erro na tela
  public static void exibeMsgErro(String msg) {
    exibeMsg(msg, JOptionPane.ERROR_MESSAGE);
  }

  // Pede uma confirma��o de opera��o via caixa de di�logo. <opcoes> e
  // <tipoMsg> podem ser combina��es dos flags normalmente passados para
  // JOptionPane.showMessageDialog()
  public static int pedeConfirmacao(String msg, int opcoes, int tipoMsg) {
    return JOptionPane.showConfirmDialog(framePrincipal, msg,
             tituloJanelaMsg, opcoes, tipoMsg);
  }

  // Extrai o diret�rio de um arquivo. A sequ�ncia retornada *N�O*
  // termina com o caractere separador de diret�rios
  public static String extraiDiretorio(File arquivo) {
    String s = arquivo.getPath();
    int i = s.lastIndexOf(arquivo.getName());

    if ((i > 0) &&
        (i < s.length() - 1)) {
      s = s.substring(0, i);

      if (s.endsWith(File.separator)) {
        s = s.substring(0, s.length() - 1);
      }

      return s;
    }
    else {
      return s;
    }
  }

  // Extrai a extens�o de um arquivo.
  // Baseado em:
  //   http://java.sun.com/docs/books/tutorial/uiswing/components/examples/Utils.java
  //   http://java.sun.com/docs/books/tutorial/uiswing/components/filechooser.html
  public static String getExtension(File f) {
    String ext = null;
    String s = f.getName();
    int i = s.lastIndexOf('.');

    if ((i > 0) &&
        (i < s.length() - 1)) {
      ext = s.substring(i + 1).toLowerCase();
    }

    return ext;
  }

  // Muda a extens�o de um nome de arquivo
  public static String mudaExtensao(String nomeArquivo,
      String novaExtensao) {
/*  int i = nomeArquivo.lastIndexOf('.');

    if ((i > 0) &&
        (i < nomeArquivo.length() - 1)) {
      return nomeArquivo.substring(0, i + 1) + novaExtensao;
    }
    else {
      return new String(nomeArquivo);
    } */

    File f = new File(nomeArquivo);
    String s = f.getName();
    int i = s.lastIndexOf('.');

    if ((i > 0) &&
        (i < s.length() - 1)) {
      s = s.substring(0, i + 1) + novaExtensao;
    }

    try {
      return new File(f.getParentFile(), s).getCanonicalPath();
    } catch (IOException ex) {
      return "";
    }
  }

  // Dada uma string qualquer, uma string representando verdadeiro, uma
  // string representando falso e um valor padr�o, retorna o valor
  // booleano correspondente � string
  public static boolean strParaBoolean(String s, String verdadeiro,
      String falso, boolean padrao) {
    s = s.toUpperCase();

    if (s.equals(verdadeiro.toUpperCase())) {
      return true;
    }
    else if (s.equals(falso.toUpperCase())) {
      return false;
    }
    else {
      return padrao;
    }
  }

  // Dado um valor booleano, retorna a string correspondente a ele,
  // de acordo com as strings passadas representando verdadeiro e falso
  public static String booleanParaStr(boolean b, String verdadeiro,
      String falso) {
    return b ? verdadeiro : falso;
  }

  // Converte uma string para inteiro. Caso a convers�o falhe, retorna um
  // valor padr�o
  public static int strParaInt(String s, int valorPadrao) {
    try {
      return Integer.parseInt(s.trim());
    } catch (Exception ex) {
      return valorPadrao;
    }
  }

  // Converte uma string para double. Caso a convers�o falhe, retorna um
  // valor padr�o
  public static double strParaDouble(String s, double valorPadrao) {
    try {
      return Double.parseDouble(s.trim());
    } catch (Exception ex) {
      return valorPadrao;
    }
  }

  // Converte uma string para double atrav�s de um DecimalFormat
  // espec�fico. Caso a convers�o falhe, retorna um valor padr�o
  public static double strParaDouble(String s, double valorPadrao,
      DecimalFormat decFormat) {
    try {
      s = s.trim();

      return (decFormat != null) ?
        decFormat.parse(s).doubleValue() :
        Double.parseDouble(s);
    } catch (Exception ex) {
      return valorPadrao;
    }
  }

  // Quantidade de dias em cada m�s do ano
  public static final int DIAS_NO_MES[] =
    {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  // Verifica se <ano> � bissexto
  public static boolean anoBissexto(int ano) {
    return (ano % 4 == 0) && ((ano % 100 != 0) || (ano % 400 == 0));
  }

  // Retorna a quantidade de dias no m�s para <mes> e <ano>
  public static int diasNoMes(int mes, int ano) {
    int resultado = 0;

    switch (mes) {
      case 1:  // Janeiro
      case 3:  // Mar�o
      case 4:  // ...
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
        resultado = DIAS_NO_MES[mes - 1];
        break;

      case 2:  // Fevereiro
        resultado = DIAS_NO_MES[mes - 1];
        if (anoBissexto(ano)) {
          resultado++;
        }
        break;
    }

    return resultado;
  }

  // Converte uma string de data no formato DD/MM/AAAA para um objeto
  // java.util.Date
  public static java.util.Date strDMAParaDate(String data) {
    java.util.Date result = null;

    try {
/*    result = FORMATO_DATA_DMA.parse(data); */

      data = preencheStrDireita(data, 10, '0');

      int dia = Integer.parseInt(data.substring(0, 2).trim());
      int mes = Integer.parseInt(data.substring(3, 5).trim());
      int ano = Integer.parseInt(data.substring(6, 10).trim());

      if ((mes < 1) || (mes > 12)) {
        throw new NumberFormatException();
      }

      if ((dia < 1) ||
          (dia > diasNoMes(mes, ano))) {
        throw new NumberFormatException();
      }

//    result = new GregorianCalendar(ano, mes - 1, dia, 0, 0, 0).getTime();
      Calendar cal = Calendar.getInstance();
      cal.clear();
      cal.set(ano, mes - 1, dia, 0, 0, 0);
      result = cal.getTime();
    } catch (Exception ex) {
      return null;
    }

    return result;
  }

  // Converte uma string de data/hora no formato DD/MM/AAAA HH:mm para um
  // objeto java.util.Date
  public static java.util.Date strDMAHoraParaDate(String data) {
    java.util.Date result = null;

    try {
/*    result = FORMATO_DATA_DMAHORA.parse(data); */

      data = preencheStrDireita(data, 16, '0');

      int dia  = Integer.parseInt(data.substring(0, 2).trim());
      int mes  = Integer.parseInt(data.substring(3, 5).trim());
      int ano  = Integer.parseInt(data.substring(6, 10).trim());
      int hora = Integer.parseInt(data.substring(11, 13).trim());
      int min  = Integer.parseInt(data.substring(14, 16).trim());

      if ((mes < 1) || (mes > 12)) {
        throw new NumberFormatException();
      }

      if ((dia < 1) ||
          (dia > diasNoMes(mes, ano))) {
        throw new NumberFormatException();
      }

      if ((hora < 0) || (hora > 23)) {
        throw new NumberFormatException();
      }

      if ((min < 0) || (min > 59)) {
        throw new NumberFormatException();
      }

//    result = new GregorianCalendar(ano, mes - 1, dia, hora, min, 0).getTime();
      Calendar cal = Calendar.getInstance();
      cal.clear();
      cal.set(ano, mes - 1, dia, hora, min, 0);
      result = cal.getTime();
    } catch (Exception ex) {
      return null;
    }

    return result;
  }

  // Associa uma tecla a uma determinada a��o (para um JRootPane, i.e. Frames
  // e Dialogs)
  public static void associaTeclaAcao(JRootPane rootPane, KeyStroke tecla,
      String nomeAcao, javax.swing.Action acao) {
    // Mapeia a tecla ao nome de a��o informado, e em seguida mapeia o nome
    // de a��o informado � rotina desejada
    rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(tecla, nomeAcao);
    rootPane.getActionMap().put(nomeAcao, acao);
  }

  // Associa uma tecla a uma determinada a��o (para um Componente qualquer)
  public static void associaTeclaAcao(JComponent component, KeyStroke tecla,
      String nomeAcao, javax.swing.Action acao) {
    // Mapeia a tecla ao nome de a��o informado, e em seguida mapeia o nome
    // de a��o informado � rotina desejada
    component.getInputMap(JComponent.WHEN_FOCUSED).put(tecla, nomeAcao);
    component.getActionMap().put(nomeAcao, acao);
  }

  // Retorna um array contendo todos os servi�os de seguran�a/criptografia
  // dispon�veis atualmente.
  // This method returns all available services types.
  // Retirado de:
  //   http://javaalmanac.com/egs/java.security/ListServices.html
  public static String[] getServiceTypes() {
    Set result = new HashSet();
    
    // All providers
    Provider[] providers = Security.getProviders();

    for (int i = 0; i < providers.length; i++) {
      // Get services provided by each provider
      Set keys = providers[i].keySet();

      for (Iterator it = keys.iterator(); it.hasNext(); ) {
        String key = (String) it.next();
        key = key.split(" ")[0];
        
        if (key.startsWith("Alg.Alias.")) {
          // Strip the alias
          key = key.substring(10);
        }

        int ix = key.indexOf('.');
        result.add(key.substring(0, ix));
      }
    }

    return (String[]) result.toArray(new String[result.size()]);
  }

  // Retorna um array contendo todas as implementa��es de um determinado
  // servi�o de seguran�a/criptografia. O par�metro <serviceType> deve ser
  // um dos resultados obtidos ao chamar o m�todo anterior, getServiceTypes().
  // Exemplo de uso:
  //   String[] names = getCryptoImpls("MessageDigest");
  // This method returns the available implementations for a service type.
  // Retirado de:
  //   http://javaalmanac.com/egs/java.security/ListServices.html
  public static String[] getCryptoImpls(String serviceType) {
    Set result = new HashSet();
    
    // All providers
    Provider[] providers = Security.getProviders();

    for (int i = 0; i < providers.length; i++) {
      // Get services provided by each provider
      Set keys = providers[i].keySet();

      for (Iterator it = keys.iterator(); it.hasNext(); ) {
        String key = (String) it.next();
        key = key.split(" ")[0];
        
        if (key.startsWith(serviceType + ".")) {
          result.add(key.substring(serviceType.length() + 1));
        }
        else if (key.startsWith("Alg.Alias." + serviceType + ".")) {
          // This is an alias
          result.add(key.substring(serviceType.length() + 11));
        }
      }
    }

    return (String[]) result.toArray(new String[result.size()]);
  }

  /**
  * Converte um array de bytes para uma string em formato hexadecimal.
  * Retirado de:
  *   http://www.devx.com/tips/Tip/13540
  * @return result String buffer in String format 
  * @param in byte[] buffer to convert to string format
  */
  public static final String DIGITOS_HEXA[] =
    {"0", "1", "2", "3", "4", "5", "6", "7",
     "8", "9", "A", "B", "C", "D", "E", "F"};

  public static String bytesParaStrHexa(byte entrada[]) {
    if ((entrada == null) ||
        (entrada.length <= 0)) {
      return null;
    }

    StringBuffer saida = new StringBuffer(entrada.length * 2);
    byte ch;

    for (int i = 0; i < entrada.length; i++) {
      ch = (byte) (entrada[i] & 0xF0);       // Strip off high nibble
      ch = (byte) (ch >>> 4);                // Shift the bits down
      ch = (byte) (ch & 0x0F);               // Must do this if high order bit is on!
      saida.append(DIGITOS_HEXA[(int) ch]);  // Convert the nibble to a String Character
      ch = (byte) (entrada[i] & 0x0F);       // Strip off low nibble 
      saida.append(DIGITOS_HEXA[(int) ch]);  // Convert the nibble to a String Character
    }

    return new String(saida);
  }

  // ...
  public static int byteParaInteiroSemSinal(byte b) {
    byte nibbleMaior = (byte) (b & 0xF0);       // Strip off high nibble
    nibbleMaior = (byte) (nibbleMaior >>> 4);   // Shift the bits down
    nibbleMaior = (byte) (nibbleMaior & 0x0F);  // Must do this if high order bit is on!
    byte nibbleMenor = (byte) (b & 0x0F);       // Strip off low nibble 

    return (((int) nibbleMaior) << 4) | nibbleMenor;
  }

  // Converte uma string em formato hexadecimal (preferencialmente gerada pela
  // fun��o anterior) para um array de bytes. Cada par de caracteres da
  // string original corresponde a um byte no array de retorno
  public static byte[] strHexaParaBytes(String s) {
    if (s == null) {
      return null;
    }

    int tamS = s.length();
    int i = 0, j = 0;
    byte[] result = new byte[tamS / 2];

    while (i < tamS) {
      result[j] = (byte) Integer.parseInt(s.substring(i, i + 2), 16);

      i += 2;
      j++;
    }

    return result;
  }

  // Ajusta a FormatterFactory de um JFormattedTextField para a m�scara
  // especificada
  public static void setFormatterFactory(JFormattedTextField componente,
      String mascara) throws ParseException {
    componente.setFormatterFactory(new DefaultFormatterFactory(
      new MaskFormatter(mascara)));
  }

  // Dada a string <s>, preenche-a pela esquerda com o caractere <c> at�
  // que a string tenha tamanho <numCasas>
  public static String preencheStrEsquerda(String s, int numCasas, char c) {
    if (s == null) {
      return null;
    }

    int tam = s.length();

    if (tam >= numCasas) {
      return new String(s);
    }

    int casasFaltando = numCasas - tam;
    StringBuffer buffer = new StringBuffer(casasFaltando);

    for (int i = 0; i < casasFaltando; i++) {
      buffer.append(c);
    }

    return buffer.toString() + s;
  }

  // Dada a string <s>, preenche-a pela direita com o caractere <c> at�
  // que a string tenha tamanho <numCasas>
  public static String preencheStrDireita(String s, int numCasas, char c) {
    if (s == null) {
      return null;
    }

    int tam = s.length();

    if (tam >= numCasas) {
      return new String(s);
    }

    int casasFaltando = numCasas - tam;
    StringBuffer buffer = new StringBuffer(casasFaltando);

    for (int i = 0; i < casasFaltando; i++) {
      buffer.append(c);
    }

    return s + buffer.toString();
  }

  // Dada uma ComboBox, retorna o TextField usado para edit�-la (quando
  // aplic�vel)
  public static JTextField getJComboBoxJTextField(JComboBox comboBox) {
    JTextField result = null;

    ComboBoxEditor cbe = comboBox.getEditor();
    if (cbe != null) {
      Component c = cbe.getEditorComponent();
      if (c instanceof JTextField) {
        result = (JTextField) c;
      }
    }

    return result;
  }

  // Extrai a string selecionada/digitada numa ComboBox
  public static String getJComboBoxString(JComboBox comboBox) {
    String result = "";

    Object obj = comboBox.getSelectedItem();
    if (obj != null) {
      result = obj.toString();
    }
    else {
      JTextField jtf = getJComboBoxJTextField(comboBox);
      if (jtf != null) {
        result = jtf.getText();
      }
    }

    return result;
  }

  // ToDo: ... comentar ...
  public static java.util.Date zeraHora(java.util.Date d) {
    if (d == null) {
      return d;
    }

    // Zera hora, minuto, segundo e milissegundo
//  GregorianCalendar cal = new GregorianCalendar();
    Calendar cal = Calendar.getInstance();
    cal.setTime(d);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE,      0);
    cal.set(Calendar.SECOND,      0);
    cal.set(Calendar.MILLISECOND, 0);

//  return new java.util.Date(cal.getTimeInMillis());
    return cal.getTime();
  }
  

   public static String getDataSistema() {
        return Utils.FORMATO_DATA_DMA.format(new Date());
       // return dateFormat.format(date);
    }

  // ToDo: ... comentar ...
  public static Color decodificaCor(String s, Color resultPadrao) {
    Color result = resultPadrao;

    try {
      String[] itens = s.split(",", 3);

      if (itens.length >= 3) {
        int r = Integer.parseInt(itens[0].trim());
        int g = Integer.parseInt(itens[1].trim());
        int b = Integer.parseInt(itens[2].trim());
        result = new Color(r, g, b);
      }
    } catch (Exception ex) { }

    return result;
  }

  // ToDo: ... comentar ...
  public static String codificaCor(Color c) {
    return "" + c.getRed() + "," +
      c.getGreen() + "," +
      c.getBlue();
  }

  // ...
  public static boolean equalsSeguro(Object obj1, Object obj2,
      boolean converterDataParaTimestamp) {
    // Se <obj1> for do tipo java.util.Date, iremos "promov�-lo"
    // a java.sql.Timestamp para que a compara��o com <obj2> n�o
    // falhe de forma incondicional
    if (converterDataParaTimestamp &&
        (obj1 != null)) {
      if (!(obj1 instanceof java.sql.Timestamp) &&
          obj1 instanceof java.util.Date) {
        obj1 = new java.sql.Timestamp(
          ((java.util.Date) obj1).getTime());
      }
    }

    // Se <obj2> for do tipo java.util.Date, iremos "promov�-lo"
    // a java.sql.Timestamp para que a compara��o com <obj1> n�o
    // falhe de forma incondicional
    if (converterDataParaTimestamp &&
        (obj2 != null)) {
      if (!(obj2 instanceof java.sql.Timestamp) &&
          obj2 instanceof java.util.Date) {
        obj2 = new java.sql.Timestamp(
          ((java.util.Date) obj2).getTime());
      }
    }

    // Compara��o efetiva
    if (obj1 == null) {
      if (obj2 == null) {
        return true;
      }
      else {
        return obj2.equals(obj1);
      }
    }
    else {
      return obj1.equals(obj2);
    }
  }

  // ...
  public static boolean equalsSeguro(Object obj1, Object obj2) {
    return equalsSeguro(obj1, obj2, true);
  }

  // ...
  public static boolean equalsIgnoreCaseSeguro(String s1, String s2) {
    if (s1 == null) {
      return (s2 == null) ? true : s2.equalsIgnoreCase(s1);
    }
    else {
      return s1.equalsIgnoreCase(s2);
    }
  }
 
 public static int diaDaSemana(int dia, int mes, int ano) { 
      Calendar calendario = new GregorianCalendar(ano, mes - 1, dia);  
      return calendario.get(Calendar.DAY_OF_WEEK);   

 } 
 
 public static Day adicionaDias(Day data, int dias) {
     Calendar calendario = new GregorianCalendar(data.getYear(), 
              data.getMonth() - 1, data.getDayOfMonth());
      calendario.add(Calendar.DAY_OF_MONTH,dias);
      return new Day(calendario.get(Calendar.DAY_OF_MONTH), calendario.get(Calendar.MONTH) + 1,
              calendario.get(Calendar.YEAR));
 
 }

 public static boolean mesmaSemana(Day inicio, Day fim) {
        Calendar c1 = new GregorianCalendar(inicio.getYear(),
                inicio.getMonth()-1, inicio.getDayOfMonth());

        Calendar c2 = new GregorianCalendar(fim.getYear(),
                fim.getMonth()-1, fim.getDayOfMonth());

        int week_inicio = c1.get(Calendar.WEEK_OF_YEAR);
        int week_fim = c2.get(Calendar.WEEK_OF_YEAR);

        boolean retorno = false;
        if (week_inicio == week_fim) {
            retorno = true;
        }
        if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR)) {
            int diaSemana_inicio = Utils.diaDaSemana(c1.get(Calendar.DAY_OF_MONTH), c1.get(Calendar.MONTH), c1.get(Calendar.YEAR));
            int diaSemana_fim = Utils.diaDaSemana(c2.get(Calendar.DAY_OF_MONTH), c2.get(Calendar.MONTH), c2.get(Calendar.YEAR));
            for (int i = diaSemana_inicio; i < Calendar.SATURDAY; i++) {
                if (diaSemana_fim == i) {
                    Calendar c3 = new GregorianCalendar(inicio.getYear(),inicio.getMonth(),inicio.getDayOfMonth()+i);
                    if(c2.equals(c3)){
                        retorno = true;
                    }
                }

            }
        }
        return retorno;
    }


 public static boolean mesmoMes(Day inicio, Day fim){
     int monthInicio  = inicio.getMonth();
     int monthFim  = fim.getMonth();
     int yearInicio  = inicio.getYear();
     int yearFim  = fim.getYear();

     boolean retorno = false;
     if(monthFim == monthInicio){
         retorno =  true;
     }

     if(retorno && yearInicio != yearFim){
         retorno =  false;
     }
     

     return retorno;

 }
  // ...
/*
  public static java.sql.Timestamp geraTimestamp(java.util.Date d) {
    return (d != null) ? new java.sql.Timestamp(d.getTime()) : null;
  }
*/
}
