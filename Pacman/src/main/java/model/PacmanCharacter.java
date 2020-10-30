package model;

import model.movingStrategy.DefaultMovingStrategy;
import model.movingStrategy.MovingStrategy;

/**
 * Cette classe décrit le comportement d'un personnage
 * @author Clément Colné
 */
public class PacmanCharacter {

    private int posX;
    private int posY;
    private int life = 10;
    private int speed = 2;
    private MovingStrategy movingStrategy;

    /**
     * Constructeur du personnage pacman
     * @author Clément
     * @param posX Position du personnage en X
     * @param posY Position du personnage en Y
     */
    public PacmanCharacter(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        movingStrategy = new DefaultMovingStrategy();
    }

    /**
     *
     * @author Adham
     * Déplace la position du personnage d'une case vers la droite
     * @author Adham
     */
    public void mooveRight() {
        movingStrategy.mooveRight(this);
    }

    /**
     * Déplace la position du personnage d'une case vers la gauche
     * @author Adèle
     */
    public void mooveLeft() {
        movingStrategy.mooveLeft(this);
    }

    /**
     * Déplace la position du personnage d'une case vers le haut
     * @author Raphael
     */
    public void mooveUp() {
    	movingStrategy.mooveUp(this);
    }

    /**
     * Déplace la position du personnage d'une case vers le bas
     * @author Clément
     */
    public void mooveDown() {
        movingStrategy.mooveDown(this);
    }

    /**
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
     * Définit à quelle vitesse avance le personnage
     * @param speed
     */
    public void setSpeed(int speed){
        this.speed = speed;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Modifie la stratégie de déplacement en vigueur
     * @param movingStrategy nouvelle stratégie de déplacement à appliquer
     * @author Adèle
     */
    public void setMovingStrategy(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
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
     * @author Adèle
     * @return le nombre de point de vie du personnage
     */
    public int getLife() {
        return life;
    }

    /**
     * Retourne la position en X et en Y du personnage
     * @return toString du personnage
     */
    @Override
    public String toString() {
        return "Position pacman : (" + posX + " ; " + posY + ")";
    }

    public int getSpeed() {
        return speed;
    }
}
