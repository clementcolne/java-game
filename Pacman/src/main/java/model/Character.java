package model;

import model.movingStrategy.MovingStrategy;

/**
 * @author Adèle Barbier
 * 17/11/2020
 **/
public abstract class Character {
    protected double posX;
    protected double posY;
    protected double speed;
    protected String path;
    protected MovingStrategy movingStrategy;

    /**
     * Crée un personnage à la position (posX, posY)
     * @param posX Position du personnage en X
     * @param posY Position du personnage en Y
     */
    public Character(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
        this.speed = 1;
    }

    /**
     * Met en place une nouvelle stratégie de déplacment pour le personnage
     * @param strategy nouvelle stratégie à utiliser
     */
    public void setMovingStrategy(MovingStrategy strategy){
        movingStrategy = strategy;
    }

    public MovingStrategy getMovingStrategy() {
        return movingStrategy;
    }

    /**
     * Retourne la position en X du personnage
     * @author Clément
     * @return position en X du personnage
     */
    public double getPosX() {
        return posX;
    }

    /**
     * Modifier la position en abscisse du Pacman
     * @author Adèle
     * @param posX Position en abscisse
     */
    public void setPosX(double posX) {
        this.posX = posX;
    }

    /**
     * Retourne la position en Y du personnage
     * @author Clément
     * @return position en Y du personnage
     */
    public double getPosY() {
        return posY;
    }

    /**
     * Modifier la position en ordonnée du Pacman
     * @author Adèle
     * @param posY Position en ordonnée
     */
    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
