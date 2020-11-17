package model;

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
    }

    @Override
    public void setPosX(double posX) {
        this.posX = posX;
    }

    @Override
    public void setPosY(double posY) {
        this.posY = posY;
    }
}
