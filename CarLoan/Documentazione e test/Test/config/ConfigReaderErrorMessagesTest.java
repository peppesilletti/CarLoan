package config;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConfigReaderErrorMessagesTest {

    private ConfigReaderErrorMessages test;

    @Before
    public void setUp() throws Exception {
        test = ConfigReaderErrorMessages.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        test = null;
    }

    @Test
    public void testGetProperty() {
         String property[] = {"errorsHandling.exceptions.DAOException", "prova",null};

         String results[] = {"Si è verificato un errore durante di recupero dati. \n Contattare l'amministratore", null, null};

         for (int i = 0; i < results.length; i++) {
            String result = test.getProperty(property[i]);
            assertTrue("Caso di test per testGetProperty: "+i,
                    result.equals(results[i]));
        }

    }

}
