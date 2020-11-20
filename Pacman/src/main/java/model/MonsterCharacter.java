package model;

import model.movingStrategy.DefaultMovingStrategy;

/**
 * @author Clément Colné
 */
public class MonsterCharacter extends Character {
    /**
     * Crée un personnage à la position (posX, posY)
     *
     * @param posX Position du personnage en X
     * @param posY Position du personnage en Y
     */
    public MonsterCharacter(double posX, double posY) {
        super(posX, posY);
        setMovingStrategy(new DefaultMovingStrategy(this));
    }

    @Override
    public void setPosX(double posX) {
        this.posX = posX;
    }

    @Override
    public void setPosY(double posY) {
        this.posY = posY;
    }

    /**
     * Retourne vrai pour indiquer que personnage est un monstre
     * @return true
     */
    @Override
    public boolean isMonster() {
        return true;
    }

    /**
     * Retourne la position en X et en Y du personnage
     * @author Clément
     * @return toString du personnage
     */
    @Override
    public String toString() {
        return "Position monstre : (" + posX + " ; " + posY + ")";
    }
}
