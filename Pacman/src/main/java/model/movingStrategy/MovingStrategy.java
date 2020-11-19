package model.movingStrategy;

import java.util.Iterator;

import engine.MapBuilder;
import model.Character;
import model.Ground;
import model.PacmanGame;

/**
 * @author Adèle Barbier, Raphaël Kimm
 * 30/10/2020 -> 06/11/2020
 **/
public abstract class MovingStrategy {
	
	protected MapBuilder mapBuilder;
	protected Character character;
	protected double factorX = 0, factorY = 0, wayX = 0, wayY = 0;
	
	/**
	 * Constructeur permettant d'indiquer qui est concerné par la stratégie
	 * @author Raphaël
	 * @param c Character
	 */
	public MovingStrategy(Character c) {
		this.character = c;
	}

	/**
	 * Déplacement du personnage par défaut vers le haut
	 * @author Raphaël
	 */
	public void mooveUp() {
		this.character.setPosY(this.character.getPosY()+this.character.getSpeed()*this.wayY);
	}

	/**
	 * Déplacement du personange par défaut vers le bas
	 * @author Raphaël
	 */
	public void mooveDown() {
		this.character.setPosY(this.character.getPosY()+this.character.getSpeed()*this.wayY);
	}

	/**
	 * Déplacement du personnage par défaut vers la droite
	 * @author Raphaël
	 */
	public void mooveRight() {
		this.character.setPosX(this.character.getPosX()+this.character.getSpeed()*this.wayX);
	}

	/**
	 * Déplacement du personnage par défaut vers la gauche
	 * @author Raphaël
	 */
	public void mooveLeft() {
		this.character.setPosX(this.character.getPosX()+this.character.getSpeed()*this.wayX);
	}
    
	public abstract boolean canMoove(double x, double y, MapBuilder mapBuilder);
	
    /**
     * Permet de savoir si le Pacman peut traverser ou non un mur (en fonction de s'il a l'effet Ghost ou qu'aucun mur ne se trouve devant lui)
     * @author Raphaël
     * @param x Position à incrémenter par rapport à la position actuelle en abscisse du Pacman
     * @param y Position à incrémenter par rapport à la position actuelle en ordonnée du Pacman
     * @return true s'il peut passer, false sinon
     */
    public boolean canBypassGround(double x, double y) {
    	double posX = this.character.getPosX();
    	double posY = this.character.getPosY();
    	
    	boolean insideXArea = posX + x >= 0 && ((posX + x) >= 0 ? Math.ceil(posX + x) < this.mapBuilder.getWidth() : false);
		boolean insideYArea = posY + y >= 0 && ((posY + y) >= 0 ? Math.ceil(posY + y) < this.mapBuilder.getHeight() : false);


    	if (this.character.getGhost()) {
    		if (insideXArea && insideYArea) {
    			return true;
    		}
    		else {
    			if (posX + x < 0) {
    				character.setPosX(0);
    			}
    			else if (Math.ceil(posX + x) >= mapBuilder.getWidth()) {
    				character.setPosX(mapBuilder.getWidth()-1);
    			}
    			else if (posY + y < 0) {
    				character.setPosY(0);
    			}
    			else if (Math.ceil(posY + y) >= mapBuilder.getHeight()) {
    				character.setPosY(mapBuilder.getHeight()-1);
    			}
    			return false;
    		}
    	}

    	double testX = posX;
    	boolean insideGameX = true;
    	while ((this.factorX > 0) ? testX < posX + x &&  insideGameX: testX > posX + x && insideGameX) {
    		
    		testX += this.factorX;
    		
    		insideGameX = testX >= 0 && ((testX) >= 0 ? Math.ceil(testX) < this.mapBuilder.getWidth() : false);
    		
    		if (!insideGameX) {
    			if ((int)posX != posX) {
					this.factorX /= 2;
				}
    			
				this.character.setPosX(testX-this.factorX);
    			return false;
    		};
    		
    		Iterator<Ground> collidingGrounds = PacmanGame.getCollidingGrounds(testX, posY, this.mapBuilder);
    		
    		while (collidingGrounds.hasNext()) {
    			Ground collidingGround = collidingGrounds.next();
    			if (!this.mapBuilder.get(collidingGround.getPosX(), collidingGround.getPosY()).isAccessible()) {
    				if ((int)posX != posX) {
    					this.factorX /= 2;
    				}
    				
    				this.character.setPosX(testX-this.factorX);
	    			return false;
    			}
    		}
    	}
    	
    	double testY = posY;
    	boolean insideGameY = true;
    	while ((this.factorY > 0) ? testY < posY + y && insideGameY: testY > posY + y && insideGameY) {
    		
    		testY += this.factorY;
    		

    		
    		insideGameY = testY >= 0 && ((testY) >= 0 ? Math.ceil(testY) < this.mapBuilder.getHeight() : false);

    		if (!insideGameY) {
    			if ((int)posY != posY) {
					this.factorY /= 2;
				}
				
				this.character.setPosY(testY-this.factorY);
    			return false;
    		}
    		
    		Iterator<Ground> collidingGrounds = PacmanGame.getCollidingGrounds(posX, testY, this.mapBuilder);
    		
    		while (collidingGrounds.hasNext()) {
    			Ground collidingGround = collidingGrounds.next();
    			if (!this.mapBuilder.get(collidingGround.getPosX(), collidingGround.getPosY()).isAccessible()) {
    				if ((int)posY != posY) {
    					this.factorY /= 2;
    				}
    				this.character.setPosY(testY-this.factorY);
	    			return false;
    			}
    		}
    	}
    	
    	return true;
    }
	
	/**
     * Calculer le facteur de déplacement maximal (compris entre 0 et 1) du Pacman en abscisse
     * @author Raphaël
     * @param x Position en abscisse qui doit être incrémentée sur le Pacman
     */
	public void setFactorX(double x) {
		double sign = Math.signum(x);
		double speed = character.getSpeed();
		this.factorX = sign*speed + ((Math.abs(speed) > 1) ? sign - sign*speed : 0);
		this.wayX = sign;
	}
	
    /**
     * Retourner le facteur de déplacement maximal en abscisse (compris entre 0 et 1) du Pacman
     * @author Raphaël
     * @return Facteur de déplacement en abscisse
     */
	public double getFactorX() {
		return factorX;
	}
	
	/**
     * Calculer le facteur de déplacement maximal (compris entre 0 et 1) du Pacman en ordonnée
     * @author Raphaël
     * @param y Position en ordonnée qui doit être incrémentée sur le Pacman
     */
	public void setFactorY(double y) {
		double sign = Math.signum(y);
		double speed = character.getSpeed();
		this.factorY = sign*speed + ((Math.abs(speed) > 1) ? sign - sign*speed : 0);
		this.wayY = sign;
	}
	
	/**
     * Retourner le facteur de déplacement maximal en ordonnée (compris entre 0 et 1) du Pacman
     * @author Raphaël
     * @return Facteur de déplacement en abscisse
     */
	public double getFactorY() {
		return factorY;
	}
	
	/**
	 * Permet d'indiquer dans quel sens le Pacman doit se déplacer en abscisse (normal ou inversé)
	 * @author Raphaël
	 * @param wayX Compris entre -1 (déplacement inversé) et 1 (déplacement normal)
	 */
	public void setWayX(int wayX) {
		this.wayX = wayX;
	}
	
	/**
	 * Retourner le sens de déplacement du Pacman en abscisse
	 * @author Raphaël
	 * @return -1 si inversé, 0 si aucun déplacement, 1 sinon
	 */
	public double getWayX() {
		return wayX;
	}
	
	/**
	 * Permet d'indiquer dans quel sens le Pacman doit se déplacer en ordonnée (normal ou inversé)
	 * @author Raphaël
	 * @param wayY Compris entre -1 (déplacement inversé) et 1 (déplacement normal)
	 */
	public void setWayY(double wayY) {
		this.wayY = wayY;
	}
	
	/**
	 * Retourner le sens de déplacement du Pacman en ordonnée
	 * @author Raphaël
	 * @return -1 si inversé, 0 si aucun déplacement, 1 sinon
	 */
	public double getWayY() {
		return wayY;
	}    
}
