package business.applicationServices;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import transferObjects.entitiesTO.AutovetturaTO;

public class GestioneAutovettureTest {

    GestioneAutovetture test;

    @Before
    public void setUp() throws Exception {
        test = new GestioneAutovetture();
    }

    @After
    public void tearDown() throws Exception {
        test = null;
    }

    public void testInserireAutovettura() {
        AutovetturaTO auto1 = new AutovetturaTO();

        auto1.agenzia="7";
        auto1.alimentazione = "Metano";
        auto1.chilometraggio = (float) 112365.32;
        auto1.classeAutovetturaId = "33";
        auto1.marca = "Seat";
        auto1.modello = "Ibiza";
        auto1.targa = "ABCD123";

        AutovetturaTO auto[] = {auto1, auto1};
        Boolean results[] = {true, false};

        for (int i = 0; i < auto.length; i++) {
            Boolean result = test.inserireAutovettura(auto[i]);
            assertTrue("Caso di test testInserireAutovettura: " + i,
                    result.equals(results[i]));
        }

    }

    public void testEliminareAutovettura() {
        String idAuto[] = {"9", "50", "5"};

        Boolean results[] = {true, false, false};

        for (int i = 0; i < idAuto.length; i++) {
            Boolean result = test.eliminareAutovettura(idAuto[i]);
            assertTrue("Caso di test testEliminareAutovettura: " + i,
                    result.equals(results[i]));
        }
    }

    public void testModificareAutovettura() {
        AutovetturaTO auto1 = new AutovetturaTO();
        AutovetturaTO auto2 = new AutovetturaTO();
        AutovetturaTO auto3 = new AutovetturaTO();

        auto1.agenzia="7";
        auto1.alimentazione = "Metano";
        auto1.chilometraggio = (float) 112365.32;
        auto1.classeAutovetturaId = "33";
        auto1.marca = "Seat";
        auto1.modello = "Ibiza";
        auto1.targa = "ABCD123";
        auto1.id = "50";

        auto2.agenzia="7";
        auto2.alimentazione = "Metano";
        auto2.chilometraggio = (float) 112365.32;
        auto2.classeAutovetturaId = "33";
        auto2.marca = "Renault";
        auto2.modello = "Clio";
        auto2.targa = "ABCD123";
        auto2.id = "9";

        auto3.agenzia="7";
        auto3.alimentazione = "Gas";
        auto3.chilometraggio = (float) 112365.32;
        auto3.classeAutovetturaId = "33";
        auto3.marca = "Seat";
        auto3.modello = "Ibiza";
        auto3.targa = "ABCD123";
        auto3.id = "5";

        AutovetturaTO auto[] = {auto1, auto2, auto3};
        Boolean results[] = {false, true, false};

        for (int i = 0; i < auto.length; i++) {
            Boolean result = test.modificareDatiAutovettura(auto[i]);
            assertTrue("Caso di test testModificareAutovettura: " + i,
                    result.equals(results[i]));
        }

    }

    @Test
    public void testRicercareAutovettureParams() {
        AutovetturaTO auto1 = new AutovetturaTO();
        auto1.alimentazione = "Metano";
        auto1.chilometraggio = (float) 50.34;
        auto1.classeAutovetturaId = "28";
        auto1.marca = "Mercedes";
        auto1.modello = "Benz";
        auto1.targa = "XZD56";

        String params[][] = {{"XZD56", "", ""}, {"mercedes", "", ""},
                {"occasionale", "", ""}, {"XZD56", "mercedes", ""},
                {"", "mercedes", "occasionale"}, {"ABC456", "", ""},
                {"ferrari", "", ""}, {"spaziosa", "", ""}};

        AutovetturaTO auto[] = {auto1, auto1, auto1, auto1, auto1, null, null, null};

        for (int i = 0; i < auto.length; i++) {
            List<AutovetturaTO> a = test.ricercareAutovetturaParams(params[i][0],
                    params[i][1], params[i][2]);
            if (a == null) {
                assertNull("Caso di test per testRicercareAutovetturaParams: "+i,
                    a);
            } else {
                assertTrue("Caso di test per testRicercareAutovetturaParams: "+i,
                    a.get(0).equals(auto[i]));
            }
        }

    }

}
