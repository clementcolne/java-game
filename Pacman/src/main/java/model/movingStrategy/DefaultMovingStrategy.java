package model.movingStrategy;

import engine.MapBuilder;
import model.PacmanCharacter;

/**
 * @author Adèle Barbier, Raphaël Kimm
 * 30/10/2020
 **/
public class DefaultMovingStrategy extends MovingStrategy {
	
	/**
	 * Constructeur de la stratégie par défaut
	 * @author Raphaël
	 * @param pc Pacman concerné par la stratégie
	 */
    public DefaultMovingStrategy(PacmanCharacter pc) {
		super(pc);
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
    	
    	double posX = this.pacmanCharacter.getPosX();
    	double posY = this.pacmanCharacter.getPosY();
    	boolean canBypass = true;
    	
    	if (!pacmanCharacter.getGhost()) {
	    	canBypass = this.canBypassGround(x, y);   	
    	}
    	
    	return canBypass && posX + x < mapBuilder.getWidth() && posY + y < mapBuilder.getHeight() && posX + x >= 0 && posY + y >= 0;
    }
}
