package projetBPO.jeux;

/**
 * @author Xavier CHOPIN
 */


import projetBPO.algos.LargeurDAbord;
import projetBPO.algos.ProfondeurDAbord;
import java.awt.*;
import java.util.*;


/**un état est représenté par la position du personnage ; le
 monde est rangé dans un tableau statique, donc commun à tous les états (avec la convention : 0 pour
 une case vide, 1 pour symboliser un mur).
 */
public class Labyrinthe extends Etat {

    final static int CASE_VIDE = 0;
    final static int MUR = 1;
    final static int PERSONNAGE = 2;
    final static int CASE_PARCOURUE = 3;
    protected static int[][] monde;
    static ArrayList<Point> labyEnAttentes ; // dans votre test vous faites setmonde après avoir placé des coordonnées... il faut donc temporiser
    public int X;
    public int Y;

    /**
     * Constructeur Labyrinthe(int x, int y) où x et y représentent la position courante du
     * personnage, numérotés à partir de 0.
     */

    public Labyrinthe(int x, int y) {

            this.X = x;
            this.Y = y;


            // Si on fait setMonde avant ... mais dans votre test vous le faites après !!
        if (monde !=null){
            if ( (x < monde.length) && (y < monde[0].length) && (x>=0) && (y>=0)){
                monde[x][y] = PERSONNAGE;
            }else{
                throw new AssertionError("Valeurs invalides");
            }
        }else{
            if (labyEnAttentes == null){
                labyEnAttentes = new ArrayList<>();
                labyEnAttentes.add(new Point(X,Y));
            }
        }


    }

    /**
     *  Fonction permettant de montrer sur la sortie standard les "fins" de recherches et voir dans quelles directions
     *  l'algorithme s'est propagé
     * @param x abscisse
     * @param y ordonnée
     */
    public void setCaseParcourue(int x, int y) {

        if ( (x < monde.length) && (y < monde[0].length) && (x>=0) && (y>=0)){
            monde[x][y] = CASE_PARCOURUE;
        }else{
            throw new AssertionError("Valeurs invalides");
        }

    }



    /**
     * Fonction setMonde: ajoute un monde au jeu
     * @param map tableau d'entier bidemensionnel
     */
    public static void setMonde(int[][] map) {
        monde = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++)
            monde[i] = map[i].clone();

        if (labyEnAttentes != null){
            for (Point p : labyEnAttentes){
                monde[(int)p.getX()][(int)p.getX()] = PERSONNAGE;
            }
        }
    }

    /**
     * Fonction qui permet de parcourir les état accessibles depuis l'état e
     * @return un tableau des états accessibles depuis e
     */

    public Iterator<Etat> iterator(){
        ArrayList<Etat> list = new ArrayList<>();


        if (Y+1 < monde[0].length) {
            if (monde[X][Y+1] != MUR)
                list.add(new Labyrinthe(X, Y + 1));
        }

        if (Y-1 >= 0) {
            if (monde[X][Y-1] != MUR)
                list.add(new Labyrinthe(X, Y - 1));
        }

        if (X+1 < monde.length) {
            if (monde[X+1][Y] != MUR)
                list.add(new Labyrinthe(X + 1, Y));
        }

        if (X-1 >= 0) {
            if (monde[X-1][Y] != MUR)
                list.add(new Labyrinthe(X - 1, Y));
        }

        return list.iterator();
    }


    /***
     * Permet de comparer deux labyrinthes
     * @param obj Labyrinthe à tester
     * @return si les deux laby sont égaux
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Labyrinthe other = (Labyrinthe) obj;
        return ( (X == other.X) && (Y == other.Y) );
    }

    /**
     * Fonction permettant d'afficher le labyrinthe sur la sortie standard
     */
    public String toString() {
        final int X1 = monde.length;
        final int Y1 = monde[0].length;
        String res = "";

        for (int i = 0 ; i < Y1 ; i++) {
            for (int j = 0 ; j < X1 ; j++) {
                switch (monde[j][i]) {
                    case MUR:
                        res+=("|||");
                        break;
                    case CASE_VIDE:
                        res+=("   ");
                        break;

                    case CASE_PARCOURUE:
                        res+=(" o ");
                        break;

                    default:
                        res+=(" x ");
                        break;
                }
            }
            res+=("\n");
        }
        res+=("\n - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n");
        return res;
    }




    public static void main (String[] args) {
        System.out.println(" _              ______  _     _ ______  _____ ______  _______ _     _ _______");
        System.out.println("| |        /\\  (____  \\| |   | (_____ \\(_____)  ___ \\(_______) |   | (_______)");
        System.out.println("| |       /  \\  ____)  ) |___| |_____) )  _  | |   | |_      | |__ | |_____   ");
        System.out.println("| |      / /\\ \\|  __  ( \\_____/(_____ (  | | | |   | | |     |  __)| |  ___)");
        System.out.println("| |_____| |__| | |__)  )  ___        | |_| |_| |   | | |_____| |   | | |_____ ");
        System.out.println("|_______)______|______/  (___)       |_(_____)_|   |_|\\______)_|   |_|_______)");
        System.out.println("\n - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n");

        LargeurDAbord algoLargeur = new LargeurDAbord();
        ProfondeurDAbord algoPronfondeur = new ProfondeurDAbord();
        int[][] monde = new int[][]{
                {1, 1, 1, 1, 1, 1, 1}, {1, 0, 0, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 1, 1}, {1, 0, 0, 1, 0, 1, 1}, {1, 0, 1, 1, 0, 1, 1},

                {1, 0, 0, 0, 1, 1, 1}, {1, 1, 0, 0, 1, 1, 1}, {1, 0, 0, 0, 0, 1, 1}, {1, 0, 1, 0, 1, 1, 1},

                {1, 0, 1, 0, 0, 0, 1}, {1, 0, 1, 1, 1, 0, 1}, {1, 0, 0, 0, 0, 1, 1}, {1, 1, 1, 1, 1, 1, 1}
        };
        Labyrinthe.setMonde(monde);


        /** - - -  TEST  - - - */
        Labyrinthe laby1 = new Labyrinthe(1, 1);
        Labyrinthe etatFinal1 = new Labyrinthe(10, 5);
        Etat.setEtatFinal(etatFinal1);


        if (algoPronfondeur.existeChemin(laby1))
            System.out.println("\n ___ PROFONDEUR ___ \n Y a t-il un chemin ? " + algoPronfondeur.existeChemin(laby1));

        System.out.println(laby1);
        Labyrinthe.setMonde(monde);

        // - - - - - - -
        if (algoLargeur.existeChemin(laby1))
            System.out.println("\n ___ LARGEUR ___ \n Y a t-il un chemin ? " + algoLargeur.existeChemin(laby1));


        System.out.println(laby1);
        Labyrinthe.setMonde(monde);

        // - - - - - - -



        // Deuxieme Test
        System.out.println("\n- - - Changement du point de départ - - - ");
        laby1 = new Labyrinthe(monde.length-4, monde[0].length-2);

        if (algoPronfondeur.existeChemin(laby1))
            System.out.println("\n ___ PROFONDEUR ___ \n Y a t-il un chemin ? " + algoPronfondeur.existeChemin(laby1));


        System.out.println(laby1);
        Labyrinthe.setMonde(monde);

        // - - - - - - -
        if (algoLargeur.existeChemin(laby1))
            System.out.println("\n ___ LARGEUR ___ \n Y a t-il un chemin ? " + algoLargeur.existeChemin(laby1));


        System.out.println(laby1);
        Labyrinthe.setMonde(monde);



        /** ------------------------------ TEST COMPARATEUR TEMPS ------------------------------------------- */

        long debutProfondeur = System.nanoTime();
        algoPronfondeur.existeChemin(laby1);
        double tempsProfondeur = ( System.nanoTime() - debutProfondeur ) / 1000000.0;;

        // - - - - - - -

        long debutLargeur = System.nanoTime();
        algoLargeur.existeChemin(laby1);
        double tempsLargeur = ( System.nanoTime() - debutLargeur ) / 1000000.0;


        // - - - - - - -

        System.out.println("RESULTAT AVEC CHEMIN : ");
        System.out.println("Temps Profondeur : " + tempsProfondeur + "ms");
        System.out.println("Temps Largeur : " + tempsLargeur + "ms");
        System.out.println("\n---- TEST SUIVANT : SANS CHEMIN ---- ");


        // Deuxieme Test
        laby1 = new Labyrinthe(monde.length-4, monde[0].length-2);


        debutProfondeur = System.nanoTime();
        algoPronfondeur.existeChemin(laby1);
        tempsProfondeur = ( System.nanoTime() - debutProfondeur ) / 1000000.0;

        // - - - - - - -

        debutLargeur = System.nanoTime();
        algoLargeur.existeChemin(laby1);
        tempsLargeur = ( System.nanoTime() - debutLargeur ) / 1000000.0;


        // - - - - - - -

        System.out.println("RESULTAT SANS CHEMIN : ");
        System.out.println("Temps Profondeur : " + tempsProfondeur + "ms");
        System.out.println("Temps Largeur : " + tempsLargeur + "ms");


    }



}
