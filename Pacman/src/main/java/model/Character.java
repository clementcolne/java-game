package model;

import engine.MapBuilder;
import model.movingStrategy.MovingStrategy;

/**
 * @author Adèle Barbier
 * 17/11/2020
 **/
public abstract class Character {

    protected double posX;
    protected double posY;
    protected double speed;
    protected int life = 10;
    protected int range = 1;
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
     *
     * Déplace la position du personnage d'une case vers la droite
     * @author Adham
     */
    public void mooveRight() {
        movingStrategy.mooveRight();
    }

    /**
     * Déplace la position du personnage d'une case vers la gauche
     * @author Adèle
     */
    public void mooveLeft() {
        movingStrategy.mooveLeft();
    }

    /**
     * Déplace la position du personnage d'une case vers le haut
     * @author Raphael
     */
    public void mooveUp() {
        movingStrategy.mooveUp();
    }

    /**
     * Modifier la vitesse du Pacman
     * @author Raphaël
     * @param s
     */
    public void setSpeed(double s) {
        this.speed = s;
    }

    /**
     * Retourner la vitesse du Pacman
     * @author Raphaël
     * @return Vitesse du pacman
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Déplace la position du personnage d'une case vers le bas
     * @author Clément
     */
    public void mooveDown() {
        movingStrategy.mooveDown();
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    /**
     * Modifier la position en ordonnée du Pacman
     * @author Adèle
     * @param posY Position en ordonnée
     */
    public void setPosY(double posY) {
        this.posY = posY;
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
     * Retourne la position en Y du personnage
     * @author Clément
     * @return position en Y du personnage
     */
    public double getPosY() {
        return posY;
    }

   /**
     * Détermine si le personnage peut aller dans la direction désirée, en fonction de sa stratégie de déplacement
     * @param x coordonnée horizontale de la destination
     * @param y coordonnée horizontale de la destination
     * @param mapBuilder carte des cases du jeu
     * @return true si le personnage peut se déplacer vers la case souhaitée
     */
    public boolean canMoove(double x, double y, MapBuilder mapBuilder){
        return movingStrategy.canMoove(x, y, mapBuilder);
    }

    /**
     * Retourne toujours faux
     * @return false
     */
    public boolean isMonster() {
        return false;
    }
}
