package config;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConfigReaderPenaliTest {

    private ConfigReaderPenali test;

    @Before
    public void setUp() throws Exception {
        test = ConfigReaderPenali.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        test = null;
    }

    @Test
    public void testGetProperty() {
         String property[] = {"penale_chilometri_superati", "prova",null};

         String results[] = {"5", null, null};

         for (int i = 0; i < results.length; i++) {
            String result = test.getProperty(property[i]);
            assertTrue("Caso di test per testGetProperty: "+i,
                    result.equals(results[i]));
        }

    }

}
