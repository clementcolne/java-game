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
}
