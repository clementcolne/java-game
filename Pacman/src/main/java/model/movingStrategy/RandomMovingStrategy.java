package model.movingStrategy;

import engine.MapBuilder;
import model.Character;
import model.PacmanCharacter;

/**
 * @author Adèle Barbier
 * 30/10/2020
 **/
public class RandomMovingStrategy extends MovingStrategy {

	/**
	 * Constructeur de la stratégie Random (déplacement étourdi)
	 * @author Raphaël
	 * @param pc Pacman concerné par la stratégie
	 */
    public RandomMovingStrategy(PacmanCharacter pc) {
		super(pc);
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
