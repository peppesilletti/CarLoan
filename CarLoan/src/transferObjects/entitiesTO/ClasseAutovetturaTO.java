package transferObjects.entitiesTO;

import transferObjects.TransferObjectInt;
import business.businessObjects.ClasseAutovettura;

/**
 * Transfer Object per il business object {@link ClasseAutovettura}.
 * */
public class ClasseAutovetturaTO implements TransferObjectInt {

    public String  nome;
    public Integer ariaCondizionata;
    public String  tipoCambio;
    public Integer numeroPorte;
    public Integer numeroPosti;
    public String id;

    /**
     * Costruttore di default.
     * */
    public ClasseAutovetturaTO() {
        // TODO Auto-generated constructor stub
    }

    /**Costrutto con parametri.
     * @param nome
     * @param ariaCondizionata
     * @param tipoCambio
     * @param numeroPorte
     * @param numeroPosti
     */
    public ClasseAutovetturaTO(String nome, Integer ariaCondizionata,
            String tipoCambio, Integer numeroPorte, Integer numeroPosti) {
        this.nome = nome;
        this.ariaCondizionata = ariaCondizionata;
        this.tipoCambio = tipoCambio;
        this.numeroPorte = numeroPorte;
        this.numeroPosti = numeroPosti;
    }

    /**
     * Costruttore che crea un nuovo TO basato su un'istanza di TO già
     * esistente.
     *
     * @param classe
     *            TO esterno già esistente.
     * */
    public ClasseAutovetturaTO(final ClasseAutovetturaTO classe) {
        this.nome = classe.nome;
        this.ariaCondizionata = classe.ariaCondizionata;
        this.tipoCambio = classe.tipoCambio;
        this.numeroPorte = classe.numeroPorte;
        this.numeroPosti = classe.numeroPosti;
        this.id = classe.id;
    }

    /**
     * Crea un nuovo Transfer Object.
     *
     * @return Nuova istanza del TO.
     * */
    public final ClasseAutovetturaTO getData() {
        return new ClasseAutovetturaTO(this);
    }

    public Boolean equals(ClasseAutovetturaTO classe) {
        if (classe.ariaCondizionata.equals(this.ariaCondizionata)
                && classe.nome.equals(this.nome)
                && classe.numeroPorte.equals(this.numeroPorte)
                && classe.numeroPosti.equals(this.numeroPosti)
                && classe.tipoCambio.equals(this.tipoCambio)) {
            return true;
        } else {
            return false;
        }
    }
}
