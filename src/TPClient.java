
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter; // Window Event
import java.awt.event.WindowEvent; // Window Event
import java.awt.event.*;
import java.awt.event.WindowListener;
import java.net.Socket;
import java.util.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.BufferedReader;

import java.io.*;
import java.util.List;
import java.util.Timer;
import java.awt.desktop.SystemEventListener;
import java.awt.event.*;
/**
 * Class
 */

public class TPClient extends Frame {

    int[] etat = new int[2 * 10 * 10];
    /**
     * Changer un tableau de byte en un tableau de int
     */
    int team;
    /**
     * Changer en int
     */
    int x;
    int y;
    int port = 7000;
    TPPanel tpPanel;
    TPCanvas tpCanvas;

    DataInputStream fromServer;
    /**
     * Pour connexion au server
     */
    DataOutputStream toServer; /**Pour connexion au server*/

    /**
     * Constructeur
     */
    public TPClient(int team, int x, int y) throws Exception {
        Socket socket = new Socket("127.0.0.1", port);

        toServer = new DataOutputStream(socket.getOutputStream());  /**Pour la relation au server*/
        fromServer = new DataInputStream(socket.getInputStream());  /**Pour la relation au server*/

        System.out.println(fromServer.readUTF()); /**Pour la relation au server*/

        toServer.write(x); /**Pour la relation au server (écrire x) */
        toServer.write(y); /**Pour la relation au server (écrire y)*/
        toServer.write(team);  /**Pour la relation au server (écrire team)*/

        int length = fromServer.readInt();
        for (int i = 0; i < length; i++) {
            this.etat[i] = fromServer.readInt();
        }

        this.x = x;
        this.y = y;
        this.team = team;

        setLayout(new BorderLayout());
        tpPanel = new TPPanel(this);
        add("South", tpPanel);
        tpCanvas = new TPCanvas(this.etat);
        add("Center", tpCanvas);

        new Timer().schedule(new TimerRefresh(), 0, 500);
        new Timer().schedule(new TimerRedevenirVide(), 0, 10000);

        tpCanvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_UP) {
                    haut();
                }
                else if (keyCode == KeyEvent.VK_DOWN) {
                    bas();
                }
                else if (keyCode == KeyEvent.VK_LEFT) {
                    gauche();
                }
                else if (keyCode == KeyEvent.VK_RIGHT) {
                    droite();
                }
            }
        });

    }



    /**
     * Action vers droite
     */
    public synchronized void droite() {
        try {
            toServer.writeUTF("Droite");
            int length = fromServer.readInt();
            for (int i = 0; i < length; i++) {
                this.etat[i] = fromServer.readInt();
            }
            tpCanvas.setEtat(this.etat);
            tpCanvas.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    /**
     * Action vers gauche
     */
    public synchronized void gauche() {
        try {
            toServer.writeUTF("Gauche");
            int length = fromServer.readInt();
            for (int i = 0; i < length; i++) {
                this.etat[i] = fromServer.readInt();
            }
            tpCanvas.setEtat(this.etat);
            tpCanvas.repaint();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Action vers haut
     */
    public synchronized void haut() {
        try {

            toServer.writeUTF("Haut");
            int length = fromServer.readInt();
            for (int i = 0; i < length; i++) {
                this.etat[i] = fromServer.readInt();
            }
            tpCanvas.setEtat(this.etat);
            tpCanvas.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Action vers bas
     */
    public synchronized void bas() {
        try {

            toServer.writeUTF("Bas");
            int length = fromServer.readInt();
            for (int i = 0; i < length; i++) {
                this.etat[i] = fromServer.readInt();
            }
            tpCanvas.setEtat(this.etat);
            tpCanvas.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Pour rafraichir la situation
     */
    public synchronized void refresh() {
        try {
            toServer.writeUTF("Stop");
            int length = fromServer.readInt();
            for (int i = 0; i < length; i++) {
                this.etat[i] = fromServer.readInt();
            }
            tpCanvas.setEtat(this.etat);
            tpCanvas.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tpCanvas.repaint();
    }

    /**
     * Pour recevoir l'Etat
     */
    public synchronized void RedevenirVide() {
        try {
            toServer.writeUTF("Vide");
            int length = fromServer.readInt();
            for (int i = 0; i < length; i++) {
                this.etat[i] = fromServer.readInt();
            }
            tpCanvas.setEtat(this.etat);
            tpCanvas.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Choisir une team en 'Cheval' ou 'Chateau' : ");
        String Team = myObj.nextLine();

        int choixTeam = 3;
        if (Team.equals("Cheval")) {
            choixTeam = 0;
        } else if (Team.equals("Chateau")) {
            choixTeam = 1;
        }

        while (choixTeam != 1 && choixTeam != 0) {
            System.out.println("Une erreur est survenue !");
            System.out.println("Choisir une team en 'Cheval' ou 'Chateau' : ");
            Team = myObj.nextLine();
            if (Team.equals("Cheval")) {
                choixTeam = 0;
            } else if (Team.equals("Chateau")) {
                choixTeam = 1;
            }
        }
        System.out.println("Choisir votre position en x : ");
        int choixX = Integer.parseInt(myObj.nextLine());
        while (choixX < 1 || choixX > 10) {
            System.out.println("Une erreur est survenue !");
            System.out.println("Choisir votre position en x : ");
            choixX = Integer.parseInt(myObj.nextLine());
        }

        System.out.println("Choisir votre position en y : ");
        int choixY = Integer.parseInt(myObj.nextLine());
        while (choixY < 1 || choixY > 10) {
            System.out.println("Une erreur est survenue !");
            System.out.println("Choisir votre position en y : ");
            choixY = Integer.parseInt(myObj.nextLine());
        }

        try {
            /** Convertir en int[] */
            TPClient tPClient = new TPClient(choixTeam, choixX, choixY);

            tPClient.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });


            tPClient.pack();
            tPClient.setBackground(Color.BLACK);
            tPClient.setSize(500, 500 + 100);
            tPClient.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Pour rafraichir
     */
    class TimerRefresh extends TimerTask {

        public void run() {
            refresh();
        }
    }

    class TimerRedevenirVide extends TimerTask {
        public void run() {
            RedevenirVide();
        }
    }






}