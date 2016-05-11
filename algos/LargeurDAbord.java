package projetBPO.algos;

import projetBPO.jeux.Etat;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by Xavier on 05/04/2016.
 */
public class LargeurDAbord implements IRecherche {

    private ArrayList<Etat> file = new ArrayList<>();

    /**
     * Fonction qui permet d'ajouter les éléments à la file pour les traiter après, afin d'utiliser le même fonctionnement qu'un algorithme BFS
     * @param iterateur les valeurs à ajouter
     * @param h l'historique pour tester si on a déjà utilisé ces valeurs
     */
    private void ajouterFile(Iterator<Etat> iterateur,Historique h){

        while (iterateur.hasNext()) {
            Etat suivant = iterateur.next();
            if ( h.neContientPas(suivant) )
                file.add(suivant);
        }

    }

    /**
     * Fonction récursive permettant d'itérer sur tous les états accessibles à une action de l'état en paramètre
     * @param etat    Etat à tester
     * @return si on peut trouver un chemin final
     */
    @Override
    public boolean existeChemin(Etat etat) {
        boolean res = false;
        Historique histo = new Historique();
        histo.ajouter(etat);

        if (etat.estFinal()){
            res = true;
            return res;
        }

        Iterator<Etat> iterateur = etat.iterator();

        if (iterateur==null)
            return false;

        if ( iterateur.hasNext() && res == false){
            iterateur.forEachRemaining(file::add);
            file.remove(etat);
            res = existeChemin(file.get(0),histo);
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
    private boolean existeChemin(Etat etat, Historique histo){
        boolean res = false;
        if (etat.estFinal())
            return true;

            file.remove(0);
            histo.ajouter(etat);
            Iterator<Etat> iterateur = etat.iterator();
            ajouterFile(iterateur,histo);
            if (file.size() > 0){
                res = existeChemin(file.get(0),histo);
            }

            return res;
    }






}
