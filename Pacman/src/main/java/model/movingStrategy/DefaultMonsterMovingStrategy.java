package model.movingStrategy;

import engine.MapBuilder;
import model.Character;
import model.PacmanCharacter;

/**
 * @author Clément Colné
 */
public class DefaultMonsterMovingStrategy extends MovingStrategy {
    /**
     * Constructeur permettant d'indiquer qui est concerné par la stratégie
     *
     * @param pc Pacman
     * @author Clément
     */
    public DefaultMonsterMovingStrategy(PacmanCharacter pc) {
        super(pc);
    }

    @Override
    public boolean canMoove(double x, double y, MapBuilder mapBuilder) {
        this.setFactorX(x);
        this.setFactorY(y);
        this.mapBuilder = mapBuilder;
        return canBypassGround(x, y);
    }
}
