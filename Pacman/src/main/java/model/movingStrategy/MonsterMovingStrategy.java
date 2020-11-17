package model.movingStrategy;

import engine.MapBuilder;
import model.Character;
import model.PacmanCharacter;

/**
 * @author Clément Colné
 */
public abstract class MonsterMovingStrategy extends MovingStrategy {

    /**
     * Constructeur permettant d'indiquer qui est concerné par la stratégie
     *
     * @param pc Pacman
     * @author Clément
     */
    public MonsterMovingStrategy(PacmanCharacter pc) {
        super(pc);
    }

    @Override
    public void mooveUp() {

    }

    @Override
    public void mooveDown() {

    }

    @Override
    public void mooveRight() {

    }

    @Override
    public void mooveLeft() {

    }

    public abstract void moove(Character character);

    public abstract  boolean canMoove(double x, double y, MapBuilder mapBuilder);

}
