package model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import engine.CustomIterator;
import engine.ImageFactory;
import model.movingStrategy.DefaultMovingStrategy;
import model.movingStrategy.MovingStrategy;

/**
 * Cette classe décrit le comportement d'un personnage
 * @author Clément Colné
 */
public class PacmanCharacter extends Character{

	private List<int[]> visitedCoordinates;

    /**
     * Constructeur du personnage pacman
     * @author Clément
     * @param posX Position du personnage en X
     * @param posY Position du personnage en Y
     */
    public PacmanCharacter(double posX, double posY) {
        super(posX, posY);
        animation = ImageFactory.getInstance().loadAnimation("Character/wraith.gif", 60);
        movingStrategy = new DefaultMovingStrategy(this);
        visitedCoordinates = new LinkedList<>(Arrays.asList(new int[] {(int) this.posX, (int) this.posY}));
    }


	/**
	 * Permet de modifier la portée d'attaque du Pacman (en cases)
	 * @author Raphaël
	 * @param r Portée des attaques
	 */
	public void setRange(int r) {
		if (this.range >= 1)
			this.range = r;
		else {
			this.range = 1;
		}
	}

	/**
	 * Retourner la portée des attaques du Pacman
	 * @author Raphaël
	 * @return Portée des attaques du Pacman
	 */
	public int getRange() {
		return this.range;
	}


	/**
	 * Modifier la position en abscisse du Pacman
	 * @author Adèle
	 * @param posX Position en abscisse
	 */
    public void setPosX(double posX) {
        super.setPosX(posX);
        this.visitedCoordinates.add(new int[] {(int) this.posX, (int) this.posY});
    }

    /**
     * Modifier la position en ordonnée du Pacman
     * @author Adèle
     * @param posY Position en ordonnée
     */
    public void setPosY(double posY) {
        super.setPosY(posY);
        this.visitedCoordinates.add(new int[] {(int) this.posX, (int) this.posY});
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
     * Modifie la stratégie de déplacement en vigueur
     * @param movingStrategy nouvelle stratégie de déplacement à appliquer
     * @author Adèle
     */
    public void setMovingStrategy(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public MovingStrategy getMovingStrategy() {
    	return this.movingStrategy;
    }

    public String getMovingStrategyType(){
        return this.movingStrategy.getType();
    }
    
    /**
     * Retourner un itérateur sur la liste des coordonnées parcourues par le Pacman
     * @author Raphaël
     * @return Itérateur sur la liste des coordonées parcourues par le Pacman
     */
    public Iterator<int[]> getVisitedCoordinates() {
    	return new CustomIterator<int[]>(this.visitedCoordinates);
    }

    /**
     * Retourne la position en X et en Y du personnage
     * @author Clément
     * @return toString du personnage
     */
    @Override
    public String toString() {
        return "Position pacman : (" + posX + " ; " + posY + ")";
    }
}
