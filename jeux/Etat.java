package projetBPO.jeux;

/**
 * @author Xavier CHOPIN
 */

public abstract class Etat implements Iterable<Etat> {

    private static Etat etatFinal;

    /** Fonction permettant de savoir si cet état est final */
    public boolean estFinal() {
        return this.equals(etatFinal);
    }


    /** - - - GETTER & SETTER - - - */

    public static Etat getEtatFinal(){
        return etatFinal;
    }

    public static void setEtatFinal(Etat etat){
        etatFinal = etat;
    }


}





