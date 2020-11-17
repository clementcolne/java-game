package model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import engine.CustomIterator;
import engine.MapBuilder;
import model.movingStrategy.DefaultMovingStrategy;
import model.movingStrategy.MovingStrategy;

/**
 * Cette classe décrit le comportement d'un personnage
 * @author Clément Colné
 */
public class PacmanCharacter extends Character{

    private int life = 10;
	private int range = 1;
	private boolean ghost = false;
	private List<int[]> visitedCoordinates;

    /**
     * Constructeur du personnage pacman
     * @author Clément
     * @param posX Position du personnage en X
     * @param posY Position du personnage en Y
     */
    public PacmanCharacter(double posX, double posY) {
        super(posX, posY);
        
        movingStrategy = new DefaultMovingStrategy(this);
        visitedCoordinates = new LinkedList<>(Arrays.asList(new int[] {(int) this.posX, (int) this.posY}));
    }

    /**
     *
     * Déplace la position du personnage d'une case vers la droite
     * @author Adham
     */
    public void mooveRight() {
        movingStrategy.mooveRight();
    }

    /**
     * Déplace la position du personnage d'une case vers la gauche
     * @author Adèle
     */
    public void mooveLeft() {
        movingStrategy.mooveLeft();
    }

    /**
     * Déplace la position du personnage d'une case vers le haut
     * @author Raphael
     */
    public void mooveUp() {
    	movingStrategy.mooveUp();
    }

    /**
     * Déplace la position du personnage d'une case vers le bas
     * @author Clément
     */
    public void mooveDown() {
        movingStrategy.mooveDown();
    }

    /**
     * @author Adèle
     * Inflige des dégats au personnage, ce qui lui fait perdre un/des points de vie
     * @param damage ampleur des dégats infligés, nombre de points de vie perdus par le personnage
     */
    public void setDamage(int damage){
        if(life-damage>=0)
            life -= damage;
        else
            life = 0;
        System.out.println("Vie : "+life);
    }

    /**
     * Modifier la vitesse du Pacman
     * @author Raphaël
     * @param s
     */
	public void setSpeed(double s) {
		this.speed = s;
	}

    /**
     * Retourner la vitesse du Pacman
     * @author Raphaël
     * @return Vitesse du pacman
     */
	public double getSpeed() {
		return this.speed;
	}

    /**
     * Permet de modifier le caractère fantôme du Pacman. S'il est fantôme, il peut traverser les murs
     * @author Raphaël
     * @param b true ou false
     */
	public void setGhost(boolean g) {
		this.ghost = g;
	}

	/**
	 * Retourne si le Pacman est un fantôme
	 * @author Raphaël
	 * @return true ou false
	 */
	public boolean getGhost() {
		return this.ghost;
	}

	/**
	 * Permet de modifier la portée d'attaque du Pacman (en cases)
	 * @author Raphaël
	 * @param r Portée des attaques
	 */
	public void setRange(int r) {
		if (this.range >= 1)
			this.range = r;
		else {
			this.range = 1;
		}
	}

	/**
	 * Retourner la portée des attaques du Pacman
	 * @author Raphaël
	 * @return Portée des attaques du Pacman
	 */
	public int getRange() {
		return this.range;
	}


	/**
	 * Modifier la position en abscisse du Pacman
	 * @author Adèle
	 * @param posX Position en abscisse
	 */
    public void setPosX(double posX) {
        this.posX = posX;
        this.visitedCoordinates.add(new int[] {(int) this.posX, (int) this.posY});
    }

    /**
     * Modifier la position en ordonnée du Pacman
     * @author Adèle
     * @param posY Position en ordonnée
     */
    public void setPosY(double posY) {
        this.posY = posY;
        this.visitedCoordinates.add(new int[] {(int) this.posX, (int) this.posY});
    }

    /**
     * Modifie la stratégie de déplacement en vigueur
     * @param movingStrategy nouvelle stratégie de déplacement à appliquer
     * @author Adèle
     */
    public void setMovingStrategy(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public MovingStrategy getMovingStrategy() {
    	return this.movingStrategy;
    }

    /**
     * @author Adèle
     * @return le nombre de point de vie du personnage
     */
    public int getLife() {
        return life;
    }
    
    /**
     * Retourner un itérateur sur la liste des coordonnées parcourues par le Pacman
     * @author Raphaël
     * @return Itérateur sur la liste des coordonées parcourues par le Pacman
     */
    public Iterator<int[]> getVisitedCoordinates() {
    	return new CustomIterator<int[]>(this.visitedCoordinates);
    }

    /**
     * Détermine si le personnage peut aller dans la direction désirée, en fonction de sa stratégie de déplacement
     * @param x coordonnée horizontale de la destination
     * @param y coordonnée horizontale de la destination
     * @param mapBuilder carte des cases du jeu
     * @return true si le personnage peut se déplacer vers la case souhaitée
     */
    public boolean canMoove(double x, double y, MapBuilder mapBuilder){
        return movingStrategy.canMoove(x, y, mapBuilder);
    }

    /**
     * Retourne la position en X et en Y du personnage
     * @author Clément
     * @return toString du personnage
     */
    @Override
    public String toString() {
        return "Position pacman : (" + posX + " ; " + posY + ")";
    }
}
