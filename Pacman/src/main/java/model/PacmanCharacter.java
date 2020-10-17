package model;

/**
 * Cette classe décrit le comportement d'un personnage
 * @author Clément Colné
 */
public class PacmanCharacter {

    private int posX;
    private int posY;

    /**
     * Constructeur du personnage pacman
     * @author Clément
     * @param posX Position du personnage en X
     * @param posY Position du personnage en Y
     */
    public PacmanCharacter(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     *
     * @author Adham
     */
    public void mooveRight() {

    }

    /**
     *
     * @author Adèle
     */
    public void mooveLeft() {

    }

    /**
     *
     * @author Raphael
     */
    public void mooveUp() {

    }

    /**
     *
     * @author Clément
     */
    public void mooveDown() {
        posY += 1;
    }

    /**
     * Retourne la position en X du personnage
     * @author Clément
     * @return position en X du personnage
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Retourne la position en Y du personnage
     * @return position en Y du personnage
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Retourne la position en X et en Y du personnage
     * @return toString du personnage
     */
    @Override
    public String toString() {
        return "Position pacman : (" + posX + " ; " + posY + ")";
    }

}
