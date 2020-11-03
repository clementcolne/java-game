package model;

import java.awt.Color;

/**
 * Classe représentant un mur
 * @author Raphaël
 *
 */
public class Wall extends Ground {
	
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
		this.color = Color.DARK_GRAY;
	}
	
	/**
	 * Permet d'indiquer qu'un mur est bloquant (ne peut pas être traversé)
	 * @author Raphaël
	 * @return false
	 */
	@Override
	public boolean isAccessible() {
		return false;
	}

}