package business.applicationServices;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;

import transferObjects.entitiesTO.ClasseAutovetturaTO;

public class GestioneClassiAutovettureTest {
    private GestioneClassiAutovetture test;

    @Before
    public void setUp() throws Exception {
        test = new GestioneClassiAutovetture();
    }

    @After
    public void tearDown() throws Exception {
        test = null;
    }


    public void testInserireClasseAutovettura() {
        ClasseAutovetturaTO classe1 = new ClasseAutovetturaTO();

        classe1.nome="Lusso";
        classe1.ariaCondizionata=1;
        classe1.tipoCambio="Manuale";
        classe1.numeroPorte=4;
        classe1.numeroPosti=4;

        Boolean results[] = {true, false};
        ClasseAutovetturaTO classi[] = {classe1, classe1};

        for (int i = 0; i < classi.length; i++) {
            Boolean result = test.inserireClasseAutovettura(classi[i]);
            assertTrue("Caso di test per testInserireClasseAutovettura: "+i,
                    result.equals(results[i]));
        }

    }


    public void testModificareDatiClasseAutovettura() {
        ClasseAutovetturaTO classe1 = new ClasseAutovetturaTO();
        ClasseAutovetturaTO classe2 = new ClasseAutovetturaTO();
        ClasseAutovetturaTO classe3 = new ClasseAutovetturaTO();
        ClasseAutovetturaTO classe4 = new ClasseAutovetturaTO();

        classe1.nome="SuperLusso";
        classe1.ariaCondizionata=1;
        classe1.tipoCambio="Manuale";
        classe1.numeroPorte=4;
        classe1.numeroPosti=4;
        classe1.id = "40";

        classe2.nome="SuperLusso";
        classe2.ariaCondizionata=1;
        classe2.tipoCambio="Manuale";
        classe2.numeroPorte=4;
        classe2.numeroPosti=4;
        classe2.id = "101";

        classe3.nome="Economica";
        classe3.ariaCondizionata=1;
        classe3.tipoCambio="Manuale";
        classe3.numeroPorte=4;
        classe3.numeroPosti=4;
        classe3.id = "29";

        classe4.nome="Economica";
        classe4.ariaCondizionata=1;
        classe4.tipoCambio="Manuale";
        classe4.numeroPorte=4;
        classe4.numeroPosti=4;
        classe4.id = "40";

        ClasseAutovetturaTO classi[] = {classe1, classe2, classe3, classe4};
        Boolean results[] = {true, false, false, false};

        for (int i = 0; i < classi.length; i++) {
            Boolean result = test.modificareDatiClasseAutovettura(classi[i]);
            assertTrue("Caso di test per testModificareDatiClasseAutovettura"+i,
                    result.equals(results[i]));
        }

    }

    public void testEliminareClasseAutovettura() {
        String idClassi[] = {"40", "33", "100"};
        Boolean results[] = {true, false, false};

        for (int i = 0; i < results.length; i++) {
            Boolean result = test.eliminareClasseAutovettura(idClassi[i]);
            assertTrue("Caso di test per testEliminareClasseAutovettura: "+i,
                    result.equals(results[i]));
        }
    }


    public void testRiepilogoClassiAutovettura() {
        ClasseAutovetturaTO classe1 = new ClasseAutovetturaTO();
        ClasseAutovetturaTO classe2 = new ClasseAutovetturaTO();
        ClasseAutovetturaTO classe3 = new ClasseAutovetturaTO();

        classe1.nome="Occasionale";
        classe1.ariaCondizionata=1;
        classe1.tipoCambio="Automatico";
        classe1.numeroPorte=5;
        classe1.numeroPosti=4;
        classe1.id = "28";

        classe2.nome="Economica";
        classe2.ariaCondizionata=1;
        classe2.tipoCambio="Manuale";
        classe2.numeroPorte=5;
        classe2.numeroPosti=7;
        classe2.id = "29";

        classe3.nome="Sportiva";
        classe3.ariaCondizionata=0;
        classe3.tipoCambio="Manuale";
        classe3.numeroPorte=2;
        classe3.numeroPosti=2;
        classe3.id = "33";

        ClasseAutovetturaTO classi[] = {classe1, classe2, classe3};

        List<ClasseAutovetturaTO> results = test.riepilogoClassiAutovettura();

        for (int i = 0; i < classi.length; i++) {
            assertTrue("Caso di test per testRiepilogoClassiAutovettura"+i,
                    results.get(i).equals(classi[i]));
        }
    }
}
