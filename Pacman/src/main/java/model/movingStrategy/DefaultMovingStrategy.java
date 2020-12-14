package model.movingStrategy;

import engine.MapBuilder;
import model.Character;

/**
 * @author Adèle Barbier, Raphaël Kimm
 * 30/10/2020
 **/
public class DefaultMovingStrategy extends MovingStrategy {
	
	/**
	 * Constructeur de la stratégie par défaut
	 * @author Raphaël
	 * @param c Character concerné par la stratégie
	 */
    public DefaultMovingStrategy(Character c) {
		super(c);
	}

	/**
     * Vérifie si le joueur peut se déplacer dans la direction par défaut
     * @author Raphaël
     * @param x Position en x à ajouter au Pacman
     * @param y Position en y à ajouter au Pacman
     */
    @Override
    public boolean canMoove(double x, double y, MapBuilder mapBuilder) {
    	if (x != 0 && y != 0) {
    		return false;
    	}
    	
    	this.setFactorX(x);
    	this.setFactorY(y);
    	this.mapBuilder = mapBuilder;

    	return this.canBypassGround(x, y);
    }
}
