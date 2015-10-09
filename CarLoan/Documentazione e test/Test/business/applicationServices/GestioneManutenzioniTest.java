package business.applicationServices;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import transferObjects.entitiesTO.ManutenzioneTO;

public class GestioneManutenzioniTest {

    private GestioneManutenzioni test;

    @Before
    public void setUp() throws Exception {
        test = new GestioneManutenzioni();
    }

    @After
    public void tearDown() throws Exception {
        test = null;
    }

    public void testInserireManutenzione() {
        ManutenzioneTO manutenzione = new ManutenzioneTO();

        manutenzione.dataInizio = "12/08/2015";
        manutenzione.tipo = "Ordinaria";
        manutenzione.autovetturaId = "5";

        Boolean results[] = {true, false};

        for (int i = 0; i < results.length; i++) {
            Boolean result = test.inserireManutenzione(manutenzione);
            assertTrue("Caso di test per testInserireManutenzione: "+i,
                    result.equals(results[i]));
        }
    }


    public void testModificareDatiManutenzione() {
        ManutenzioneTO manutenzione = new ManutenzioneTO();

        manutenzione.dataInizio = "12/08/2015";
        manutenzione.tipo = "Ordinaria";
        manutenzione.autovetturaId = "5";
        manutenzione.dataFine ="13/08/2015";
        manutenzione.id = "4";

        ManutenzioneTO manutenzione1 = new ManutenzioneTO();

        manutenzione1.dataInizio = "12/08/2015";
        manutenzione1.tipo = "Ordinaria";
        manutenzione1.autovetturaId = "5";
        manutenzione1.dataFine ="13/08/2015";
        manutenzione1.id = "500";

        ManutenzioneTO manutenzione2 = new ManutenzioneTO();

        manutenzione2.dataInizio = "12/08/2015";
        manutenzione2.tipo = "Ordinaria";
        manutenzione2.autovetturaId = "5";
        manutenzione2.dataFine ="06/08/2015";
        manutenzione2.id = "4";

        ManutenzioneTO manutenzioni[] = {manutenzione, manutenzione1, manutenzione2};

        Boolean results[] = {true, false, false};

        for (int i = 0; i < manutenzioni.length; i++) {
            Boolean result = test.modificareDatiManutenzione(manutenzioni[i]);
            assertTrue("Caso di test testModificareDatiManutenzione: "+i,
                    result.equals(results[i]));
        }

    }

    public void testRicercaManutenzioniAutovettura() {
        ManutenzioneTO manutenzione = new ManutenzioneTO();

        manutenzione.dataInizio = "2015-08-12";
        manutenzione.tipo = "Ordinaria";
        manutenzione.autovetturaId = "5";
        manutenzione.dataFine ="2015-08-13";

        ManutenzioneTO manutenzioni[] = {manutenzione, null};

        String targhe[] = {"XZD56", "AAAA"};

        for (int i = 0; i < targhe.length; i++) {
            List<ManutenzioneTO> result = test.ricercaManutenzioniAutovettura(targhe[i]);
            if (result == null) {
                assertNull("Caso di test per testRicercaManutenzioneAutovetture: "+i,
                        (manutenzioni[i]));
            } else {
                assertTrue("Caso di test per testRicercaManutenzioneAutovetture: "+i,
                        result.get(0).equals(manutenzioni[i]));
            }
        }
    }

    @Test
    public void testRiepilogoManutenzioni() {
        ManutenzioneTO manutenzione = new ManutenzioneTO();

        manutenzione.dataInizio = "2015-08-12";
        manutenzione.tipo = "Ordinaria";
        manutenzione.autovetturaId = "5";
        manutenzione.dataFine ="2015-08-13";

        ManutenzioneTO manutenzione1 = new ManutenzioneTO();

        manutenzione1.dataInizio = "2015-09-15";
        manutenzione1.tipo = "Ordinaria";
        manutenzione1.autovetturaId = "7";

        ManutenzioneTO manutenzioni[] = {manutenzione1, manutenzione};

        List<ManutenzioneTO> result = test.riepilogoManutenzioni();

        for (int j = 0; j < manutenzioni.length; j++) {
            assertTrue("Caso di test per testRicercaManutenzioneAutovetture: "+j,
                    result.get(j).equals(manutenzioni[j]));
        }

    }


}
