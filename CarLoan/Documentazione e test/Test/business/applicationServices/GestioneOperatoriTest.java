package business.applicationServices;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import transferObjects.entitiesTO.OperatoreTO;

public class GestioneOperatoriTest {

    private GestioneOperatori test;

    @Before
    public void setUp() throws Exception {
        test = new GestioneOperatori();
    }

    @After
    public void tearDown() throws Exception {
        test = null;
    }

    @Test
    public void testInserireOperatore() {
        OperatoreTO operatore = new OperatoreTO();

        operatore.username = "spinelliv";
        operatore.password = "prova";
        operatore.agenziaId = "5";
        operatore.nome = "Vincenzo";
        operatore.cognome = "Spinelli";

        OperatoreTO operatori[] = {operatore, operatore};
        Boolean results[] = {true, false};

        for (int i = 0; i < operatori.length; i++) {
            Boolean result = test.inserireAccountOperatore(operatori[i]);
            assertTrue("Caso di studio per testInserireOperatore: "+i,
                    result.equals(results[i]));
        }

    }


    public void testRicercareOperatoreNomeCognome() {
        String nomeCognome[][] = {{"Mario", "Rossi"},{"Filippo", "Rossi"}};

        OperatoreTO operatore = new OperatoreTO();

        operatore.username = "ope";
        operatore.agenziaId = "1";
        operatore.nome = "Filippo";
        operatore.cognome = "Rossi";

        OperatoreTO results[] = {null, operatore};

        for (int i = 0; i < nomeCognome.length; i++) {
            List<OperatoreTO> result =
                    test.ricercaOperatoreNomeCognome(
                            nomeCognome[i][0], nomeCognome[i][1]);

            if (result == null) {
                assertNull("Caso di test per testRicercareOperatoreNomeCognome: "+i,
                    results[i]);
            } else {
                assertTrue("Caso di test per testRicercareOperatoreNomeCognome: "+i,
                    result.get(0).equals(results[i]));
            }
        }
    }


    public void testRiepilogoOperatori() {
        OperatoreTO operatore = new OperatoreTO();

        operatore.username = "spinelliv";
        operatore.agenziaId = "5";
        operatore.nome = "Vincenzo";
        operatore.cognome = "Spinelli";

        OperatoreTO operatore1 = new OperatoreTO();

        operatore1.username = "ope";
        operatore1.agenziaId = "1";
        operatore1.nome = "Filippo";
        operatore1.cognome = "Rossi";

        OperatoreTO results[] = {operatore1, operatore};

        List<OperatoreTO> result = test.riepilogoOperatori();

        for (int i = 0; i < results.length; i++) {
            assertTrue("Caso di test per testRiepilogoOperatori: "+i,
                    result.get(i).equals(results[i]));
        }
    }

    @Test
    public void testModificareDatiAccountOperatore() {
        OperatoreTO operatore = new OperatoreTO();

        operatore.username = "spinelliv";
        operatore.agenziaId = "5";
        operatore.nome = "Vincenzo";
        operatore.cognome = "Silletti";
        operatore.id = "spinelliv";

        OperatoreTO operatore1 = new OperatoreTO();

        operatore1.username = "ope";
        operatore1.agenziaId = "5";
        operatore1.nome = "Giuseppe";
        operatore1.cognome = "Spinelli";
        operatore.id="spinelliv";

        OperatoreTO operatore2 = new OperatoreTO();

        operatore2.username = "aldo";
        operatore2.agenziaId = "5";
        operatore2.nome = "Giuseppe";
        operatore2.cognome = "Spinelli";
        operatore2.id = "aldo";

        OperatoreTO operatores[] = {operatore, operatore1, operatore2};
        Boolean results[] = {true, false, false};

        for (int i = 0; i < operatores.length; i++) {
            Boolean result = test.modificareDatiAccountOperatore(operatores[i]);
            assertTrue("Caso di studio per testModificareDatiAccountOperatore: "+i,
                    result.equals(results[i]));
        }

    }


    public void testEliminareAccountOperatore() {
        String username[] = {"spinelliv", "topolino"};
        Boolean results[] = {true, false};

        for (int i = 0; i < username.length; i++) {
            Boolean result = test.eliminareAccountOperatore(username[i]);
            assertTrue("Caso di studio per testEliminareAccountOperatore: "+i,
                    result.equals(results[i]));
        }
    }

}
