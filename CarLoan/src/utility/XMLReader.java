package utility;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.XMLReaderException;

/**
 * Classe che permette di leggere file XML.
 * */
public final class XMLReader {

    private Document doc;

    /**
     * Legge il file XML indicato nel percorso.
     *
     * @param filePath
     *            percorso del file XML da leggere.
     */
    public XMLReader(final String filePath) {
        try {
            URL url = getClass().getResource(filePath);
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            doc = docBuilder.parse(url.openStream());
        } catch (ParserConfigurationException | SAXException | IOException  e) {

        }
    }

    /**
     * Legge il percorso in cui si trova il file fxml
     * dell'ui.
     *
     *@param tag
     *
     *@param key
     *            lingua da cercare.
     *@return percorso del file XML.
     */
    public String readPath(final String tag, final String key) {
        return this.readElement(tag, "id", key, "src");
    }

    /**
    * Restituisce l'attributo di un tag presente in un file XML.
    *
    * @param tag
    *            - tag su cui si vuole effettuare la ricerca.
    * @param id
    *            - attributo che identifica il tag.
    * @param key
    *            - chiave con cui matchare id.
    * @param attribute
    *            attributo di cui si vuole leggere il valore.
    * @return valore dell'attributo cercato.
    */
   private String readElement(final String tag, final String id,
           final String key, final String attribute) {
       String value = "";
       try {
           doc.getDocumentElement().normalize();
           NodeList nList = doc.getElementsByTagName(tag);
           for (int temp = 0; temp < nList.getLength(); temp++) {

               Node nNode = nList.item(temp);
               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                   Element eElement = (Element) nNode;

                   if (eElement.getAttribute(id).matches(key)) {
                       value = eElement.getAttribute(attribute);
                   }
               }
           }
       } catch (Exception e) {
           handleException(e);
       }
       return value;
   }

    /**
     * Permette di ottenere il valore di un tag appartenente ad un nodo.
     *
     * @param sTag
     *            - tag da cui prelevare il valore.
     * @param eElement
     *            - nodo a cui appartiene il tag.
     * @return valore del tag.
     */
    private String getTagValue(final String sTag, final Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
                .getChildNodes();

        Node nValue = nlList.item(0);
        return nValue.getNodeValue();
    }

    /**
     * Gestisce le eccezioni DAO che si verificano.
     *
     * @param e
     *            eccezione verificatasi

     * */

    private void handleException(final Exception e) {
        XMLReaderException ex = new XMLReaderException(e.getMessage());
        ErrorHandlerInt er = ErrorHandler.getIstance();
        er.processError(this.getClass(), ex, ErrorHandlerInt.FATAL);
    }

}
