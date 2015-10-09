package transferObjects.entitiesTO;

import java.util.ArrayList;
import java.util.List;

import transferObjects.TransferObjectInt;

/**
 * Transfer Object per l'entità contratto.
 * */
public class ContrattoTO implements TransferObjectInt {
    public String id;
    public String dataApertura;
    public String dataChiusura;
    public String modalità;
    public Float cauzione;
    public Float importoFinale;
    public String tariffaId;
    public String clienteId;
    public String autovetturaId;
    public String agenziaAperturaId;
    public String agenziaRientroId;
    public String dataInizioNoleggio;
    public String dataFineNoleggio;
    public List<ExtraTO> listaExtra;
    public Float saldoParziale;
    public String stato;
    public Float chilometriPercorsi;

    public ClienteTO infoCliente;
    public AutovetturaTO infoAutovettura;
    public TariffaTO infoTariffa;
    public AgenziaTO infoAgenziaRientro;

    public ContrattoTO() {
        infoCliente = new ClienteTO();
        listaExtra = new ArrayList<ExtraTO>();
        infoAutovettura = new AutovetturaTO();
        infoTariffa = new TariffaTO();
        infoAgenziaRientro = new AgenziaTO();
    }

}
