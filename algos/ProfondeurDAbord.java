package projetBPO.algos;

import projetBPO.jeux.Etat;
import java.util.Iterator;


/**
 * @author Xavier CHOPIN
 */

public class ProfondeurDAbord implements IRecherche {


    /**
     * Fonction récursive permettant d'itérer sur tous les états accessibles à une action de l'état en paramètre
     * @param etat    Etat à tester
     * @return si on peut trouver un chemin final
     */
    @Override
    public boolean existeChemin(Etat etat) {
        Historique histo = new Historique();
        histo.ajouter(etat);
        boolean res = false;

        if (etat.estFinal())
            return true;

        Iterator<Etat> iterateur = etat.iterator();

        if (iterateur == null)
            return false;

        while (iterateur.hasNext() && res == false) {
            Etat suivant = iterateur.next();

            if (histo.neContientPas(suivant)) {
                histo.ajouter(suivant);
                res = existeChemin(suivant,histo);
            }
        }
        return res;
    }



    /**
     * Fonction récursive permettant d'itérer sur tous les états accessibles à une action de l'état en paramètre
     * utilise un historique pour ne pas re tester les mêmes choses et donc éviter des boucles infinies
     * @param etat    Etat à tester
     * @param histo  historique sur lequel on s'appuie
     * @return si on peut trouver un chemin final
     */
    private boolean existeChemin(Etat etat, Historique histo) {
        boolean res = false;
        if (etat.estFinal())
            return true;

        Iterator<Etat> iterateur = etat.iterator();
        while (iterateur.hasNext() && (res == false) )  {

            Etat suivant = iterateur.next();
            if (histo.neContientPas(suivant)) {
                histo.ajouter(suivant);
                if (suivant.estFinal()){
                    return true;
                }
                res = existeChemin(suivant,histo);
            }
        }

        return res;

    }
}




