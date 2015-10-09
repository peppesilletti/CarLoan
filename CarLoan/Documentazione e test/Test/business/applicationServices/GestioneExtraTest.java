package business.applicationServices;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import transferObjects.entitiesTO.ExtraTO;

public class GestioneExtraTest {

    private GestioneExtra test;

    @Before
    public void setUp() throws Exception {
        test = new GestioneExtra();
    }

    @After
    public void tearDown() throws Exception {
        test = null;
    }


    public void testInserireExtra() {
         ExtraTO extra1 = new ExtraTO();

         extra1.nome = "GPS";
         extra1.prezzo = (float) 50;
         extra1.descrizione = "Gps con mappe e voce";

         Boolean results[] = {true, false};

         for (int i = 0; i < results.length; i++) {
            Boolean result = test.inserireExtra(extra1);
            assertTrue("Caso di test per testInserireExtra: "+i,
                    result.equals(results[i]));
        }
    }

    public void testModificaDatiExtra() {
        ExtraTO extra1 = new ExtraTO();

        extra1.nome = "GPS";
        extra1.prezzo = (float) 60;
        extra1.descrizione = "Gps con mappe e voce";
        extra1.id = "9";

        ExtraTO extra2 = new ExtraTO();

        extra2.nome = "GPS";
        extra2.prezzo = (float) 50;
        extra2.descrizione = "Gps con mappe e voce";
        extra2.id = "100";

        ExtraTO extra3 = new ExtraTO();

        extra3.nome = "GPS avanzato";
        extra3.prezzo = (float) 50;
        extra3.descrizione = "Gps con mappe e voce";
        extra3.id = "9";

        ExtraTO extra4 = new ExtraTO();

        extra4.nome = "GPS avanzato";
        extra4.prezzo = (float) 50;
        extra4.descrizione = "Gps con mappe e voce";
        extra4.id = "7";

        ExtraTO extra5 = new ExtraTO();

        extra5.nome = "GPS avanzato";
        extra5.prezzo = (float) 50;
        extra5.descrizione = "Gps con mappe e voce";
        extra5.id = "6";

        ExtraTO extras[] = {extra1, extra2, extra3, extra4, extra5};

        Boolean results[] = {true, false, true, false, false};

        for (int i = 0; i < extras.length; i++) {
            Boolean result = test.modificareDatiExtra(extras[i]);

            assertTrue("Caso di test per testModificaDatiExtra: "+i,
                    result.equals(results[i]));
        }

    }

    @Test
    public void testEliminareExtra() {
        String idTariffe[] = {"9", "100", "6"};
        Boolean results[] = {true, false, false};

        for (int i = 0; i < idTariffe.length; i++) {
            Boolean result = test.eliminareExtra(idTariffe[i]);
            assertTrue("Caso di test per testEliminareExtra: "+i,
                    result.equals(results[i]));
        }
    }

}
