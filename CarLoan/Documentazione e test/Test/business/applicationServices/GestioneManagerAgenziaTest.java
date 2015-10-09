package business.applicationServices;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import transferObjects.entitiesTO.ManagerAgenziaTO;

public class GestioneManagerAgenziaTest {

    private GestioneManagerAgenzia test;

    @Before
    public void setUp() throws Exception {
        test = new GestioneManagerAgenzia();
    }

    @After
    public void tearDown() throws Exception {
        test = null;
    }

    @Test
    public void testInserireManagerAgenzia() {
        ManagerAgenziaTO manager = new ManagerAgenziaTO();

        manager.username = "sillettig";
        manager.password = "prova";
        manager.agenziaId = "5";
        manager.nome = "Giuseppe";
        manager.cognome = "Silletti";

        ManagerAgenziaTO managers[] = {manager, manager};
        Boolean results[] = {true, false};

        for (int i = 0; i < managers.length; i++) {
            Boolean result = test.inserireManagerAgenzia(managers[i]);
            assertTrue("Caso di studio per testInserireManagerAgenzia: "+i,
                    result.equals(results[i]));
        }

    }

    @Test
    public void testRicercareManagerByNomeCognome() {
        String nomeCognome[][] = {{"Filippo", "Rossi"},{"Giuseppe", "Romanazzi"}};

        ManagerAgenziaTO manager = new ManagerAgenziaTO();

        manager.username = "manager";
        manager.agenziaId = "3";
        manager.nome = "Giuseppe";
        manager.cognome = "Romanazzi";

        ManagerAgenziaTO results[] = {null, manager};

        for (int i = 0; i < nomeCognome.length; i++) {
            List<ManagerAgenziaTO> result =
                    test.ricercareManagerByNomeCognome(
                            nomeCognome[i][0], nomeCognome[i][1]);

            if (result == null) {
                assertNull("Caso di test per testRicercareManagerByNomeCognome: "+i,
                    results[i]);
            } else {
                assertTrue("Caso di test per testRicercareManagerByNomeCognome: "+i,
                    result.get(0).equals(results[i]));
            }
        }
    }

    @Test
    public void testRiepilogoManagerAgenzia() {
        ManagerAgenziaTO manager = new ManagerAgenziaTO();

        manager.username = "sillettig";
        manager.agenziaId = "5";
        manager.nome = "Giuseppe";
        manager.cognome = "Silletti";

        ManagerAgenziaTO manager1 = new ManagerAgenziaTO();

        manager1.username = "manager";
        manager1.agenziaId = "3";
        manager1.nome = "Giuseppe";
        manager1.cognome = "Romanazzi";

        ManagerAgenziaTO results[] = {manager1, manager};

        List<ManagerAgenziaTO> result = test.riepilogoManagerAgenzia();

        for (int i = 0; i < results.length; i++) {
            assertTrue("Caso di test per testRiepilogoManagerAgenzia: "+i,
                    result.get(i).equals(results[i]));
        }
    }

    @Test
    public void testModificareDatiManagerAgenzia() {
        ManagerAgenziaTO manager = new ManagerAgenziaTO();

        manager.username = "sillettig";
        manager.agenziaId = "5";
        manager.nome = "Giuseppe";
        manager.cognome = "Spinelli";
        manager.id = "sillettig";

        ManagerAgenziaTO manager1 = new ManagerAgenziaTO();

        manager1.username = "manager";
        manager1.agenziaId = "5";
        manager1.nome = "Giuseppe";
        manager1.cognome = "Spinelli";
        manager.id="sillettig";

        ManagerAgenziaTO manager2 = new ManagerAgenziaTO();

        manager2.username = "aldo";
        manager2.agenziaId = "5";
        manager2.nome = "Giuseppe";
        manager2.cognome = "Spinelli";
        manager2.id = "aldo";

        ManagerAgenziaTO managers[] = {manager, manager1, manager2};
        Boolean results[] = {true, false, false};

        for (int i = 0; i < managers.length; i++) {
            Boolean result = test.modificareDatiManagerAgenzia(managers[i]);
            assertTrue("Caso di studio per testModificareDatiManagerAgenzia: "+i,
                    result.equals(results[i]));
        }

    }

    @Test
    public void testEliminareManagerAgenzia() {
        String username[] = {"sillettig", "topolino"};
        Boolean results[] = {true, false};

        for (int i = 0; i < username.length; i++) {
            Boolean result = test.eliminareManagerAgenzia(username[i]);
            assertTrue("Caso di studio per testEliminareManagerAgenzia: "+i,
                    result.equals(results[i]));
        }
    }

}
