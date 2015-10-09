package datastore.entitiesDAO;

import java.util.List;

import transferObjects.entitiesTO.TariffaTO;

/**
 * Interfaccia che offre servizi per la memorizzazione e il recupero dei dati di
 * una tariffa dal datastore.
 * */
public interface TariffaDAO extends DaoInt<TariffaTO> {
    /**
     * Ricerca una tariffa in base alla classe autovettura.
     * @param classe
     *      Nome della classe.
     * @return
     *      Lista di tariffe che corrispondono alla ricerca.
     * */
    List<TariffaTO> getTariffaByClasse(String classe);
}
