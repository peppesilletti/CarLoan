package commands;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;

import commands.gestioneAccountManagerAgenzia.EliminareAccountManager;
import commands.gestioneAccountManagerAgenzia.InserireAccountManager;
import commands.gestioneAccountManagerAgenzia.ModificareDatiAccountManager;
import commands.gestioneAccountManagerAgenzia.RicercaManagerNomeCognome;
import commands.gestioneAccountManagerAgenzia.RicercareDatiAccountManager;
import commands.gestioneAccountManagerAgenzia.RiepilogoManager;
import commands.gestioneAgenzia.EliminareAgenzia;
import commands.gestioneAgenzia.EliminareExtraAgenzia;
import commands.gestioneAgenzia.GetExtraAgenzia;
import commands.gestioneAgenzia.GetManagerAgenzia;
import commands.gestioneAgenzia.GetOperatoriAgenzia;
import commands.gestioneAgenzia.InserireAgenzia;
import commands.gestioneAgenzia.InserireExtraAgenzia;
import commands.gestioneAgenzia.ModificareAgenzia;
import commands.gestioneAgenzia.RicercareAgenzia;
import commands.gestioneAgenzia.RicercareAgenzieCity;
import commands.gestioneAgenzia.RiepilogoAgenzie;
import commands.gestioneAutovetture.EliminareAutovettura;
import commands.gestioneAutovetture.InserireAutovettura;
import commands.gestioneAutovetture.ModificareAutovettura;
import commands.gestioneAutovetture.RestituireAutovettureDisponibili;
import commands.gestioneAutovetture.RicercareAutovettura;
import commands.gestioneAutovetture.RicercareAutovetturaParams;
import commands.gestioneAutovetture.RiepilogoAutovetture;
import commands.gestioneClassiAutovetture.EliminareClasseAutovettura;
import commands.gestioneClassiAutovetture.InserireClasseAutovettura;
import commands.gestioneClassiAutovetture.ModificareClasseAutovettura;
import commands.gestioneClassiAutovetture.RicercareClasseAutovettura;
import commands.gestioneClassiAutovetture.RiepilogoClasseAutovettura;
import commands.gestioneClienti.InserireCliente;
import commands.gestioneClienti.ModificareDatiCliente;
import commands.gestioneClienti.RicercaClientiCodFiscale;
import commands.gestioneClienti.RiepilogoClienti;
import commands.gestioneContratti.AperturaContratto;
import commands.gestioneContratti.ChiusuraContratto;
import commands.gestioneContratti.NextNumeroContratto;
import commands.gestioneContratti.RicercareContratto;
import commands.gestioneContratti.StampaContratto;
import commands.gestioneExtra.EliminareExtra;
import commands.gestioneExtra.InserireExtra;
import commands.gestioneExtra.ModificareExtra;
import commands.gestioneExtra.RicercareExtra;
import commands.gestioneExtra.RiepilogoExtra;
import commands.gestioneLogin.GetTipoUtente;
import commands.gestioneLogin.Login;
import commands.gestioneManutenzioni.EliminareManutenzione;
import commands.gestioneManutenzioni.InserireManutenzione;
import commands.gestioneManutenzioni.ModificareDatiManutenzione;
import commands.gestioneManutenzioni.RicercareManutenzioniAutovettura;
import commands.gestioneManutenzioni.RiepilogoManutenzioni;
import commands.gestioneOperatore.EliminareAccountOperatore;
import commands.gestioneOperatore.InserireAccountOperatore;
import commands.gestioneOperatore.ModificareDatiAccountOperatore;
import commands.gestioneOperatore.RicercaOperatoreNomeCognome;
import commands.gestioneOperatore.RiepilogoOperatori;
import commands.gestioneTariffe.EliminareTariffa;
import commands.gestioneTariffe.InserireTariffa;
import commands.gestioneTariffe.ModificareTariffa;
import commands.gestioneTariffe.RicercareTariffeClasse;
import commands.gestioneTariffe.RiepilogoTariffe;

import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.ApplicationControllerException;

/**
 * Factory dei comandi.
 * */
public final class CommandFactory {

    /**
     * Unica istanza della classe.
     * */
    private static CommandFactory instance =
            new CommandFactory();

    /**
     * Contiene tutti i comandi disponibili.
     * */
    private Map<String, Class> commandMap;

    /**
     * Costruttore privato per la realizzazione del singleton.
     * */
    private CommandFactory() {

        commandMap = new HashMap<String, Class>();

        commandMap.put("login", Login.class);
        commandMap.put("getTipoUtente", GetTipoUtente.class);

        //Command per Gestione Operatori
        commandMap.put("inserireAccountOperatore",
                InserireAccountOperatore.class);
        commandMap.put("modificareDatiAccountOperatore",
                ModificareDatiAccountOperatore.class);
        commandMap.put("eliminareAccountOperatore",
                EliminareAccountOperatore.class);
        commandMap.put("riepilogoAccountOperatore", RiepilogoOperatori.class);
        commandMap.put("ricercareOperatoreByNomeCognome",
                RicercaOperatoreNomeCognome.class);

        //Command per Gestione Classe Autovettura
        commandMap.put("inserireClasseAutovettura",
                InserireClasseAutovettura.class);
        commandMap.put("modificareClasseAutovettura",
                ModificareClasseAutovettura.class);
        commandMap.put("eliminareClasseAutovettura",
                EliminareClasseAutovettura.class);
        commandMap.put("ricercareClasseAutovettura",
                RicercareClasseAutovettura.class);
        commandMap.put("riepilogoClasseAutovettura",
                RiepilogoClasseAutovettura.class);

        //Command per Gestione Extra
        commandMap.put("inserireExtra", InserireExtra.class);
        commandMap.put("modificareExtra", ModificareExtra.class);
        commandMap.put("eliminareExtra", EliminareExtra.class);
        commandMap.put("ricercareExtra", RicercareExtra.class);
        commandMap.put("riepilogoExtra", RiepilogoExtra.class);

        //Gestione Manager Agenzia
        commandMap.put("inserireAccountManager", InserireAccountManager.class);
        commandMap.put("modificareDatiAccountManager",
                ModificareDatiAccountManager.class);
        commandMap.put("eliminareAccountManager",
                EliminareAccountManager.class);
        commandMap.put("ricercareDatiAccountManager",
                RicercareDatiAccountManager.class);
        commandMap.put("riepilogoManager", RiepilogoManager.class);
        commandMap.put("GetManagerAgenzia", GetManagerAgenzia.class);
        commandMap.put("inserireExtraAgenzia", InserireExtraAgenzia.class);
        commandMap.put("eliminareExtraAgenzia", EliminareExtraAgenzia.class);
        commandMap.put("ricercareManagerByNomeCognome",
                RicercaManagerNomeCognome.class);

        //Commands per la Gestione Agenzie
        commandMap.put("riepilogoAgenzie", RiepilogoAgenzie.class);
        commandMap.put("inserireAgenzia", InserireAgenzia.class);
        commandMap.put("modificareAgenzia", ModificareAgenzia.class);
        commandMap.put("eliminareAgenzia", EliminareAgenzia.class);
        commandMap.put("ricercareAgenzia", RicercareAgenzia.class);

        commandMap.put("riepilogoOperatoriAgenzia",
                GetOperatoriAgenzia.class);

        commandMap.put("riepilogoExtraAgenzia",
                GetExtraAgenzia.class);

        commandMap.put("ricercareAgenzieCittà", RicercareAgenzieCity.class);

        //Commands per la Gestione Tariffe
        commandMap.put("inserireTariffa", InserireTariffa.class);
        commandMap.put("riepilogoTariffe", RiepilogoTariffe.class);
        commandMap.put("eliminareTariffa", EliminareTariffa.class);
        commandMap.put("modificareTariffa", ModificareTariffa.class);
        commandMap.put("ricercareTariffeClasse", RicercareTariffeClasse.class);

        //Commands per la Gestione Autovetture
        commandMap.put("inserireAutovettura", InserireAutovettura.class);
        commandMap.put("modificareAutovettura", ModificareAutovettura.class);
        commandMap.put("eliminareAutovettura", EliminareAutovettura.class);
        commandMap.put("ricercareDatiAutovettura", RicercareAutovettura.class);
        commandMap.put("riepilogoAutovetture", RiepilogoAutovetture.class);
        commandMap.put("ricercareAutovetturaParams",
                    RicercareAutovetturaParams.class);
        commandMap.put("restituisciAutovettureDisponibili",
                RestituireAutovettureDisponibili.class);


        //Commands per la Gestione Manutenzioni
        commandMap.put("inserireManutenzione", InserireManutenzione.class);
        commandMap.put("modificareDatiManutenzione",
                ModificareDatiManutenzione.class);
        commandMap.put("eliminareManutenzione", EliminareManutenzione.class);
        commandMap.put("riepilogoManutenzioni", RiepilogoManutenzioni.class);
        commandMap.put("ricercareManutenzioniAutovettura", RicercareManutenzioniAutovettura.class);

        //Commands per la Gestione Clienti
        commandMap.put("inserireCliente", InserireCliente.class);
        commandMap.put("modificareDatiCliente", ModificareDatiCliente.class);
        commandMap.put("riepilogoClienti", RiepilogoClienti.class);
        commandMap.put("ricercareClientiCodFiscale", RicercaClientiCodFiscale.class);

        //Commands per la Gestione Contratti
        commandMap.put("aperturaContratto", AperturaContratto.class);
        commandMap.put("nextNumeroContratto", NextNumeroContratto.class);
        commandMap.put("ricercareContratto", RicercareContratto.class);
        commandMap.put("chiusuraContratto", ChiusuraContratto.class);
        commandMap.put("stampareContratto", StampaContratto.class);
    }

    /**
     * Restituisce l'unica istanza della classe.
     * @return
     *      Istanza della classe
     * */
    public static CommandFactory getInstance() {
        return instance;
    }

    /**
     * Ritorna un comando.
     *
     * @param commandName
     *            Il comando da creare.
     * @return Il comando richiesto.
     * @throws IllegalArgumentException
     *             Solleva un'eccezione se non è possibile
     *             creare il comando.
     * */
    public Command getCommand(final String commandName,
            final RequestInt request)
            throws IllegalArgumentException {

            Class c = commandMap.get(commandName);
            Command command = null;
            if (c != null) {
                try {
                    if (request.getClass().equals(ComplexRequest.class)) {
                        Constructor constructor =
                                c.getConstructor(RequestInt.class);

                        command = (Command)
                                constructor.newInstance(request);
                        return command;
                    } else {
                        command = (Command) c.newInstance();
                        return command;
                    }


            } catch (NoSuchMethodException | SecurityException
                    | InstantiationException | IllegalAccessException
                    | InvocationTargetException e1) {


                ApplicationControllerException ex =
                        new ApplicationControllerException(e1.getMessage());
                ErrorHandlerInt er = ErrorHandler.getIstance();
                er.processError(this.getClass(), ex, ErrorHandlerInt.FATAL);

            }
        } else {
            throw new IllegalArgumentException(
                    commandName + " non è un comando valido.");
        }
        return null;
    }

}
