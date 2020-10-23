package model;

import java.awt.Color;

/**
 * Classe représentant à la fois un sol concret ainsi qu'un type générique pour d'autres types de sols
 * @author Raphaël
 *
 */
public class Ground {

	protected final int posX, posY;
	protected final char name = 'g';
	protected final Color color = Color.WHITE;
	
	/**
	 * Constructeur de Ground (sol)
	 * @author Raphaël
	 * @param x, position en abscisse de la case du sol
	 * @param y, position en ordonnée de la case du sol
	 */
	protected Ground(int x, int y) {
		this.posX = x;
		this.posY = y;
	}
	
	/**
	 * Permet d'indiquer qu'un sol est toujours accessible par un personnage
	 * @author Raphaël
	 * @return true
	 */
	protected boolean isAccessible() {
		return true;
	}
	
	/**
	 * Permet de retourner la position en abscisse du sol
	 * @author Raphaël
	 * @return Position en abscisse du sol
	 */
	protected int getPosX() {
		return this.posX;
	}
	
	/**
	 * Permet de retourner la position en ordonnée du sol
	 * @author Raphaël
	 * @return Position en ordonnée du sol
	 */
	protected int getPosY() {
		return this.posY;
	}

	/**
	 * Retourne l'initiale qui est identifiant du type de sol
	 * @author Raphaël
	 * @return Type du sol
	 */
	protected char getName() {
		return this.name;
	}

	/**
	 * Retourne la couleur du type de sol
	 * @author Raphaël
	 * @return Couleur du sol
	 */
	protected Color getColor() {
		return this.color;
	}
}
