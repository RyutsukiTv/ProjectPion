


import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.*;
import java.net.*;


/**
 * Creation de la classe Server
 */
public class TPServer {
    static int compteur = 0;

    public static void main(String[] args) throws IOException {
        /** Server ecoute sur le port 7000 */

        ServerSocket ss = new ServerSocket(7000);
        int[] etat = new int[2 * 10 * 10];

        /** Affichage confimation lancement serveur */


        System.out.println(
                "=======================================\n" +
                "========================@==============\n" +
                "===========888=888=888  @@@============\n" +
                "===========88888888888 @@@@@@@=========\n" +
                "===========8888888888 @@@@@  @@========\n" +
                "===========888888888 @@@@@@@@@@@=======\n" +
                "===========888888888 @@@@@@@@@@@=======\n" +
                "===========8888888888  @@@@@==@@=======\n" +
                "===========88888888888 @@@@@@==========\n" +
                "===========8888888888 @@@@@@@@=========\n" +
                "===========888888888 @@@@@@@@@@========\n" +
                "=====================@@@@@@@@@@========\n" +
                "=======================================\n" );
        System.out.println(
                "===============RUN AND KILL===========\n" );
        System.out.println(
                "initialisation de la partie, en attente de joueur\n" );

        /** Client arrive */


        while (true) {
            Socket s = null;

            try {
                /** Pour les requests des clients (Bas, Haut, ...) */
                s = ss.accept();
                compteur++;
                /** Pour lire et ecrire (les input et output) du client */
                DataOutputStream toClient = new DataOutputStream(s.getOutputStream());
                DataInputStream fromClient = new DataInputStream(s.getInputStream());

                System.out.println("Un nouveau Joueur de connecte");

                /** Creation des Threads */
                TPClientCommande TPClientCommande = new TPClientCommande(etat, s, fromClient, toClient,compteur);
                Thread t = new Thread(TPClientCommande);

                /** Methode Start */
                t.start();

            } catch (Exception e) {
                s.close();
                e.printStackTrace();
            }
        }
    }
}
