package projetBPO;

import projetBPO.algos.IRecherche;
import projetBPO.algos.LargeurDAbord;
import projetBPO.algos.ProfondeurDAbord;
import projetBPO.jeux.Bloc;
import projetBPO.jeux.Labyrinthe;
import projetBPO.jeux.Robot;

public class Check {
    static String message = "Attendu : -l/-r -l/-p numéroDeTest entre 1 et 2";

    public static void main(String[] args) {
        try {
            new Check(args);
        } catch (NumberFormatException var2) {
            System.out.println("Entier incorrect. " + message);
        } catch (IllegalArgumentException var3) {
            System.out.println(var3.getMessage());
        }

    }

    public Check(String[] args) throws IllegalArgumentException {
        byte nbTests = 2;
        if(args != null && args.length == 3) {
            String choixJeu = args[0];
            String choixAlgo = args[1];
            if(choixJeu.length() == 2 && choixAlgo.length() == 2) {
                if(choixJeu.charAt(0) == 45 && choixAlgo.charAt(0) == 45) {
                    char lettreAlgo = choixAlgo.charAt(1);
                    if(lettreAlgo != 112 && lettreAlgo != 108) {
                        throw new IllegalArgumentException("Option d\'algo incorrecte. " + message);
                    } else {
                        Object algo;
                        if(lettreAlgo == 112) {
                            algo = new ProfondeurDAbord();
                        } else {
                            algo = new LargeurDAbord();
                        }

                        int noTest = Integer.parseInt(args[2]);
                        if(noTest >= 0 && noTest <= nbTests) {
                            char lettreJeu = choixJeu.charAt(1);
                            if(lettreJeu != 108 && lettreJeu != 114) {
                                throw new IllegalArgumentException("Option de jeu incorrecte. " + message);
                            } else {
                                if(lettreJeu == 108) {
                                    this.testLabyrinthe((IRecherche)algo, noTest);
                                } else {
                                    this.testRobot((IRecherche)algo, noTest);
                                }

                            }
                        } else {
                            throw new IllegalArgumentException("Numéro de test incorrect. " + message);
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Options incorrectes. " + message);
                }
            } else {
                throw new IllegalArgumentException("Paramètres incorrect. " + message);
            }
        } else {
            throw new IllegalArgumentException("Nombre d\'arguments incorrect. " + message);
        }
    }

    public static void testLabyrinthe(IRecherche algo, int noTest) {
        System.out.println("Test numéro " + noTest + " de Labyrinthe avec " + algo.getClass().getName());
        int[][] monde = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 1}, {0, 0, 1, 0}, {1, 1, 0, 0}};
        int[][][] lesMondes = new int[][][]{(int[][])null, monde, monde};
        Labyrinthe laby1 = new Labyrinthe(0, 0);
        Labyrinthe etatFinal1 = new Labyrinthe(1, 2);
        Labyrinthe laby2 = new Labyrinthe(0, 0);
        Labyrinthe etatFinal2 = new Labyrinthe(3, 3);
        Labyrinthe[] lesLabys = new Labyrinthe[]{null, laby1, laby2};
        Labyrinthe[] lesArrivees = new Labyrinthe[]{null, etatFinal1, etatFinal2};
        Labyrinthe.setEtatFinal(lesArrivees[noTest]);
        Labyrinthe.setMonde(lesMondes[noTest]);
        System.out.println("le labyrinthe :\n" + lesLabys[noTest]);
        System.out.println("départ : (0, 0)");
        System.out.print("arrivée : ");
        if(noTest == 1) {
            System.out.println("(1, 2)");
        } else {
            System.out.println("(3, 3)");
        }

        if(algo.existeChemin(lesLabys[noTest])) {
            System.out.println("Il existe un chemin.");
        } else {
            System.out.println("Pas trouvé de chemin.");
        }

    }

    public static void testRobot(IRecherche algo, int noTest) {
        Bloc b1 = new Bloc("rouge", 70, 10);
        Bloc b2 = new Bloc("bleu", 20, 5);
        Bloc b3 = new Bloc("vert", 21, 5);
        Robot robot1 = new Robot(1, new Bloc[]{b1});
        Robot robot2 = new Robot(3, new Bloc[]{b1, b2, b3});
        Robot[] lesRobots = new Robot[]{null, robot1, robot2};
        System.out.println("les blocs du robot :");
        if(noTest == 1) {
            System.out.println("" + b1);
        } else {
            System.out.println("" + b1 + b2 + b3);
        }

        System.out.print("nombre de blocs à empiler : ");
        if(noTest == 1) {
            System.out.println(1);
        } else {
            System.out.println(3);
        }

        if(algo.existeChemin(lesRobots[noTest])) {
            System.out.println("Il existe un chemin.");
        } else {
            System.out.println("Pas trouvé de chemin.");
        }

    }
}
