package model;

import java.awt.Color;

/**
 * Classe représentant un mur
 * @author Raphaël
 *
 */
public class Wall extends Ground {

	protected final char name = 'w';
	protected final Color color = Color.GRAY;
	
	/**
	 * Constructeur de Wall (mur)
	 * @author Raphaël
	 * @param x, position en abscisse du mur
	 * @param y, position en ordonnée du mur
	 */
	protected Wall(int x, int y) {
		super(x, y);
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
}
