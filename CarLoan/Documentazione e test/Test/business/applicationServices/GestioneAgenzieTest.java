package business.applicationServices;


import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.entitiesTO.ExtraAgenziaTO;

public class GestioneAgenzieTest {

    GestioneAgenzie test;

    @Before
    public void setUp() throws Exception {
        test = new GestioneAgenzie();
    }

    @After
    public void tearDown() throws Exception {
        test = null;
    }

    public void testInserireAgenzia() {
        AgenziaTO agenzia = new AgenziaTO();
        AgenziaTO agenzia1 = new AgenziaTO();
        Boolean result = null;

        Boolean[] results = {true, false};

        agenzia.città = "Torino";
        agenzia.indirizzo = "Via Roma,55";
        agenzia.telefono = "052316587";

        agenzia1.città = "Torino";
        agenzia1.indirizzo = "Via Roma,55";
        agenzia1.telefono = "052316587";

        AgenziaTO[] agenzie = {agenzia, agenzia1};

        for (int i = 0; i < agenzie.length; i++) {
            result = test.inserireAgenzia(agenzie[i]);
            assertTrue("Caso di test testInserireAgenzia"+ i, result.equals(results[i]));
        }
    }

    public void testModificareDatiAgenzia() {
        AgenziaTO agenzia = new AgenziaTO();
        AgenziaTO agenzia1 = new AgenziaTO();
        AgenziaTO agenzia2 = new AgenziaTO();
        AgenziaTO agenzia3 = new AgenziaTO();

        Boolean[] results = {false, false, false, true};

        agenzia.città = "Bari";
        agenzia.indirizzo = "Via Amendola,45";
        agenzia.telefono = "080336548";
        agenzia.id = "1";

        agenzia1.città = "Torino";
        agenzia1.indirizzo = "Via Grumo,44";
        agenzia1.telefono = "052316587";
        agenzia.id = "1";

        agenzia2.città = "Bari";
        agenzia2.indirizzo = "Via Amendola,45";
        agenzia2.telefono = "080336548";
        agenzia2.id = "8";

        agenzia3.città = "Torino";
        agenzia3.indirizzo = "Via Roma,45";
        agenzia3.telefono = "08082399";
        agenzia3.id = "7";

        AgenziaTO[] agenzie = {agenzia, agenzia1, agenzia2, agenzia3};

        for (int i = 0; i < agenzie.length; i++) {
            Boolean result = test.modificareDatiAgenzia(agenzie[i]);
            System.out.println(result);
            assertTrue("Caso di test testModificareDatiAgenzia: " + i,
                    result.equals(results[i]));
        }

    }

    public void testInserireExtraAgenzia() {
        ExtraAgenziaTO extra1 = new ExtraAgenziaTO();
        ExtraAgenziaTO extra2 = new ExtraAgenziaTO();
        extra1.id = "7";
        extra1.agenziaId = "5";

        extra2.id = "7";
        extra2.agenziaId = "5";

        ExtraAgenziaTO extras[] = {extra1, extra2};
        Boolean results[] = {true, false};

        for (int i = 0; i < extras.length; i++) {
            Boolean result = test.inserireExtraAgenzia(extras[i]);
            assertTrue("Caso di test testInserireExtraAgenzia: " + i,
                    result.equals(results[i]));
        }

    }

    public void testEliminareAgenzia() {
        String[] idAgenzia = {"7", "1", "6"};
        Boolean[] results = {true, false, false};

        for (int i = 0; i < idAgenzia.length; i++) {
            Boolean result = test.eliminareAgenzia(idAgenzia[i]);
            assertTrue("Caso di test testEliminareAgenzia: " + i, result.equals(results[i]));
        }


    }


    public void testRicercareAgenzieCittà() {
        String[] città = { "Torino", "Napoli"};

        AgenziaTO agenzia = new AgenziaTO("Torino", "Via Grumo,44", "080851545");
        AgenziaTO agenzia1 = new AgenziaTO("Torino", "Via Roma,45", "08082399");
        AgenziaTO[] agenzie = {agenzia, agenzia1, null};

        List<AgenziaTO> result = null;

        for (int i = 0; i < città.length; i++) {
            result = test.ricercareAgenzieCittà(città[i]);
            if (result == null) {
                assertNull("Caso di test testRicercareAgenzieCittà: " + i, agenzie[i+1]);
            } else {
                for (int j = 0; j < result.size(); j++) {
                    assertTrue("Caso di test testRicercareAgenzieCittà: "+ i, result.get(j).equals(agenzie[j]));
                }
            }
        }
    }


    @Test
    public void testRiepilogoAgenzie() {
        AgenziaTO agenzia = new AgenziaTO();
        agenzia.città = "Bari";
        agenzia.indirizzo = "Via Amendola,44";
        agenzia.telefono = "080336548";

        AgenziaTO agenzia1 = new AgenziaTO();
        agenzia1.città = "Torino";
        agenzia1.indirizzo = "Via Grumo,44";
        agenzia1.telefono = "080851545";

        AgenziaTO agenzia2 = new AgenziaTO();
        agenzia2.città = "Milano";
        agenzia2.indirizzo = "Via Torino,55";
        agenzia2.telefono = "0808915187";

        AgenziaTO agenzia3 = new AgenziaTO();
        agenzia3.città = "Torino";
        agenzia3.indirizzo = "Via Roma,45";
        agenzia3.telefono = "08082399";

        AgenziaTO agenzie[] = {agenzia, agenzia1, agenzia2, agenzia3};

        List<AgenziaTO> result = test.riepilogoAgenzie();

        for (int i = 0; i < agenzie.length; i++) {
            assertTrue("Caso di test per testRiepilogoAgenzie: "+i,
                    agenzie[i].equals(result.get(i)));
        }

    }

}
