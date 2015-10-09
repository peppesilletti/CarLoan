package datastore.mySqlDB;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SqlQueryFactoryTest {

    private SqlQueryFactory test;

    @Before
    public void setUp() throws Exception {
        test = SqlQueryFactory.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        test = null;
    }

    @Test
    public void testGetQuery() {
        String operations[] = {"ResearchUserByName", "prova", null};
        String query[] = {"SELECT username, tipo, agenzia_id FROM dati_account WHERE username=?;",
                "", null};

        for (int i = 0; i < query.length; i++) {
            String result = test.getQuery(operations[i]);
            assertTrue("Caso di test per testGetQuery: "+i,
                    result.equals(query[i]));
        }

    }

}
