package model.movingStrategy;

import engine.MapBuilder;
import model.PacmanCharacter;

/**
 * @author Adèle Barbier
 * 17/11/2020
 **/
public abstract class PacmanMovingStrategy extends MovingStrategy{

    /**
     * Constructeur permettant d'indiquer qui est concerné par la stratégie
     *
     * @param pc Pacman
     * @author Raphaël
     */
    public PacmanMovingStrategy(PacmanCharacter pc) {
        super(pc);
    }

    /**
     * Déplacement du personnage par défaut vers le haut
     * @author Raphaël
     */
    public abstract void mooveUp();

    /**
     * Déplacement du personange par défaut vers le bas
     * @author Raphaël
     */
    public abstract void mooveDown();

    /**
     * Déplacement du personnage par défaut vers la droite
     * @author Raphaël
     */
    public abstract void mooveRight();

    /**
     * Déplacement du personnage par défaut vers la gauche
     * @author Raphaël
     */
    public abstract void mooveLeft();

    /**
     * Déplacement du personnage vers une direction aléatoire
     * Ici le pacman n'en a pas besoin
     * @author Adèle
     */
    public void moove(){

    }

    @Override
    public abstract boolean canMoove(double x, double y, MapBuilder mapBuilder);
}
