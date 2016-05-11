package projetBPO.algos;

import projetBPO.jeux.Etat;

/**
 * @author Xavier CHOPIN
 */
public interface IRecherche{

    /**
     * Fonction itère sur tous les états qu'il est possible d'atteindre en une action à partir de
     * l'état passé en paramètre. L'itération s'arrête dès qu'on constate que l'un de ces états mène à l'état
     * final.
     * @param etat : état à tester
     * @return boolean
     */
    boolean existeChemin(Etat etat);

}
