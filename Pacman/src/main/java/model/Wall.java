package model;

import java.awt.Color;

/**
 * Classe représentant un mur
 * @author Raphaël
 *
 */
public class Wall extends Ground {

	private char name;
	private Color color;
	private int posX, posY;
	
	/**
	 * Constructeur de Wall (mur)
	 * @author Raphaël
	 * @param x, position en abscisse du mur
	 * @param y, position en ordonnée du mur
	 */
	public Wall(int x, int y) {
		super(x, y);
		this.posX = x;
		this.posY = y;
		this.name = 'w';
		this.color = Color.GRAY;
	}
	
	/**
	 * Permet d'indiquer qu'un mur est bloquant (ne peut pas être traversé)
	 * @author Raphaël
	 * @return false
	 */
	@Override
	protected boolean isAccessible() {
		return false;
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

	/**
	 * Retourne le nom, la position en X et en Y du sol
	 * @author Clément
	 * @return toString du sol
	 */
	@Override
	public String toString() {
		return "(" + name + posX + ";" + posY + ")";
	}

}
