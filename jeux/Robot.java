package projetBPO.jeux;

import projetBPO.algos.LargeurDAbord;
import projetBPO.algos.ProfondeurDAbord;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


/**
 * Created by Xavier on 05/04/2016.
 */
public class Robot extends Etat {

    public int hauteurPile;
    private ArrayList<Bloc> tas;
    private ArrayList<Bloc> pile;
    private static String solution;

    public Robot(int hauteurPile, Bloc ... tab){
        this.hauteurPile = hauteurPile;
        tas = new ArrayList<>(Arrays.asList(tab));
        solution = "";

        if (pile == null) {
            pile = new ArrayList<>();
        }
    }

    public boolean estFinal(){
        if (pile.size() == hauteurPile){

            solution += this.toString();

            return true;
        }

        return false;
    }

    /**
     * Méthode permettant d'obtenir la solution si elle a été trouvé
     * @return le toString du robot solution
     */
    public String getSolution(){
        return solution;
    }

    private void ajouterPile(ArrayList<Bloc> oldPile, Bloc  b ){
        if (oldPile == null || oldPile.size() < 0){
            pile.add(b);
        }else{
            oldPile.add(b);
            pile = new ArrayList<>(oldPile);
        }
    }



    public String toString(){
        String res = "Ce Robot à une pile de " + pile.size() + " blocs ( ";

        for (Bloc b : pile)
            res+= b.nom + " ";

        res+=") et il lui reste " + tas.size() + " blocs (";

        for (Bloc b : tas)
            res+= b.nom + " ";

        res+=") \n";
        return res;
    }


    private Bloc[] listeEnTableau(ArrayList<Bloc> liste){
        return liste.toArray(new Bloc[liste.size()]);
    }


    /**
     * Fonction retournant le bloc en tête de pile
     * @return Bloc b (le dernier élément de la liste Pile)
     */
    private Bloc teteDePile(){
        if (pile !=null && pile.size() > 0)
           return pile.get(pile.size()-1);

        return null;
    }


    /**
     * Fonction iterator permettant de créer des noeuds d'un graphe
     * cette fonction permet nottament de tester au fur et à mesure les différentes combinaisons de bloc à empiler que le Robot possède
     * @return iterator<Etat> les combinaisons
     */
    public Iterator<Etat> iterator(){
        ArrayList<Etat> list = new ArrayList<>();
        ArrayList<Bloc> nouveauTas;


        if (tas.size() > 0) {
            // Si nous sommes au début: création des premiers noeuds
            if ((pile.size() < 1)) {

                // Si on demande de base: une hauteur > aux nombres de blocs, c'est impossible
                if (hauteurPile > tas.size())
                   return null;

                for (int i = 0; i < tas.size(); i++) {

                    nouveauTas = new ArrayList(tas); // Commençons par une copie du tas original
                    nouveauTas.remove(i); // Supprimons l'élément que l'on place dans la pile
                    Robot r = new Robot(hauteurPile,listeEnTableau(nouveauTas));
                    r.ajouterPile(null,tas.get(i));
                    list.add(r);

                }

            }else{

                for (int i = 0; i < tas.size(); i++) {

                    nouveauTas = new ArrayList(tas); // Commençons par une copie du tas original
                    Bloc prochain = nouveauTas.get(i);

                    // Testons si l'on peut poser le prochain bloc sur notre pile (Pas supérieur à 20% des attributs du bloc précédent)
                    if ( (prochain.masse <= 1.2 * teteDePile().masse) && (prochain.largeur <= 1.2 * teteDePile().largeur) ){
                        nouveauTas.remove(i); // Supprimons l'élément que l'on place dans la pile
                        Robot r = new Robot(hauteurPile,listeEnTableau(nouveauTas));
                        r.ajouterPile(pile,tas.get(i)); // On ajoute l'élement dans la pile
                        list.add(r);
                    }
                }
            }
        }


        return list.iterator();

    }



    public static void main(String args[]){
        LargeurDAbord algoLargeur = new LargeurDAbord();
        ProfondeurDAbord algoProfondeur = new ProfondeurDAbord();

        Bloc b1 = new Bloc("rouge", 70, 10);
        Bloc b2 = new Bloc("bleu", 20, 5);
        Bloc b3 = new Bloc("vert", 21, 5);
        Bloc b4 = new Bloc("orange", 20, 1);
        Bloc b5 = new Bloc("jaune", 100, 21);
        Bloc b6 = new Bloc("impossible", 40, 8000);
        Bloc b7 = new Bloc("impossible2", 40000, 80);

        Robot robot1 = new Robot(1, new Bloc[]{b1});
        Robot robot2 = new Robot(2, new Bloc[]{b1, b2});
        Robot robot3 = new Robot(5, new Bloc[]{b1,b3,b4,b5,b2});
        Robot robot4 = new Robot(2, new Bloc[]{b4,b5});
        Robot robot5 = new Robot(3, new Bloc[]{b7,b6,b1});
        Robot robot6 = new Robot(2, new Bloc[]{b6,b7});
        Robot robot7 = new Robot(4, new Bloc[]{b4,b4,b1,b2});



        System.out.println("\n Test de l'algo en profondeur : ");
        if (algoProfondeur.existeChemin(robot4)) {
            System.out.println("Un chemin est disponible");
        }else{
            System.out.println("Pas de chemin");
        }
        System.out.println("\n --------------- ");


        System.out.println("\n Test de l'algo en largeur : ");
        if (algoLargeur.existeChemin(robot4)) {
            System.out.println("Un chemin est disponible");
        }else{
            System.out.println("Pas de chemin");
        }
        System.out.println("\n --------------- ");


        /** ------------------------------ TEST COMPARATEUR TEMPS ------------------------------------------- */

        long debutLargeur = System.nanoTime();
        algoLargeur.existeChemin(robot4);
        double tempsLargeur = ( System.nanoTime() - debutLargeur ) / 1000000.0;


        long debutProfondeur = System.nanoTime();
        algoProfondeur.existeChemin(robot4);
        double tempsProfondeur = ( System.nanoTime() - debutProfondeur) / 1000000.0;


        System.out.println("\n -------- RESULTAT: -------- ");
        System.out.println("Temps Profondeur : " + tempsProfondeur + "ms");
        System.out.println("Temps Largeur : " + tempsLargeur + "ms");

    }

}
