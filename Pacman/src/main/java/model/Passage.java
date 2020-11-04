package model;

import java.awt.*;

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
    }

    public Passage(){
        this.path = "Extra/passage.png";
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
}
