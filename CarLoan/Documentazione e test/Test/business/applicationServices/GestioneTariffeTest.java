package business.applicationServices;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;

import transferObjects.entitiesTO.TariffaTO;

public class GestioneTariffeTest {

    private GestioneTariffe test;

    @Before
    public void setUp() throws Exception {
        test = new GestioneTariffe();
    }

    @After
    public void tearDown() throws Exception {
        test = null;
    }

    public void testInserireTariffa() {
        TariffaTO tariffa = new TariffaTO();

        tariffa.chilometraggio = "Limitato";
        tariffa.chilometriGiorno = (float) 12.33;
        tariffa.classeAutovetturaId = "33";
        tariffa.importoUnitario = (float) 50.33;
        tariffa.modalità = "Settimanale";

        Boolean result = test.inserireTariffa(tariffa);

        assertTrue("Caso di test per testInserireTariffa: "+0,
                result.equals(true));
    }


    public void testModificareDatiTariffa() {
        TariffaTO tariffa = new TariffaTO();

        tariffa.chilometraggio = "Limitato";
        tariffa.chilometriGiorno = (float) 13.36;
        tariffa.classeAutovetturaId = "33";
        tariffa.importoUnitario = (float) 50.33;
        tariffa.modalità = "Settimanale";
        tariffa.id = "10";

        TariffaTO tariffa1 = new TariffaTO();

        tariffa1.chilometraggio = "Limitato";
        tariffa1.chilometriGiorno = (float) 13.36;
        tariffa1.classeAutovetturaId = "33";
        tariffa1.importoUnitario = (float) 50.33;
        tariffa1.modalità = "Settimanale";
        tariffa1.id = "100";

        TariffaTO tariffa2 = new TariffaTO();

        tariffa2.chilometraggio = "Illimitato";
        tariffa2.chilometriGiorno = (float) 0.0;
        tariffa2.classeAutovetturaId = "29";
        tariffa2.importoUnitario = (float) 20.00;
        tariffa2.modalità = "Giornaliera";
        tariffa2.id = "6";

        Boolean results[] = {true, false, false};

        TariffaTO tariffe[] = {tariffa, tariffa1, tariffa2};

        for (int i = 0; i < tariffe.length; i++) {
            Boolean result = test.modificareDatiTariffa(tariffe[i]);
            assertTrue("Caso di test per testModificareDatiTariffa: "+i,
                    result.equals(results[i]));
        }

    }


    public void testRiepilogoTariffe() {
        TariffaTO tariffa = new TariffaTO();

        tariffa.chilometraggio = "Illimitato";
        tariffa.chilometriGiorno = (float) 0;
        tariffa.classeAutovetturaId = "28";
        tariffa.importoUnitario = (float) 20.33;
        tariffa.modalità = "Settimanale";

        TariffaTO tariffa1 = new TariffaTO();

        tariffa1.chilometraggio = "Limitato";
        tariffa1.chilometriGiorno = (float) 13.36;
        tariffa1.classeAutovetturaId = "33";
        tariffa1.importoUnitario = (float) 50.33;
        tariffa1.modalità = "Settimanale";

        TariffaTO tariffe[] = {tariffa, tariffa1};

        List<TariffaTO> results = test.riepilogoTariffe();

        for (int i = 0; i < tariffe.length; i++) {
            assertTrue("Caso di studio per testRiepilogoTariffe: "+i,
                    results.get(i).equals(tariffe[i]));
        }
    }


    public void testRicercareTariffaByClasse() {
        TariffaTO tariffa = new TariffaTO();

        tariffa.chilometraggio = "Illimitato";
        tariffa.chilometriGiorno = (float) 0;
        tariffa.classeAutovetturaId = "28";
        tariffa.importoUnitario = (float) 20.33;
        tariffa.modalità = "Settimanale";

        String classi[] = {"Occasionale", "Prova"};

        TariffaTO results[] = {tariffa, null};

        for (int i = 0; i < classi.length; i++) {
            List<TariffaTO> result = test.ricercareTariffaByClasse(classi[i]);

            if (result == null) {
                assertNull("Caso di test per testRicercareTariffaByClasse"+i,
                        results[i]);
            } else {
            assertTrue("Caso di test per testRicercareTariffaByClasse"+i,
                    result.get(0).equals(results[i]));
            }
        }

    }


    public void testEliminareTariffa() {

        String idTariffe[] = {"10", "6","100"};

        Boolean results[] = {true, false, false};

        for (int i = 0; i < idTariffe.length; i++) {
            Boolean result = test.eliminareTariffa(idTariffe[i]);
            assertTrue("Caso di test per testEliminareTariffa"+i,
                    result.equals(results[i]));
        }

    }

}
