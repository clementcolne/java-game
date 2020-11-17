package model.movingStrategy;

import engine.MapBuilder;
import model.Character;
import model.PacmanCharacter;

/**
 * @author Clément Colné
 */
public class DefaultMonsterMovingStrategy extends MonsterMovingStrategy {
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
    public void moove(Character character) {
        // TODO : pour l'instant aucune vérification si le perso a le droit de se déplacer
        double x, y;
        x = (Math.random() * (1 - -1 + 1) + -1);
        y = (Math.random() * (1 - -1 + 1) + -1);
        character.setPosX(character.getPosX() + x);
        character.setPosY(character.getPosY() + y);
    }

    @Override
    public boolean canMoove(double x, double y, MapBuilder mapBuilder) {
        this.setFactorX(x);
        this.setFactorY(y);
        this.mapBuilder = mapBuilder;
        return canBypassGround(x, y);
    }
}
