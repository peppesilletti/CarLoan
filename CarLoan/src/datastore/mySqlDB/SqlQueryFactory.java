package datastore.mySqlDB;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.ConfigException;

/**
 * Preleva le query dal file xml che le mappa con le rispettive operazioni di
 * persistenza.
 * */
public final class SqlQueryFactory {

    /**
     * Unica istanza della classe.
     * */
    private static final SqlQueryFactory INSTANCE = new SqlQueryFactory();

    /**
     * Path del file xml contenente le query sql.
     * */
    private static URL url;

    /**
     * Costruttore privato.
     * */
    private SqlQueryFactory() {
        url = getClass().getResource("XmlQuery.xml");
    }

    /**
     * Restituisce l'unica istanza della classe.
     * @return
     *      Istanza della classe.
     * */
    public static SqlQueryFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Ritorna la query desiderata in base all'operazione inserita.
     *
     * @param operation
     *            Operazione della quale si vuole sapere l'sql
     * @return La query sql richiesta.
     * @throws ConfigException
     *             Solleva un'eccezione se non c'� la query richiesta.
     * */
    public String getQuery(final String operation) throws ConfigException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File(url.getPath()));
            NodeList nodeList = document.getElementsByTagName("SQL");

            for (int x = 0, size = nodeList.getLength(); x < size; x++) {
                String value = nodeList.item(x).getAttributes()
                        .getNamedItem("ID").getNodeValue();
                if (value.compareTo(operation) == 0) {
                    return nodeList.item(x).getTextContent().trim();
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            ConfigException ex = new ConfigException(e);
            ErrorHandlerInt er = ErrorHandler.getIstance();
            er.processError(ex.getClass(), ex, ErrorHandlerInt.FATAL);
        }
        throw new ConfigException("Query non trovata");
    }
}
