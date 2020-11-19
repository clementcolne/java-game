package model.movingStrategy;

import engine.MapBuilder;
import model.Character;

/**
 * @author Clément Colné
 */
public class DefaultMonsterMovingStrategy extends MovingStrategy {
    /**
     * Constructeur permettant d'indiquer qui est concerné par la stratégie
     *
     * @param c Pacman
     * @author Clément
     */
    public DefaultMonsterMovingStrategy(Character c) {
        super(c);
    }

    @Override
    public boolean canMoove(double x, double y, MapBuilder mapBuilder) {
        this.setFactorX(x);
        this.setFactorY(y);
        // vérifier mur
        // vérifier sortie map
        // vérifier passage 
        this.mapBuilder = mapBuilder;
        return canBypassGround(x, y);
    }
}
