package model;

import java.awt.*;

import engine.ImageFactory;

/**
 * Classe représentant à la fois un sol concret ainsi qu'un type générique pour d'autres types de sols
 * @author Raphaël
 *
 */
public class Ground {

	protected int posX, posY;
	protected char name;
	protected Color color;
	protected Image image;
	
	/**
	 * Constructeur de Ground (sol)
	 * @author Raphaël
	 * @param x, position en abscisse de la case du sol
	 * @param y, position en ordonnée de la case du sol
	 */
	public Ground(int x, int y) {
		this.posX = x;
		this.posY = y;
		this.name = 'g';
		this.color = Color.WHITE;
		this.image = ImageFactory.getInstance().loadImage("Ground/Ground_lvl1.png");
	}

	public Image getImage() {
		return image;
	}

	/**
	 * Permet d'indiquer qu'un sol est toujours accessible par un personnage
	 * @author Raphaël
	 * @return true sauf si la case est un mur
	 */
	public boolean isAccessible() {
		return true;
	}

	/**
	 * Permet d'indiquer que la case est ou non un passage
	 * @return toujours false saux si la case est un passage
	 */
	protected boolean isPassage() {
		return false;
	}

	protected boolean isTreasure() { return false; }
	
	/**
	 * Permet d'indiquer que le case est ou non un effet
	 * @author Raphaël
	 * @return false sauf si la case est un effet
	 */
	protected boolean isEffect() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Permet de retourner la position en abscisse du sol
	 * @author Raphaël
	 * @return Position en abscisse du sol
	 */
	public int getPosX() {
		return this.posX;
	}
	
	/**
	 * Permet de retourner la position en ordonnée du sol
	 * @author Raphaël
	 * @return Position en ordonnée du sol
	 */
	public int getPosY() {
		return this.posY;
	}

	/**
	 * Retourne l'initiale qui est identifiant du type de sol
	 * @author Raphaël
	 * @return Type du sol
	 */
	public char getName() {
		return this.name;
	}

	/**
	 * Retourne la couleur du type de sol
	 * @author Raphaël
	 * @return Couleur du sol
	 */
	public Color getColor() {
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
	
	/**
	 * Permet de déléguer la gestion des comportements des cases aux classes filles (qui sont des sols de différents types)
	 * @author Raphaël
	 * @param pacmanCharacter Pacman auquel doit être appliqué l'effet
	 */
	public void doEffect(PacmanCharacter pacmanCharacter) {}
	
	/**
	 * Permet d'indiquer si le sol n'effectue aucune action ayant une répercussion sur d'autres objets
	 * @author Raphaël
	 * @return true pour un sol par défaut
	 */
	public boolean hasEmptyBehavior() {
		return true;
	}

}
