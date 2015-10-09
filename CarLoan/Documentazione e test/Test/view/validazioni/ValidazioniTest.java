package view.validazioni;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

public class ValidazioniTest {

    private Validazione test;

    @After
    public void tearDown() throws Exception {
        test = null;
    }

    @Test
    public void test() {
         TipoValidazione v[] = {
                 TipoValidazione.CODICE_FISCALE,
                 TipoValidazione.COGNOME,
                 TipoValidazione.DATA,
                 TipoValidazione.FLOAT,
                 TipoValidazione.INTERO,
                 TipoValidazione.INDIRIZZO,
                 TipoValidazione.NOME,
                 TipoValidazione.PASSWORD,
                 TipoValidazione.TARGA,
                 TipoValidazione.TELEFONO,
                 TipoValidazione.USERNAME,
         };

         String codFiscale[] = {"FLPVRD85T10A562L", "F89D85T10A562L"};
         String cognome[] = {"Silletti", "@Silletti"};
         String date[] = {"12/12/2012", "123/12/2012"};
         String fl[] = {"20.32", "stringa"};
         String in[] = {"2", "stringa"};
         String indirizzo[] = {"Via Amendola,44", "Via Percoco 5"};
         String nome[] = {"Giuseppe", "@Giuseppe"};
         String password[] = {"lunaPark", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"};
         String targa[] = {"AA523", "a"};
         String telefono[] = {"08089152365","stringa"};
         String username[] = {"sillex", "@ciao"};

         String prove[][] = {codFiscale, cognome, date,
                 fl, in, indirizzo, nome, password, targa, telefono,
                 username};

         Boolean results[] = {true, false};

         for (int i = 0; i < v.length; i++) {
             Validazione va = ValidazioneFactory.getValidazione(v[i]);
             for (int j = 0; j < results.length; j++) {
                 Boolean result = va.valida(prove[i][j]);
                 assertTrue("Caso di test per "+v[i].toString(),
                         result.equals(results[j]));
             }
         }

    }

}
