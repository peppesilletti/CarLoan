package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.ClasseAutovetturaTO;

/**
 * Business Object per l'entità classe autovettura.
 * */
public interface ClasseAutovetturaInt {
    /**
     *Inserisce una classe autovettura.
     *@param classe
     *      TO con i dati della classe.
     *@return
     *      Il risultato dell'operazione.
     * */
    Boolean addClasseAutovettura(ClasseAutovetturaTO classe);

    /**
     * Restituisce una classe autovettura.
     * @param id
     *      id della classe autovettura.
     * @return
     *      TO con i dati dell'autovettura.
     * */
    ClasseAutovetturaTO getClasseAutovettura(
            String id);

    /**
     * Elimina una classe autovettura.
     * @param id
     *      id della classe autovettura.
     * @return
     *      Risultato dell'operazione.
     * */
    Boolean deleteClasseAutovettura(String id);

    /**
     *Aggiorna i dati di una classe autovettura.
     *@param classe
     *      TO con i dati aggiornati della classe.
     *@return
     *      Il risultato dell'operazione.
     * */
    Boolean updateClasseAutovettura(ClasseAutovetturaTO classe);

    /**
     * Restituisce tutte le autovetture.
     * @return
     *      Una lista con tutte le autovetture.
     * */
    List<ClasseAutovetturaTO> getAll();

    /**
     * Controlla se esiste già la classe autovettura nel sistema.
     * @param dati
     *      TO che contiene i dati della classe autovettura da verificare.
     * @return
     *      Il risultato della verifica.
     * */
    Boolean existCheck(ClasseAutovetturaTO dati);
}
