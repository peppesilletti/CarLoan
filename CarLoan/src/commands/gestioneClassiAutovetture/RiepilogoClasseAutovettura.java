package commands.gestioneClassiAutovetture;

import java.util.List;

import transferObjects.entitiesTO.ClasseAutovetturaTO;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneClassiAutovetture;

import commands.Command;

/**
 * Classe che incapsula il comando di riepilogo delle classi autovetture.
 * */
public class RiepilogoClasseAutovettura implements Command {
    /**
    **
    * Application service per la gestione delle classi autovettura.
    * */
   private GestioneClassiAutovetture gca;

   /**
    * Costruttore della classe.
    * */
   public RiepilogoClasseAutovettura() {
       gca = new GestioneClassiAutovetture();
   }

   @Override
   public final ResponseInt execute() {
       ComplexResponse<ClasseAutovetturaTO> response =
               new ComplexResponse<ClasseAutovetturaTO>();

       List<ClasseAutovetturaTO> list = gca.riepilogoClassiAutovettura();

       response.addParameterList(list);

       response.addParameterList(list);

       return response;
   }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
