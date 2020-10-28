package model;

/**
 * Cette classe décrit le comportement d'un personnage
 * @author Clément Colné
 */
public class PacmanCharacter {

    private int posX;
    private int posY;
    private int life = 10;

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
     * Déplace la position du personnage d'une case vers la droite
     * @author Adham
     */
    public void mooveRight() {
        posX += 1;
    }

    /**
     * Déplace la position du personnage d'une case vers la gauche
     * @author Adèle
     */
    public void mooveLeft() {
        posX -= 1;
    }

    /**
     * Déplace la position du personnage d'une case vers le haut
     * @author Raphael
     */
    public void mooveUp() {
    	posY -= 1;
    }

    /**
     * Déplace la position du personnage d'une case vers le bas
     * @author Clément
     */
    public void mooveDown() {
        posY += 1;
    }

    /**
     * @author Adèle
     * Inflige des dégats au personnage, ce qui lui fait perdre un/des points de vie
     * @param damage ampleur des dégats infligés, nombre de points de vie perdus par le personnage
     */
    public void setDamage(int damage){
        if(life-damage>=0)
            life -= damage;
        else
            life = 0;
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
     * @author Clément
     * Retourne la position en Y du personnage
     * @return position en Y du personnage
     */
    public int getPosY() {
        return posY;
    }

    /**
     * @author Adèle
     * @return le nombre de point de vie du personnage
     */
    public int getLife() {
        return life;
    }

    /**
     * @author Clément
     * Retourne la position en X et en Y du personnage
     * @return toString du personnage
     */
    @Override
    public String toString() {
        return "Position pacman : (" + posX + " ; " + posY + ")";
    }
}
