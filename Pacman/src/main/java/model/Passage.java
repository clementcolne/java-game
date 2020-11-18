package model;

import java.awt.*;

import engine.ImageFactory;

/**
 * @author Clément Colné
 */
public class Passage extends Ground {

    private Passage linkedPassage;

    /**
     * Constructeur de Passage
     * @param x Position x de la case passage
     * @param y Position y de la case passage
     * @author Clément
     */
    public Passage(int x, int y) {
        super(x, y);
        this.name = 'p';
        this.color = Color.BLUE;
        this.image = ImageFactory.getInstance().loadImage("Extra/passage.png");
    }

    /**
     * Fixe le passage lié qui téléportera le personnage
     * @author Clément
     * @param p Passage lié qui téléporte le personnage vers ce nouveau passage
     */
    public void setLinkedPassage(Passage p) {
        linkedPassage = p;
    }

    /**
     * Retourne le passage lié
     * @author Clément
     * @return le passage lié
     */
    public Passage getLinkedPassage() {
        return linkedPassage;
    }

    /**
     * Permet d'indiquer que la case est un passage
     * @return toujours vrai
     * @author Clément
     */

    @Override
    public boolean isPassage() {
        return true;
    }

    /**
     * Retourne la description String de l'objet
     * @return toString de l'objet
     */
    @Override
    public String toString() {
        return "Passage(" + posX + ";" + posY + ") lié à Passage(" + linkedPassage.getPosX() + ";" + linkedPassage.getPosY() + ")";
    }

    /**
     * Déplace le personnage vers le passage lié
     * @param character personnage auquel l'effet va être affecté
     */
    @Override
    public void doEffect(PacmanCharacter character) {
        character.setPosX(getLinkedPassage().getPosX());
        character.setPosY(getLinkedPassage().getPosY());
    }
    
    /**
	 * Permet d'indiquer que le sol Passage effectue une action ayant une répercussion sur d'autres objets
	 * @author Raphaël
	 * @return false pour un une case de téléportation
	 */
    @Override
	public boolean hasEmptyBehavior() {
		return false;
	}
}
