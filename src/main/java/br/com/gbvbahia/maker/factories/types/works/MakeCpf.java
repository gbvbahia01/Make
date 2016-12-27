package br.com.gbvbahia.maker.factories.types.works;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.managers.Notification;
import br.com.gbvbahia.maker.factories.types.managers.NotifierStage;
import br.com.gbvbahia.maker.factories.types.works.commons.ValueSpecializedFactory;
import br.com.gbvbahia.maker.log.LogInfo;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.swing.text.MaskFormatter;

/**
 * Cria uma String que passa no teste de validacao de CPF, nove caracteres mais dois digitos
 * verificadores.<br>
 * Para que funcione deve ter o valor isCPF no arquivo make.xml<br>
 * ATENCAO: Criado para facilitar testes de desenvolvimento de software.<br>
 * <br>
 *
 * <b>MakeCpf</b><br>
 * tag: isCPF<br>
 * Example: isCPF<br>
 * This specialized factory work with a type of number that exist in Brazil.<br>
 * Works only with String.<br>
 *
 * @since v.1 09/06/2012
 * @author Guilherme
 */
public class MakeCpf implements ValueSpecializedFactory {

    /**
     * Key for this specialized factory.
     */
    public static final String KEY_PROPERTY = "isCPF";
    private static GeraCPF cpfMake = new GeraCPF();
    /**
     * Cannot be instantiated outside.
     */
    private MakeCpf() {
    }

    @Override
    public <T> void makeValue(Field field, T entity, String... testName)
            throws IllegalAccessException, IllegalArgumentException {
        field.set(entity, getCpf());
    }

    @Override
    public boolean workValue(String fieldName, String value) {
        LogInfo.logDebugInformation("MakeCPF", I18N.getMsg("workValueMake", value));
        if (KEY_PROPERTY.equals(StringUtils.trim(value))) {
            return true;
        }
        LogInfo.logDebugInformation("MakeCPF", I18N.getMsg("notIsWork", "CPF", value));
        return false;
    }

    @Override
    public <T> boolean isWorkWith(Field field, T entity) {
        return field.getType().equals(String.class);
    }

    /**
     * Observer to warn about the test stage.
     */
    @Override
    public void updateStage(Notification notification) {
        if (notification.isCreationFinished()) {
            instance = null;
        }
    }

    /**
     * Retorna uma string no formato de um CPF valido, em relação a validacao do digito verificador.
     * Retorna nove caracteres mais dois digitos verificadores, totalizando onze caracteres. Nao
     * retorna formatado, somente numeros, pontos e ifen são excluidos. <br>
     * ATENCAO: Criado para facilitar testes de desenvolvimento de software.
     *
     * @return String no formato de um CPF valido.
     */
    public static String getCpf() {
        return cpfMake.geraCPFFinal();
    }

    // ==============
    // Static control
    // ==============
    private static ValueSpecializedFactory instance = null;
        /**
     * Get a instance for this class encapsulated by ValueSpecializedFactory.
     *
     * @return a instance for MakeCpf class encapsulated by ValueSpecializedFactory.
     */
    public static synchronized ValueSpecializedFactory getInstance() {
        if (instance == null) {
            instance = new MakeCpf();
            NotifierStage.getNotifyer().addObserver(instance);
        }
        return instance;
    }

    /**
     * Thanks for: https://github.com/rauleite/Gerador-de-CPF/blob/master/GeradorCPF/src/GeraCPF.java
     */
    private static class GeraCPF {

        private ArrayList<Integer> listaAleatoria = new ArrayList<Integer>();
        private ArrayList<Integer> listaNumMultiplicados = null;

        public int geraNumAleatorio() {
            int numero = (int) (Math.random() * 10);
            return numero;
        }

        public ArrayList<Integer> geraCPFParcial() {
            for (int i = 0; i < 9; i++) {
                listaAleatoria.add(geraNumAleatorio());
            }
            return listaAleatoria;
        }

        public ArrayList<Integer> geraPrimeiroDigito() {
            listaNumMultiplicados = new ArrayList<Integer>();
            int primeiroDigito;
            int totalSomatoria = 0;
            int restoDivisao;
            int peso = 10;
            for (int item : listaAleatoria) {
                listaNumMultiplicados.add(item * peso);
                peso--;
            }
            for (int item : listaNumMultiplicados) {
                totalSomatoria += item;
            }
            restoDivisao = (totalSomatoria % 11);
            if (restoDivisao < 2) {
                primeiroDigito = 0;
            } else {
                primeiroDigito = 11 - restoDivisao;
            }
            listaAleatoria.add(primeiroDigito);
            return listaAleatoria;
        }

        public ArrayList<Integer> geraSegundoDigito() {
            listaNumMultiplicados = new ArrayList<Integer>();
            int segundoDigito;
            int totalSomatoria = 0;
            int restoDivisao;
            int peso = 11;
            for (int item : listaAleatoria) {
                listaNumMultiplicados.add(item * peso);
                peso--;
            }
            for (int item : listaNumMultiplicados) {
                totalSomatoria += item;
            }
            restoDivisao = (totalSomatoria % 11);
            if (restoDivisao < 2) {
                segundoDigito = 0;
            } else {
                segundoDigito = 11 - restoDivisao;
            }
            listaAleatoria.add(segundoDigito);
            return listaAleatoria;
        }

        public String geraCPFFinal() {
            geraCPFParcial();
            geraPrimeiroDigito();
            geraSegundoDigito();
            String cpf = "";
            String texto = "";
            for (int item : listaAleatoria) {
                texto += item;
            }
            try {
                MaskFormatter mf = new MaskFormatter("###.###.###-##");
                mf.setValueContainsLiteralCharacters(false);
                cpf = mf.valueToString(texto);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return cpf;
        }
    }
}
