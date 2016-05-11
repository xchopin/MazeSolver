package projetBPO;


import projetBPO.algos.IRecherche;
import projetBPO.algos.LargeurDAbord;
import projetBPO.algos.ProfondeurDAbord;
import projetBPO.jeux.*;

import java.util.Scanner;

/**
 * Created by Xavier on 30/04/2016.
 */
public class Main {

    //On ne va pas run de commande OS car sur Java c'est trop complexe pour faire un clear pour chaque OS (UNIX,XP,>Win7,etc...)
    public static void clear() {
        for (int i = 0 ; i < 25 ; i ++ ){
            System.out.println("\n");
        }
        System.out.flush();
    }


    public static void mazeDFS(){
        System.out.println(" _              ______  _     _ ______  _____ ______  _______ _     _ _______");
        System.out.println("| |        /\\  (____  \\| |   | (_____ \\(_____)  ___ \\(_______) |   | (_______)");
        System.out.println("| |       /  \\  ____)  ) |___| |_____) )  _  | |   | |_      | |__ | |_____   ");
        System.out.println("| |      / /\\ \\|  __  ( \\_____/(_____ (  | | | |   | | |     |  __)| |  ___)");
        System.out.println("| |_____| |__| | |__)  )  ___        | |_| |_| |   | | |_____| |   | | |_____ ");
        System.out.println("|_______)______|______/  (___)       |_(_____)_|   |_|\\______)_|   |_|_______)");
        System.out.println("\n - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n");
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

        System.out.println("\n ___ PROFONDEUR ___ \n Départ (1,1) - Fin (10,5) \n" +
                    "Y a t-il un chemin ? " + algoPronfondeur.existeChemin(laby1));

        System.out.println(laby1);
        Labyrinthe.setMonde(monde);


        // Deuxieme Test
        System.out.println("\n- - - Changement du point de départ - - - ");
        laby1 = new Labyrinthe(monde.length-4, monde[0].length-2);
            System.out.println("\n ___ PROFONDEUR ___ \n Départ (9,5) - Fin (10,5) \n" +
                    " Y a t-il un chemin ? " + algoPronfondeur.existeChemin(laby1));


        System.out.println(laby1);
        Labyrinthe.setMonde(monde);

    }

    public static void mazeBFS(){
        System.out.println(" _              ______  _     _ ______  _____ ______  _______ _     _ _______");
        System.out.println("| |        /\\  (____  \\| |   | (_____ \\(_____)  ___ \\(_______) |   | (_______)");
        System.out.println("| |       /  \\  ____)  ) |___| |_____) )  _  | |   | |_      | |__ | |_____   ");
        System.out.println("| |      / /\\ \\|  __  ( \\_____/(_____ (  | | | |   | | |     |  __)| |  ___)");
        System.out.println("| |_____| |__| | |__)  )  ___        | |_| |_| |   | | |_____| |   | | |_____ ");
        System.out.println("|_______)______|______/  (___)       |_(_____)_|   |_|\\______)_|   |_|_______)");
        System.out.println("\n - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n");

        LargeurDAbord algoLargeur = new LargeurDAbord();
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


        // - - - - - - -
        System.out.println("\n ___ LARGEUR ___ \n Départ (1,1) - Fin (10,5) \n" +
                "  Y a t-il un chemin ? " + algoLargeur.existeChemin(laby1));
        System.out.println(laby1);
        Labyrinthe.setMonde(monde);


        // Deuxieme Test

        System.out.println("\n- - - Changement du point de départ - - - ");
        laby1 = new Labyrinthe(monde.length-4, monde[0].length-2);
        System.out.println("\n ___ LARGEUR ___ \n Départ (9,5) - Fin (10,5) \n" +
                " Y a t-il un chemin ? " + algoLargeur.existeChemin(laby1));
        System.out.println(laby1);
        Labyrinthe.setMonde(monde);



    }

    public static void chronoMaze(){

        System.out.println(
                        "    .-._;.--.;_.-.\n" +
                        "   (_.'_..--.._'._)\n" +
                        "    /.' . 60 . '.\\\n" +
                        "   // .      / . \\\\\n" +
                        "  |; .      /   . |;\n" +
                        "  ||45    ()    15||\n" +
                        "  |; .          . |;\n" +
                        "   \\\\ .        . //\n" +
                        "    \\'._' 30 '_.'/\n" +
                        "     '-._'--'_.-'\n" +
                        "         `\"\"` \n" +
                        "   - CHRONO LABY - \n  "

        );


        ProfondeurDAbord algoPronfondeur = new ProfondeurDAbord();
        LargeurDAbord algoLargeur = new LargeurDAbord();

        int[][] monde = new int[][]{
                {1, 1, 1, 1, 1, 1, 1}, {1, 0, 0, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 1, 1}, {1, 0, 0, 1, 0, 1, 1}, {1, 0, 1, 1, 0, 1, 1},

                {1, 0, 0, 0, 1, 1, 1}, {1, 1, 0, 0, 1, 1, 1}, {1, 0, 0, 0, 0, 1, 1}, {1, 0, 1, 0, 1, 1, 1},

                {1, 0, 1, 0, 0, 0, 1}, {1, 0, 1, 1, 1, 0, 1}, {1, 0, 0, 0, 0, 1, 1}, {1, 1, 1, 1, 1, 1, 1}
        };
        Labyrinthe.setMonde(monde);



        Labyrinthe laby1 = new Labyrinthe(1, 1);
        Labyrinthe etatFinal1 = new Labyrinthe(10, 5);
        Etat.setEtatFinal(etatFinal1);


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

    public static void robotDFS(){

        System.out.println(
                "                         .andAHHAbnn.\n" +
                "                      .aAHHHAAUUAAHHHAn.\n" +
                "                     dHP^~\"        \"~^THb.\n" +
                "               .   .AHF                YHA.   .\n" +
                "               |  .AHHb.              .dHHA.  |\n" +
                "               |  HHAUAAHAbn      adAHAAUAHA  |\n" +
                "               I  HF~\"_____        ____ ]HHH  I\n" +
                "              HHI HAPK\"\"~^YUHb  dAHHHHHHHHHH IHH\n" +
                "              HHI HHHD> .andHH  HHUUP^~YHHHH IHH\n" +
                "              YUI ]HHP     \"~Y  P~\"     THH[ IUP\n" +
                "               \"  `HK                   ]HH'  \"\n" +
                "                   THAn.  .d.aAAn.b.  .dHHP\n" +
                "                   ]HHHHAAUP\" ~~ \"YUAAHHHH[\n" +
                "                   `HHP^~\"  .annn.  \"~^YHH'\n" +
                "                    YHb    ~\" \"\" \"~    dHF\n" +
                "                     \"YAb..abdHHbndbndAP\"\n" +
                "                      THHAAb.  .adAHHF\n" +
                "                       \"UHHHHHHHHHHU\"\n" +
                "                         ]HHUUHHHHHH[\n" +
                "                       .adHHb \"HHHHHbn.\n" +
                "                ..andAAHHHHHHb.AHHHHHHHAAbnn..\n" +
                "           .ndAAHHHHHHUUHHHHHHHHHHUP^~\"~^YUHHHAAbn.\n" +
                "             \"~^YUHHP\"   \"~^YUHHUP\"        \"^YUP^\"\n" +
                "                  \"\"         \"~~\" \n" +
                "               ________________________________ \n" +
                "                         JEU DU ROBOT \n ");
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



        System.out.println("\nTest de l'algo en profondeur : \n      - - - - - - - -");
        System.out.println("1) ROBOT DE BASE : " + robot4 + "\n");
        if (algoProfondeur.existeChemin(robot4)) {
            System.out.println("2) RESULTAT : Un chemin est disponible \n");
            System.out.println("3) SOLUTION DU ROBOT : " + robot4.getSolution() + "\n");
        }else{
            System.out.println("2) RESULTAT : Pas de chemin");
        }


    }

    public static void robotBFS(){

        System.out.println(
                "                         .andAHHAbnn.\n" +
                        "                      .aAHHHAAUUAAHHHAn.\n" +
                        "                     dHP^~\"        \"~^THb.\n" +
                        "               .   .AHF                YHA.   .\n" +
                        "               |  .AHHb.              .dHHA.  |\n" +
                        "               |  HHAUAAHAbn      adAHAAUAHA  |\n" +
                        "               I  HF~\"_____        ____ ]HHH  I\n" +
                        "              HHI HAPK\"\"~^YUHb  dAHHHHHHHHHH IHH\n" +
                        "              HHI HHHD> .andHH  HHUUP^~YHHHH IHH\n" +
                        "              YUI ]HHP     \"~Y  P~\"     THH[ IUP\n" +
                        "               \"  `HK                   ]HH'  \"\n" +
                        "                   THAn.  .d.aAAn.b.  .dHHP\n" +
                        "                   ]HHHHAAUP\" ~~ \"YUAAHHHH[\n" +
                        "                   `HHP^~\"  .annn.  \"~^YHH'\n" +
                        "                    YHb    ~\" \"\" \"~    dHF\n" +
                        "                     \"YAb..abdHHbndbndAP\"\n" +
                        "                      THHAAb.  .adAHHF\n" +
                        "                       \"UHHHHHHHHHHU\"\n" +
                        "                         ]HHUUHHHHHH[\n" +
                        "                       .adHHb \"HHHHHbn.\n" +
                        "                ..andAAHHHHHHb.AHHHHHHHAAbnn..\n" +
                        "           .ndAAHHHHHHUUHHHHHHHHHHUP^~\"~^YUHHHAAbn.\n" +
                        "             \"~^YUHHP\"   \"~^YUHHUP\"        \"^YUP^\"\n" +
                        "                  \"\"         \"~~\" \n" +
                        "               ________________________________ \n" +
                        "                         JEU DU ROBOT \n ");
        LargeurDAbord algoLargeur = new LargeurDAbord();

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


        System.out.println("\nTest de l'algo en largeur : \n      - - - - - - - -");
        System.out.println("1) ROBOT DE BASE : " + robot4 + "\n");
        if (algoLargeur.existeChemin(robot4)) {
            System.out.println("2) RESULTAT : Un chemin est disponible \n");
            System.out.println("3) SOLUTION DU ROBOT : " + robot4.getSolution() + "\n");
        }else{
            System.out.println("2) RESULTAT : Pas de chemin");
        }


    }

    public static void chronoRobot() {

        System.out.println(
                "    .-._;.--.;_.-.\n" +
                        "   (_.'_..--.._'._)\n" +
                        "    /.' . 60 . '.\\\n" +
                        "   // .      / . \\\\\n" +
                        "  |; .      /   . |;\n" +
                        "  ||45    ()    15||\n" +
                        "  |; .          . |;\n" +
                        "   \\\\ .        . //\n" +
                        "    \\'._' 30 '_.'/\n" +
                        "     '-._'--'_.-'\n" +
                        "         `\"\"` \n" +
                        "   - CHRONO ROBOT - \n  "

        );

        LargeurDAbord algoLargeur = new LargeurDAbord();
        ProfondeurDAbord algoProfondeur = new ProfondeurDAbord();
        Bloc b4 = new Bloc("orange", 20, 1);
        Bloc b5 = new Bloc("jaune", 100, 21);
        Robot robot42 = new Robot(2, new Bloc[]{b4,b5});


        long debutLargeur = System.nanoTime();
        algoLargeur.existeChemin(robot42);
        double tempsLargeur = ( System.nanoTime() - debutLargeur ) / 1000000.0;

        long debutProfondeur = System.nanoTime();
        algoProfondeur.existeChemin(robot42);
        double tempsProfondeur = ( System.nanoTime() - debutProfondeur) / 1000000.0;


        System.out.println("Temps Profondeur : " + tempsProfondeur + "ms");
        System.out.println("Temps Largeur : " + tempsLargeur + "ms");
    }

    public static void runCheck(){
        System.out.println(
                "            _____ _               _      _            _   \n" +
                "           /  __ \\ |             | |    | |          | |  \n" +
                "           | /  \\/ |__   ___  ___| | __ | |_ ___  ___| |_ \n" +
                "           | |   | '_ \\ / _ \\/ __| |/ / | __/ _ \\/ __| __|\n" +
                "           | \\__/\\ | | |  __/ (__|   <  | ||  __/\\__ \\ |_ \n" +
                "            \\____/_| |_|\\___|\\___|_|\\_\\  \\__\\___||___/\\__|\n" +
                "\n                         -------------------------              \n" +
                        "/!\\ Attention n'oubliez pas le '-' pour les deux premiers args \n  ");


        String[] tab = new String[3];

        Scanner scan = new Scanner(System.in);
        System.out.println("Entrez le premier argument : '-l' ou '-r' : ");
        tab[0] = scan.nextLine();

        scan = new Scanner(System.in);
        System.out.println("Entrez le deuxieme argument : '-l' ou '-p' : ");
        tab[1] = scan.nextLine();

        scan = new Scanner(System.in);
        System.out.println("Entrez le troisieme argument : '1' ou '2' : ");
        tab[2] = scan.nextLine();

        try{
            testCheck(tab);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void testCheck(String[] args){
         String message = "Attendu : -l/-r -l/-p numéroDeTest entre 1 et 2";
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
                                    Check.testLabyrinthe((IRecherche)algo, noTest);
                                } else {
                                    Check.testRobot((IRecherche)algo, noTest);
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


    public static void main(String[] args) {


        System.out.println(
                " \n \n \n" +
                " ██▓███   ██▀███   ▒█████   ▄▄▄██▀▀▀▓█████▄▄▄█████▓       ▄▄▄▄    ██▓███   ▒█████  \n" +
                "▓██░  ██▒▓██ ▒ ██▒▒██▒  ██▒   ▒██   ▓█   ▀▓  ██▒ ▓▒      ▓█████▄ ▓██░  ██▒▒██▒  ██▒\n" +
                "▓██░ ██▓▒▓██ ░▄█ ▒▒██░  ██▒   ░██   ▒███  ▒ ▓██░ ▒░      ▒██▒ ▄██▓██░ ██▓▒▒██░  ██▒\n" +
                "▒██▄█▓▒ ▒▒██▀▀█▄  ▒██   ██░▓██▄██▓  ▒▓█  ▄░ ▓██▓ ░       ▒██░█▀  ▒██▄█▓▒ ▒▒██   ██░\n" +
                "▒██▒ ░  ░░██▓ ▒██▒░ ████▓▒░ ▓███▒   ░▒████▒ ▒██▒ ░       ░▓█  ▀█▓▒██▒ ░  ░░ ████▓▒░\n" +
                "▒▓▒░ ░  ░░ ▒▓ ░▒▓░░ ▒░▒░▒░  ▒▓▒▒░   ░░ ▒░ ░ ▒ ░░         ░▒▓███▀▒▒▓▒░ ░  ░░ ▒░▒░▒░ \n" +
                "░▒ ░       ░▒ ░ ▒░  ░ ▒ ▒░  ▒ ░▒░    ░ ░  ░   ░          ▒░▒   ░ ░▒ ░       ░ ▒ ▒░ \n" +
                "░░         ░░   ░ ░ ░ ░ ▒   ░ ░ ░      ░    ░             ░    ░ ░░       ░ ░ ░ ▒  \n" +
                "            ░         ░ ░   ░   ░      ░  ░               ░                   ░ ░  \n" +
                "                                                               ░                   \n\n" +
                "       ╔═╗╦═╗╔═╗╔═╗╔╦╗╔═╗╔╦╗  ╔╗ ╦ ╦  ═╗ ╦╔═╗╦  ╦╦╔═╗╦═╗  ╔═╗╦ ╦╔═╗╔═╗╦╔╗╔     \n" +
                "       ║  ╠╦╝╠═╣╠╣  ║ ║╣  ║║  ╠╩╗╚╦╝  ╔╩╦╝╠═╣╚╗╔╝║║╣ ╠╦╝  ║  ╠═╣║ ║╠═╝║║║║     \n" +
                "       ╚═╝╩╚═╩ ╩╚   ╩ ╚═╝═╩╝  ╚═╝ ╩   ╩ ╚═╩ ╩ ╚╝ ╩╚═╝╩╚═  ╚═╝╩ ╩╚═╝╩  ╩╝╚╝     \n" +
                "       ___________________________________________________________________     \n" +
                "\n" +
                "Bienvenue dans le programme du Projet BPO : veuillez choisir le test voulu.     \n" +
                "1 - Test d'un labyrinthe avec l'algorithme DFS \n" +
                "2 - Test d'un labyrinthe avec l'algorithme BFS \n" +
                "3 - Comparaison de la vitesse d'execution entre les deux algorithmes sur un labyrinthe\n" +
                "  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n" +
                "4 - Test d'un jeu du robot avec l'algorithme DFS \n" +
                "5 - Test d'un jeu du robot avec l'algorithme BFS \n" +
                "6 - Comparaison de la vitesse d'execution entre les deux algorithmes sur le jeu du robot\n" +
                "  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n" +
                "7 - Lancer CheckTest (test crée par les professeurs) \n\n" +
                "Votre choix : "
            );



                Scanner sc = new Scanner(System.in);
                String message = sc.nextLine();
                clear();

                switch (message){
                    case "1":
                        mazeDFS();
                        break;
                    case "2":
                        mazeBFS();
                        break;
                    case "3":
                        chronoMaze();
                        break;
                    case "4":
                        robotDFS();
                        break;
                    case "5":
                        robotBFS();
                        break;
                    case "6":
                        chronoRobot();
                        break;
                    case "7":
                        runCheck();
                        break;

                    default:
                        clear();
                        System.out.println("Mauvais argument, nouvel essai dans 2 secondes");

                        try {
                            Thread.sleep(2000);
                        } catch (Exception e) {
                            System.out.println(e);
                        }


                        main(args);

                        break;
                }

















    }







}
