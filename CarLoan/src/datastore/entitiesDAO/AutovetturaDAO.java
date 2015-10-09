package datastore.entitiesDAO;

import java.util.List;

import transferObjects.entitiesTO.AutovetturaTO;

/**
 * Interfaccia che offre servizi per la memorizzazione e il recupero dei dati di
 * una autovettura dal datastore.
 * */
public interface AutovetturaDAO extends DaoInt<AutovetturaTO> {
    /**
     * Ricerca un'autovettura secondo targa, marca o classe autovettura.
     * @param targa
     *      Targa dell'autovettura da cercare
     * @param marca
     *      Marca dell'autovettura da cercare
     * @param classe
     *      Classe dell'autovettura da cercare
     * @return
     *      List con tutte le autovettura che corrispondono ai parametri.
     */
    List<AutovetturaTO> getAutovetturaParams(String targa, String marca, String classe);

    /**
     * Restituisce un elenco di autovetture disponibili nell'agenzia.
     * @param classe
     *      Classe dell'autovettura.
     * @param agenziaID
     *      Id dell'agenzia dove sono le autovetture.
     *
     * @return
     *      Lista delle agenzie.
     * */
    List<AutovetturaTO> getAutovettureDisponibili(String classe, String agenziaId);

    /**
     * Verifica che un'autovettura non sia in manutenzione o noleggiata.
     * @param id
     *      Id dell'autovettura.
     * @result
     *      Risultato dell'operazione.
     * */
    Boolean checkAutoDisponibile(String id);
}
