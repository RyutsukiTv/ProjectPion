

import java.awt.*;

/**
 * Creation de la classe Canvas
 */

public class TPCanvas extends Canvas {

    int size = 450;
    int nbPosition = 10;
    int[] etat;

    Color[] color = {Color.red, Color.blue, Color.gray};
    // 0 red 1 blue 2 etat de mort


    public TPCanvas(int[] pEtat) {
        this.etat = pEtat;
    }

    /**
     * Actionner l'affichage du plateau de jeu
     */
    public void paint(Graphics win) {
        paintCarte(win);
        drawEtat(win);
    }

    /**
     * Définition des dimention de la grille de jeu
     */
    public Dimension getMinimumSize() {
        return new Dimension(size, size);

    }

    /**
     * Affichae de la grille de jeu
     */
    public void paintCarte(Graphics win) {
        win.drawRect(0, 0, size - 1, size - 1);    // Draw border
        for (int i = 1; i < 10; i++) {
            // Dessin
            //Couleur des lignes
            win.setColor(Color.white);
            win.drawLine(i * size / nbPosition, 0, i * size / nbPosition, size);
            win.drawLine(0, i * size / nbPosition, size, i * size / nbPosition);
        }

    }

    /**
     * Affichage de l'état de la position du joueur
     */
    public void drawEtat(Graphics win) {
        for (int i = 0; i < 10 * 10; i++) {
            if (etat[2 * i] != 0) {
                drawPlayer(win, i % 10, i / 10, etat[2 * i + 1]);
            }
        }
    }

    /**
     * fonction de dessin du contenue de la case
     */
    public void drawPlayer(Graphics win, int x, int y, int type) {
        int casesize = (size / nbPosition);
        int positionX = (x * (size / nbPosition));
        int positionY = (y * (size / nbPosition));

        int Towerx[] = {
                casesize / 8 * 1 + positionX,
                casesize / 8 * 2 + positionX,
                casesize / 8 * 2 + positionX,
                casesize / 8 * 3 + positionX,
                casesize / 8 * 3 + positionX,
                casesize / 8 * 5 + positionX,
                casesize / 8 * 5 + positionX,
                casesize / 8 * 6 + positionX,
                casesize / 8 * 6 + positionX,
                casesize / 8 * 7 + positionX,
                casesize / 8 * 7 + positionX,
                casesize / 8 * 1 + positionX,};

        int Towery[] = {
                casesize / 8 * 1 + positionY,
                casesize / 8 * 1 + positionY,
                casesize / 8 * 2 + positionY,
                casesize / 8 * 2 + positionY,
                casesize / 8 * 1 + positionY,
                casesize / 8 * 1 + positionY,
                casesize / 8 * 2 + positionY,
                casesize / 8 * 2 + positionY,
                casesize / 8 * 1 + positionY,
                casesize / 8 * 1 + positionY,
                casesize / 8 * 7 + positionY,
                casesize / 8 * 7 + positionY,};

        int towerpts = 12;

        Polygon tower = new Polygon(Towerx, Towery, towerpts);

        int Horsex[] = {
                casesize / 8 * 1 + positionX,
                casesize / 8 * 2 + positionX,
                casesize / 8 * 3 + positionX,
                casesize / 8 * 4 + positionX,
                casesize / 8 * 5 + positionX,
                casesize / 8 * 6 + positionX,
                casesize / 8 * 7 + positionX,
                casesize / 8 * 6 + positionX,
                casesize / 8 * 5 + positionX,
                casesize / 8 * 4 + positionX,
                casesize / 8 * 6 + positionX,
                casesize / 8 * 6 + positionX,
                casesize / 8 * 1 + positionX,
        };

        int Horsey[] = {
                casesize / 8 * 6 + positionY,
                casesize / 8 * 3 + positionY,
                casesize / 8 * 2 + positionY,
                casesize / 8 * 2 + positionY,
                casesize / 8 * 1 + positionY,
                casesize / 8 * 2 + positionY,
                casesize / 8 * 4 + positionY,
                casesize / 8 * 5 + positionY,
                casesize / 8 * 4 + positionY,
                casesize / 8 * 4 + positionY,
                casesize / 8 * 6 + positionY,
                casesize / 8 * 7 + positionY,
                casesize / 8 * 7 + positionY,
        };

        int horsepts = 13;

        Polygon horse = new Polygon(Horsex, Horsey, horsepts);

        int Skullx[] = {
                casesize / 8 * 3 + positionX,
                casesize / 8 * 5 + positionX,
                casesize / 8 * 5 + positionX,
                casesize / 8 * 6 + positionX,
                casesize / 8 * 7 + positionX,
                casesize / 8 * 7 + positionX,
                casesize / 8 * 6 + positionX,
                casesize / 8 * 6 + positionX,
                casesize / 8 * 6 + positionX,
                casesize / 8 * 5 + positionX,
                casesize / 8 * 5 + positionX,
                casesize / 8 * 6 + positionX,
                casesize / 8 * 6 + positionX,
                casesize / 8 * 2 + positionX,
                casesize / 8 * 2 + positionX,
                casesize / 8 * 3 + positionX,
                casesize / 8 * 3 + positionX,
                casesize / 8 * 2 + positionX,
                casesize / 8 * 2 + positionX,
                casesize / 8 * 2 + positionX,
                casesize / 8 * 1 + positionX,
                casesize / 8 * 1 + positionX,
                casesize / 8 * 2 + positionX,
                casesize / 8 * 3 + positionX,
        };

        int Skully[] = {
                casesize / 8 * 7 + positionY,
                casesize / 8 * 7 + positionY,
                casesize / 8 * 6 + positionY,
                casesize / 8 * 6 + positionY,
                casesize / 8 * 5 + positionY,
                casesize / 8 * 3 + positionY,
                casesize / 8 * 1 + positionY,
                casesize / 8 * 3 + positionY,
                casesize / 8 * 4 + positionY,
                casesize / 8 * 4 + positionY,
                casesize / 8 * 3 + positionY,
                casesize / 8 * 3 + positionY,
                casesize / 8 * 1 + positionY,
                casesize / 8 * 1 + positionY,
                casesize / 8 * 3 + positionY,
                casesize / 8 * 3 + positionY,
                casesize / 8 * 4 + positionY,
                casesize / 8 * 4 + positionY,
                casesize / 8 * 3 + positionY,
                casesize / 8 * 1 + positionY,
                casesize / 8 * 3 + positionY,
                casesize / 8 * 5 + positionY,
                casesize / 8 * 6 + positionY,
                casesize / 8 * 6 + positionY,
        };

        int skullpts = 24;

        Polygon skull = new Polygon(Skullx, Skully, skullpts);

        /**Création de la forme du joueur */
        if (color[type] == Color.blue) {
            win.setColor(color[type]);
            win.fillPolygon(tower);
            win.drawPolygon(tower);
        } else if (color[type] == Color.red) {
            win.setColor(color[type]);
            win.fillPolygon(horse);
            win.drawPolygon(horse);
        }
        /** Création de l'affichage de l'état de mort*/
        else if (color[type] == Color.gray) {
            win.setColor(color[type]);
            win.fillPolygon(skull);
            win.drawPolygon(skull);
        }

    }

    public void setEtat(int[] pEtat) {
        this.etat = pEtat;
    }
}
