package model.movingStrategy;

import engine.MapBuilder;
import model.PacmanCharacter;

/**
 * @author Adèle Barbier, Raphaël Kimm
 * 30/10/2020
 **/
public class DefaultMovingStrategy extends PacmanMovingStrategy {
	
	/**
	 * Constructeur de la stratégie par défaut
	 * @author Raphaël
	 * @param pc Pacman concerné par la stratégie
	 */
    public DefaultMovingStrategy(PacmanCharacter pc) {
		super(pc);
	}

	/**
	 * Déplacement du personnage par défaut vers le haut
	 * @author Raphaël
	 */
	public void mooveUp() {
		this.pacmanCharacter.setPosY(this.pacmanCharacter.getPosY()+this.pacmanCharacter.getSpeed()*this.wayY);
	}

	/**
	 * Déplacement du personange par défaut vers le bas
	 * @author Raphaël
	 */
	public void mooveDown() {
		this.pacmanCharacter.setPosY(this.pacmanCharacter.getPosY()+this.pacmanCharacter.getSpeed()*this.wayY);
	}

	/**
	 * Déplacement du personnage par défaut vers la droite
	 * @author Raphaël
	 */
	public void mooveRight() {
		this.pacmanCharacter.setPosX(this.pacmanCharacter.getPosX()+this.pacmanCharacter.getSpeed()*this.wayX);
	}

	/**
	 * Déplacement du personnage par défaut vers la gauche
	 * @author Raphaël
	 */
	public void mooveLeft() {
		this.pacmanCharacter.setPosX(this.pacmanCharacter.getPosX()+this.pacmanCharacter.getSpeed()*this.wayX);
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
