

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TPPanel extends Panel {
    TPClient main = null;
    Button bDroite, bGauche, bHaut, bBas;

    /**
     * Affichage des bouton
     */
    public TPPanel(TPClient pTPClient) {
        this.main = pTPClient;
        pTPClient.setBackground(Color.BLACK);

        // Button Gauche
        bGauche = new Button("Gauche");
        bGauche.setBackground(Color.BLACK);
        bGauche.setForeground(Color.white);
        bGauche.addActionListener(new ListenBoutonGauche());
        this.add(bGauche);
        // Button Droite
        bDroite = new Button("Droite");
        bDroite.setBackground(Color.BLACK);
        bDroite.setForeground(Color.white);

        bDroite.addActionListener(new ListenBoutonDroite());
        this.add(bDroite);
        // Button Haut
        bHaut = new Button("Haut");
        bHaut.setBackground(Color.BLACK);
        bHaut.setForeground(Color.white);
        bHaut.addActionListener(new ListenBoutonHaut());
        this.add(bHaut);
        // Button Gauche
        bBas = new Button("Bas");
        bBas.setBackground(Color.BLACK);
        bBas.setForeground(Color.white);

        bBas.addActionListener(new ListenBoutonBas());
        this.add(bBas);
    }

    /**
     * Action de chaque bouton
     */
    class ListenBoutonDroite implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            main.droite();
        }
    }

    class ListenBoutonGauche implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            main.gauche();
        }
    }

    class ListenBoutonHaut implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            main.haut();
        }
    }

    class ListenBoutonBas implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            main.bas();
        }
    }
}

