package model.movingStrategy;

import engine.MapBuilder;
import model.PacmanCharacter;

/**
 * @author Adèle Barbier
 * 30/10/2020
 **/
public class RandomMovingStrategy extends PacmanMovingStrategy {

	/**
	 * Constructeur de la stratégie Random (déplacement étourdi)
	 * @author Raphaël
	 * @param pc Pacman concerné par la stratégie
	 */
    public RandomMovingStrategy(PacmanCharacter pc) {
		super(pc);
	}

	/**
	 * Ici, bouger vers le haut fait bouger dans une autre direction indiquée dans canMoove
	 */
    public void mooveUp() {
        this.pacmanCharacter.setPosX(this.pacmanCharacter.getPosX()+this.pacmanCharacter.getSpeed()*this.wayX);
    }

	/**
	 * Ici, bouger vers le bas fait bouger dans une autre direction indiquée dans canMoove
	 */
    public void mooveDown() {
    	this.pacmanCharacter.setPosX(this.pacmanCharacter.getPosX()+this.pacmanCharacter.getSpeed()*this.wayX);
    }

    /**
	 * Ici, bouger vers la droite fait bouger dans une autre direction indiquée dans canMoove
	 */
    public void mooveRight() {
    	this.pacmanCharacter.setPosY(this.pacmanCharacter.getPosY()+this.pacmanCharacter.getSpeed()*this.wayY);
    }

    /**
	 * Ici, bouger vers la gauche fait bouger dans une autre direction indiquée dans canMoove
	 */
    public void mooveLeft() {
    	this.pacmanCharacter.setPosY(this.pacmanCharacter.getPosY()+this.pacmanCharacter.getSpeed()*this.wayY);
    }

    /**
     * Vérifie si le joueur peut se déplacer dans la direction Random.
     * Etant donné que l'appel à canMoove est générique, les paramètres x et y sont inversés selon la direction choisie dans les méthodes moove.
     * @author Raphaël
     * @param x Position en x à ajouter au Pacman
     * @param y Position en y à ajouter au Pacman
     * @param mapBuilder Générateur de la carte
     * @param pacmanCharacter Pacman sur lequel la vérification de déplacement doit être effectuée
     */
    public boolean canMoove(double x, double y, MapBuilder mapBuilder) {
    	if (x != 0 && y != 0) {
    		return false;
    	}
    	
    	// Inversement des axes
    	if (x > 0 || x < 0) {
    		y = x;
    		x = 0;
    	}
    	else if (y > 0 || y < 0) {
    		x = y;
    		y = 0;
    	}

    	// On valide l'inversement des axes pour les méthodes de déplacement
		this.setFactorY(y);
		this.setFactorX(x);
    	this.mapBuilder = mapBuilder;

    	return this.canBypassGround(x, y);
    }
}
