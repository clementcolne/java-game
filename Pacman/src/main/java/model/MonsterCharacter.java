package model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import engine.ImageFactory;
import model.movingStrategy.DefaultMovingStrategy;
import model.movingStrategy.GhostMovingStrategy;
import model.movingStrategy.MovingStrategy;

/**
 * @author Clément Colné
 */
public class MonsterCharacter extends Character {
	
	private List<MovingStrategy> strategies = new LinkedList<MovingStrategy>(Arrays.asList(new MovingStrategy[] {
			new DefaultMovingStrategy(this),
			new GhostMovingStrategy(this)
	}));
	
    /**
     * Crée un personnage à la position (posX, posY)
     *
     * @param posX Position du personnage en X
     * @param posY Position du personnage en Y
     */
    public MonsterCharacter(double posX, double posY) {
        super(posX, posY);
        life = 3;
        this.setMovingStrategy(this.strategies.get((int) (Math.random()*this.strategies.size())));
        if(this.getMovingStrategyType().equals("ghost")) {
            animation = ImageFactory.getInstance().loadAnimation("Character/Personnage1.gif", 60);
        }else {
            animation = ImageFactory.getInstance().loadAnimation("Character/Personnage2.gif", 60);
        }
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
     * @author Adèle
     * @return retourne la force du personnage
     */
    @Override
    public int getStrength() {
        return 1;
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
