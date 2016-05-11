package projetBPO.jeux;

/**
 * Created by Xavier on 05/04/2016.
 */
public class Bloc  {
    protected String nom;
    protected int masse, largeur;

    public Bloc(String nom, int masse, int largeur){
        this.nom = nom;
        this.masse = masse;
        this.largeur = largeur;
    }

    /**
     * fonction qui affiche les infos d'un bloc
     * @return string
     */
    @Override
    public String toString() {
        return "Bloc{" +
                "nom='" + nom + '\'' +
                ", masse=" + masse +
                ", largeur=" + largeur +
                '}';
    }
}
