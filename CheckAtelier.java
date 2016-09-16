package projetBPO;

import projetBPO.algos.IRecherche;
import projetBPO.algos.LargeurDAbord;
import projetBPO.algos.ProfondeurDAbord;
import projetBPO.jeux.Bloc;
import projetBPO.jeux.Labyrinthe;
import projetBPO.jeux.Robot;

public class CheckAtelier {
    static String message = "Attendu : -l/-r -l/-p numéroDeTest entre 1 et 4";

    public static void main(String[] args) {
        try {
            new CheckAtelier(args);
        } catch (NumberFormatException var2) {
            System.out.println("Entier incorrect. " + message);
        } catch (IllegalArgumentException var3) {
            System.out.println(var3.getMessage());
        }

    }

    public CheckAtelier(String[] args) throws IllegalArgumentException {
        byte nbTests = 4;
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

    private void testLabyrinthe(IRecherche algo, int noTest) {
        System.out.println("Test numéro " + noTest + " de Labyrinthe avec " + algo.getClass().getName());
        int[][] monde1 = new int[][]{{0, 1, 0, 0}, {1, 0, 0, 0}, {0, 0, 1, 1}, {0, 0, 0, 0}};
        int[][] monde3 = new int[][]{{0, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 0, 1}, {0, 0, 0, 1, 0, 0}, {1, 0, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0}};
        int[][][] lesMondes = new int[][][]{(int[][])null, monde1, monde1, monde3, monde3};
        Labyrinthe laby1 = new Labyrinthe(3, 1);
        Labyrinthe etatFinal1 = new Labyrinthe(0, 3);
        Labyrinthe laby2 = new Labyrinthe(3, 1);
        Labyrinthe etatFinal2 = new Labyrinthe(0, 0);
        Labyrinthe laby3 = new Labyrinthe(2, 2);
        Labyrinthe etatFinal3 = new Labyrinthe(0, 2);
        Labyrinthe laby4 = new Labyrinthe(2, 2);
        Labyrinthe etatFinal4 = new Labyrinthe(2, 1);
        Labyrinthe[] lesLabys = new Labyrinthe[]{null, laby1, laby2, laby3, laby4};
        Labyrinthe[] lesArrivees = new Labyrinthe[]{null, etatFinal1, etatFinal2, etatFinal3, etatFinal4};
        Labyrinthe.setEtatFinal(lesArrivees[noTest]);
        Labyrinthe.setMonde(lesMondes[noTest]);
        System.out.println("le labyrinthe :\n" + lesLabys[noTest]);
        System.out.println("départ : ");
        switch(noTest) {
            case 1:
            case 2:
                System.out.println("(3, 1)");
                break;
            case 3:
            case 4:
                System.out.println("(2, 2)");
        }

        System.out.println("arrivée : ");
        switch(noTest) {
            case 1:
                System.out.println("(0, 3)");
                break;
            case 2:
                System.out.println("(0, 0) ;");
                break;
            case 3:
                System.out.println("(0, 2)");
                break;
            case 4:
                System.out.println("(2, 1)");
        }

        if(algo.existeChemin(lesLabys[noTest])) {
            System.out.println("Il existe un chemin.");
        } else {
            System.out.println("Pas trouvé de chemin.");
        }

    }

    private void testRobot(IRecherche algo, int noTest) {
        Bloc b1 = new Bloc("rouge", 70, 10);
        Bloc b2 = new Bloc("bleu", 20, 5);
        Bloc b3 = new Bloc("vert", 21, 5);
        Bloc b4 = new Bloc("orange", 70, 10);
        Bloc b5 = new Bloc("turquoise", 20, 5);
        Bloc b6 = new Bloc("jaune", 70, 10);
        Bloc b7 = new Bloc("outremer", 20, 5);
        Bloc b8 = new Bloc("beige", 70, 10);
        Bloc b9 = new Bloc("rose", 70, 10);
        Bloc b10 = new Bloc("cyan", 20, 25);
        Robot robot1 = new Robot(1, new Bloc[]{b1, b2, b3});
        Robot robot2 = new Robot(3, new Bloc[]{b1});
        Robot robot3 = new Robot(6, new Bloc[]{b1, b2, b3, b4, b5, b6, b7, b8, b9});
        Robot robot4 = new Robot(3, new Bloc[]{b1, b10, b3});
        Robot[] lesRobots = new Robot[]{null, robot1, robot2, robot3, robot4};
        System.out.println("les blocs du robot :");
        switch(noTest) {
            case 1:
                System.out.println("" + b1 + b2 + b3);
                break;
            case 2:
                System.out.println("" + b1);
                break;
            case 3:
                System.out.println("" + b1 + b2 + b3 + b4 + b5 + b6 + b7 + b8 + b9);
                break;
            case 4:
                System.out.println("" + b1 + b10 + b3);
        }

        System.out.print("nombre de blocs à empiler : ");
        switch(noTest) {
            case 1:
                System.out.println("3");
                break;
            case 2:
                System.out.println("1");
                break;
            case 3:
                System.out.println("6");
                break;
            case 4:
                System.out.println("3");
        }

        if(algo.existeChemin(lesRobots[noTest])) {
            System.out.println("Il existe un chemin.");
        } else {
            System.out.println("Pas trouvé de chemin.");
        }

    }
}
