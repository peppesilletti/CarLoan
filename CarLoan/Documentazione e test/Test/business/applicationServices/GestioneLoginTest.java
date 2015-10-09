package business.applicationServices;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import transferObjects.entitiesTO.AnonimoTO;

public class GestioneLoginTest {

    private GestioneLogin test;

    @Before
    public void setUp() throws Exception {
        test = new GestioneLogin();
    }

    @After
    public void tearDown() throws Exception {
        test = null;
    }

    @Test
    public void testAutenticazione() {
        AnonimoTO anonimo = new AnonimoTO();
        AnonimoTO anonimo1 = new AnonimoTO();

        anonimo.username = "manager";
        anonimo.password = "a";

        anonimo1.username = "prova";
        anonimo1.password = "aaa";

        AnonimoTO anonimi[] = {anonimo, anonimo1};

        Boolean results[] = {true, false};

        for (int i = 0; i < anonimi.length; i++) {
            Boolean result = test.autenticazione(anonimi[i]);
            assertTrue("Caso di test per testAutenticazione: "+i,
                    result.equals(results[i]));
        }


    }

}
