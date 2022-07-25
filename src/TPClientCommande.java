
import java.net.*;
import java.io.*;
import java.lang.*;
import javax.swing.*;
/**
 * Creation de la classe ClientHandler
 */

public class TPClientCommande implements Runnable {

    /**
     * Variables pour communiquer avec Client
     */

    private int[] etat;
    DataOutputStream toClient;
    DataInputStream fromClient;
    final Socket s;

    boolean mort =false;
    int compteur;
    /**
     * Constructeur
     */

    public TPClientCommande(int[] etat, Socket s, DataInputStream fromClient, DataOutputStream toClient, int compteur) {
        this.etat = etat;
        this.s = s;
        this.fromClient = fromClient;
        this.toClient = toClient;
        this.compteur = compteur;
    }

    public void run() {
        try {
            /** Connexion et lecture des variables */

            toClient.writeUTF("connexion");
            int x = fromClient.read();
            int y = fromClient.read();
            int team = fromClient.read();

            this.etat[2 * ((y - 1) * 10 + (x - 1))] = 1;
            this.etat[2 * ((y - 1) * 10 + (x - 1)) + 1] = team;

            toClient.writeInt(this.etat.length);

            for (int i = 0; i < this.etat.length; i++) {
                toClient.writeInt(this.etat[i]);
            }



            while (true) {
                String action = fromClient.readUTF();
                                /** Action de déplacement */
                switch (action) {
                    case "Droite":
                        if (this.etat[2 * ((y - 1) * 10 + (x - 1))] != 0) {
                            if (x != 10) {
                                if (this.etat[2 * ((y - 1) * 10 + (x + 1 - 1))] != 1 && this.etat[2 * ((y - 1) * 10 + (x - 1)) + 1] != 2) {
                                    this.etat[2 * ((y - 1) * 10 + (x - 1))] = 0;
                                    this.etat[2 * ((y - 1) * 10 + (x - 1)) + 1] = 0;
                                    if (x != 10) {
                                        x += 1;  /** x + 1 donc vers la droite */
                                    }
                                    this.etat[2 * ((y - 1) * 10 + (x - 1))] = 1;
                                    this.etat[2 * ((y - 1) * 10 + (x - 1)) + 1] = team;
                                }
                                if (x != 9) {
                                    this.RegardeBas(team, x, y);
                                }
                            }
                            if (x != 1 && x != 2)
                                this.RegardeGauche(team, x, y);
                            if (x != 10 && x != 9)
                                this.RegardeDroite(team, x, y);
                            if (y != 1 && y != 2)
                                this.RegardeHaut(team, x, y);

                            this.MortHautBas(team, x, y);
                        }
                        toClient.writeInt(this.etat.length);

                        for (int i = 0; i < this.etat.length; i++) {
                            toClient.writeInt(this.etat[i]);
                        }

                        break;
                    case "Gauche":
                        if (this.etat[2 * ((y - 1) * 10 + (x - 1))] != 0) {

                            if (x != 1) {
                                if (this.etat[2 * ((y - 1) * 10 + (x - 1 - 1))] != 1 && this.etat[2 * ((y - 1) * 10 + (x - 1)) + 1] != 2) {
                                    this.etat[2 * ((y - 1) * 10 + (x - 1))] = 0;
                                    this.etat[2 * ((y - 1) * 10 + (x - 1)) + 1] = 0;
                                    if (x != 1) {
                                        x -= 1; /** x - 1 donc vers la gauche */
                                    }
                                    this.etat[2 * ((y - 1) * 10 + (x - 1))] = 1;
                                    this.etat[2 * ((y - 1) * 10 + (x - 1)) + 1] = team;
                                }
                                if (x != 2) {
                                    this.RegardeGauche(team, x, y);
                                }
                            }

                            if (x != 10 && x != 9)
                                this.RegardeDroite(team, x, y);
                            if (y != 1 && y != 2)
                                this.RegardeHaut(team, x, y);
                            if (y != 10 && y != 9)
                                this.RegardeBas(team, x, y);

                            this.MortHautBas(team, x, y);
                        }

                        toClient.writeInt(this.etat.length);
                        for (int i = 0; i < this.etat.length; i++) {
                            toClient.writeInt(this.etat[i]);
                        }
                        break;
                    case "Haut":
                        if (this.etat[2 * ((y - 1) * 10 + (x - 1))] != 0) {
                            if (y != 1) {
                                if (this.etat[2 * ((y - 1 - 1) * 10 + (x - 1))] != 1 && this.etat[2 * ((y - 1) * 10 + (x - 1)) + 1] != 2) {
                                    this.etat[2 * ((y - 1) * 10 + (x - 1))] = 0;
                                    this.etat[2 * ((y - 1) * 10 + (x - 1)) + 1] = 0;
                                    if (y != 1) {
                                        y -= 1;  /** y- 1 donc vers le Haut */
                                    }
                                    this.etat[2 * ((y - 1) * 10 + (x - 1))] = 1;
                                    this.etat[2 * ((y - 1) * 10 + (x - 1)) + 1] = team;
                                }

                                if (y != 2) {
                                    this.RegardeHaut(team, x, y);
                                }
                            }
                            if (x != 1 && x != 2)
                                this.RegardeGauche(team, x, y);
                            if (x != 10 && x != 9)
                                this.RegardeDroite(team, x, y);

                            if (y != 10 && y != 9)
                                this.RegardeBas(team, x, y);
                            this.MortGaucheDroite(team, x, y);
                        }

                        toClient.writeInt(this.etat.length);

                        for (int i = 0; i < this.etat.length; i++) {
                            toClient.writeInt(this.etat[i]);
                        }
                        break;
                    case "Bas":

                        if (this.etat[2 * ((y - 1) * 10 + (x - 1))] != 0) {
                            if (y != 10) {
                                if (this.etat[2 * ((y + 1 - 1) * 10 + (x - 1))] != 1 && this.etat[2 * ((y - 1) * 10 + (x - 1)) + 1] != 2) {
                                    this.etat[2 * ((y - 1) * 10 + (x - 1))] = 0;
                                    this.etat[2 * ((y - 1) * 10 + (x - 1)) + 1] = 0;
                                    if (y != 10) {
                                        y += 1;/** y+ 1 donc vers le Bas */
                                    }

                                    this.etat[2 * ((y - 1) * 10 + (x - 1))] = 1;
                                    this.etat[2 * ((y - 1) * 10 + (x - 1)) + 1] = team;
                                }
                                if (y != 9) {
                                    this.RegardeBas(team, x, y);
                                }
                            }
                            if (x != 1 && x != 2)
                                this.RegardeGauche(team, x, y);
                            if (x != 10 && x != 9)
                                this.RegardeDroite(team, x, y);
                            if (y != 1 && y != 2)
                                this.RegardeHaut(team, x, y);

                            this.MortGaucheDroite(team, x, y);
                        }
                        toClient.writeInt(this.etat.length);

                        for (int i = 0; i < this.etat.length; i++) {
                            toClient.writeInt(this.etat[i]);
                        }
                        break;
                    case "Stop":
                        if (this.etat[2 * ((y - 1) * 10 + (x - 1)) + 1] == 2 && this.mort == false){
                            messageMort();
                        }
                        toClient.writeInt(this.etat.length);
                        for (int i = 0; i < this.etat.length; i++) {
                            toClient.writeInt(this.etat[i]);
                        }

                        break;
                    case "Vide":
                        for (int cleanX = 1; cleanX <= 10; cleanX++) {
                            for (int cleanY = 1; cleanY <= 10; cleanY++) {
                                if (this.etat[2 * ((y - 1) * 10 + (x - 1)) + 1] == 2) {
                                    this.etat[2 * ((y - 1) * 10 + (x - 1))] = 0;
                                    this.etat[2 * ((y - 1) * 10 + (x - 1)) + 1] = 0;
                                }
                            }
                        }

                        toClient.writeInt(this.etat.length);
                        for (int i = 0; i < this.etat.length; i++) {
                            toClient.writeInt(this.etat[i]);
                        }

                        break;

                }
            }
        } catch (IOException e) {

        }
    }

    /**
     * Verification d'un sucide a gauche et a droite
     */
    public void MortGaucheDroite(int team, int x, int y) {

        if (x != 10 && x != 1)
            if (this.etat[2 * ((y - 1) * 10 + (x - 1 - 1))] == 1 && this.etat[2 * ((y - 1) * 10 + (x - 1 - 1)) + 1] != team)
                if (this.etat[2 * ((y - 1) * 10 + (x - 1 + 1))] == 1 && this.etat[2 * ((y - 1) * 10 + (x - 1 + 1)) + 1] != team)
                    this.etat[2 * ((y - 1) * 10 + (x - 1)) + 1] = 2;
    }

    /**
     * Verification d'un sucide en haut et en bas
     */
    public void MortHautBas(int team, int x, int y) {
        if (y != 10 && y != 1)
            if (this.etat[2 * ((y - 1 - 1) * 10 + (x - 1))] == 1 && this.etat[2 * ((y - 1 - 1) * 10 + (x - 1)) + 1] != team)
                if (this.etat[2 * ((y + 1 - 1) * 10 + (x - 1))] == 1 && this.etat[2 * (((y + 1) - 1) * 10 + (x - 1)) + 1] != team)
                    this.etat[2 * ((y - 1) * 10 + (x - 1)) + 1] = 2;
    }

    /**
     * Verification du gel en bas
     */
    public void RegardeBas(int team, int x, int y) {
        if (y != 10 && y != 9)
            if (this.etat[2 * ((y + 1 - 1) * 10 + (x - 1))] == 1 && this.etat[2 * (((y + 1) - 1) * 10 + (x - 1)) + 1] != team)
                if (this.etat[2 * ((y + 1 + 1 - 1) * 10 + (x - 1))] == 1 && this.etat[2 * (((y + 1 + 1) - 1) * 10 + (x - 1)) + 1] == team)
                    this.etat[2 * (((y + 1) - 1) * 10 + (x - 1)) + 1] = 2; // TUE PION
    }

    /**
     * Verification du gel en haut
     */
    public void RegardeHaut(int team, int x, int y) {
        if (y != 1 && y != 2)
            if (this.etat[2 * ((y - 1 - 1) * 10 + (x - 1))] == 1 && this.etat[2 * ((y - 1 - 1) * 10 + (x - 1)) + 1] != team)
                if (this.etat[2 * ((y - 2 - 1) * 10 + (x - 1))] == 1 && this.etat[2 * ((y - 2 - 1) * 10 + (x - 1)) + 1] == team)
                    this.etat[2 * ((y - 1 - 1) * 10 + (x - 1)) + 1] = 2; // TUE PION
    }

    /**
     * Verification du gel à gauche
     */
    public void RegardeGauche(int team, int x, int y) {
        if (x != 1 && x != 2)
            if (this.etat[2 * ((y - 1) * 10 + (x - 1 - 1))] == 1 && this.etat[2 * ((y - 1) * 10 + (x - 1 - 1)) + 1] != team)
                if (this.etat[2 * ((y - 1) * 10 + (x - 2 - 1))] == 1 && this.etat[2 * ((y - 1) * 10 + (x - 2 - 1)) + 1] == team)
                    this.etat[2 * ((y - 1) * 10 + (x - 1 - 1)) + 1] = 2; // TUE PION
    }

    /**
     * Verification du gel à droite
     */
    public void RegardeDroite(int team, int x, int y) {
        if (x != 10 && x != 9)
            if (this.etat[2 * ((y - 1) * 10 + (x - 1 + 1))] == 1 && this.etat[2 * ((y - 1) * 10 + (x - 1 + 1)) + 1] != team)
                if (this.etat[2 * ((y - 1) * 10 + (x - 1 + 2))] == 1 && this.etat[2 * ((y - 1) * 10 + (x - 1 + 2)) + 1] == team)
                    this.etat[2 * ((y - 1) * 10 + (x - 1 + 1)) + 1] = 2; // TUE PION
    }
    /**
     * affichage message de mort
     */
    public void messageMort(){
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, "Vous êtes mort");
        mort = true;
    }




}