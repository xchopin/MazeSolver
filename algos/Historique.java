package projetBPO.algos;

import projetBPO.jeux.Etat;
import projetBPO.jeux.Labyrinthe;
import java.util.ArrayList;

/**
 * Created by Xavier on 03/04/2016.
 */
public class Historique {

    private ArrayList<Etat> liste;

    public Historique(){
        this.liste = new ArrayList<>();
    }

    public void ajouter(Etat e){
        this.liste.add(e);

        // Pour le petit GUI dans mon main de Labyrinthe :-)
        if (e instanceof Labyrinthe){
            Labyrinthe lab = (Labyrinthe) e;
            lab.setCaseParcourue(lab.X,lab.Y);
        }

    }

    /**
     * Fonction qui retourne si l'historique ne contient pas l'état testé
     * @param e l'état testé
     * @return si e est dans l'historique
     */
    public boolean neContientPas(Etat e){
        return !this.liste.contains(e);
    }

}
