package business.applicationServices;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;

import transferObjects.entitiesTO.ClienteTO;

public class GestioneClientiTest {

    private GestioneClienti test;

    @Before
    public void setUp() throws Exception {
        test = new GestioneClienti();
    }

    @After
    public void tearDown() throws Exception {
        test = null;
    }

    public void testInserireCliente() {
        ClienteTO cliente = new ClienteTO();

        cliente.nome = "Mario";
        cliente.cognome = "Rossi";
        cliente.comuneNascita = "Bari";
        cliente.comuneResidenza = "Bari";
        cliente.indirizzo = "Via Amendola,12";
        cliente.idPatente = "A874";
        cliente.codiceFiscale= "RSSMRA85T10A562L";
        cliente.dataDiNascita = "05/12/1985";

        ClienteTO clienti[] = {cliente, cliente};

        Boolean results[] = {true, false};

        for (int i = 0; i < clienti.length; i++) {
            Boolean result = test.inserireCliente(clienti[i]);
            assertTrue("Caso di test per testInserireCliente: "+i,
                    result.equals(results[i]));
        }
    }


    public void testModificareDatiCliente() {
        ClienteTO cliente = new ClienteTO();

        cliente.nome = "Mario";
        cliente.cognome = "Rossi";
        cliente.comuneNascita = "Bari";
        cliente.comuneResidenza = "Monopoli";
        cliente.indirizzo = "Via Amendola,12";
        cliente.idPatente = "A874";
        cliente.codiceFiscale= "RSSMRA85T10A562L";
        cliente.dataDiNascita = "05/12/1985";
        cliente.id = "2";

        ClienteTO cliente1 = new ClienteTO();

        cliente1.nome = "Mario";
        cliente1.cognome = "Rossi";
        cliente1.comuneNascita = "Bari";
        cliente1.comuneResidenza = "Monopoli";
        cliente1.indirizzo = "Via Amendola,12";
        cliente1.idPatente = "A874";
        cliente1.codiceFiscale= "RSSMRA85T10A562L";
        cliente1.dataDiNascita = "05/12/1985";
        cliente1.id = "100";

        ClienteTO cliente2 = new ClienteTO();

        cliente2.nome = "Filippo";
        cliente2.cognome = "Verdi";
        cliente2.comuneNascita = "Bari";
        cliente2.comuneResidenza = "Bari";
        cliente2.indirizzo = "Via Orabona,12";
        cliente2.idPatente = "A9657";
        cliente2.codiceFiscale= "RSSMRA85T10A562L";
        cliente2.dataDiNascita = "05/01/1963";
        cliente2.id = "1";

        ClienteTO cliente3 = new ClienteTO();

        cliente3.nome = "Filippo";
        cliente3.cognome = "Verdi";
        cliente3.comuneNascita = "Bari";
        cliente3.comuneResidenza = "Bari";
        cliente3.indirizzo = "Via Orabona,12";
        cliente3.idPatente = "A9657";
        cliente3.codiceFiscale= "FLPVRD85T10A562L";
        cliente3.dataDiNascita = "05/01/1963";
        cliente3.id = "1";

        ClienteTO clienti[] = {cliente, cliente1, cliente2, cliente3};

        Boolean results[] = {true, false, false, true};

        for (int i = 0; i < clienti.length; i++) {
            Boolean result = test.modificareDatiCliente(clienti[i]);
            assertTrue("Caso di test per testModificareDatiCliente: "+i,
                    result.equals(results[i]));
        }
    }


    public void testRiepilogoClienti() {
        ClienteTO cliente = new ClienteTO();

        cliente.nome = "Mario";
        cliente.cognome = "Rossi";
        cliente.comuneNascita = "Bari";
        cliente.comuneResidenza = "Monopoli";
        cliente.indirizzo = "Via Amendola,12";
        cliente.idPatente = "A874";
        cliente.codiceFiscale= "RSSMRA85T10A562L";
        cliente.dataDiNascita = "1985-12-05";

        ClienteTO cliente3 = new ClienteTO();

        cliente3.nome = "Filippo";
        cliente3.cognome = "Verdi";
        cliente3.comuneNascita = "Bari";
        cliente3.comuneResidenza = "Bari";
        cliente3.indirizzo = "Via Orabona,12";
        cliente3.idPatente = "A9657";
        cliente3.codiceFiscale= "FLPVRD85T10A562L";
        cliente3.dataDiNascita = "1963-01-05";

        ClienteTO clienti[] = {cliente3, cliente};

        List<ClienteTO> results = test.riepilogoClienti();

        for (int i = 0; i < clienti.length; i++) {
            assertTrue("Caso di test per testRiepilogoClienti: "+i,
                    results.get(i).equals(clienti[i]));
        }

    }

    public void testRicercaClientiCodFiscale() {
        ClienteTO cliente = new ClienteTO();

        cliente.nome = "Mario";
        cliente.cognome = "Rossi";
        cliente.comuneNascita = "Bari";
        cliente.comuneResidenza = "Monopoli";
        cliente.indirizzo = "Via Amendola,12";
        cliente.idPatente = "A874";
        cliente.codiceFiscale= "RSSMRA85T10A562L";
        cliente.dataDiNascita = "1985-12-05";

        ClienteTO results[] = {cliente, null};

        String codFiscale[] = {"RSSMRA85T10A562L", "GPPSLL85T10A562L"};

        for (int i = 0; i < codFiscale.length; i++) {
            List<ClienteTO> result = test.ricercaClientiCodFiscale(codFiscale[i]);
            if (result == null) {
                assertNull("Caso di test testRicercaClientiCodFiscale: "+i,
                        results[i]);
            } else {
                assertTrue("Caso di test testRicercaClientiCodFiscale: "+i,
                        result.get(0).equals(results[i]));
            }
        }
    }

}
