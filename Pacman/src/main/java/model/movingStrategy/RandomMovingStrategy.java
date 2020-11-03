package model.movingStrategy;

import engine.MapBuilder;
import model.PacmanCharacter;

/**
 * @author Adèle Barbier
 * 30/10/2020
 **/
public class RandomMovingStrategy implements MovingStrategy{

    @Override
    public void mooveUp(PacmanCharacter character) {
        character.setPosX(character.getPosX()-character.getSpeed());
    }

    @Override
    public void mooveDown(PacmanCharacter character) {
        character.setPosX(character.getPosX()+character.getSpeed());
    }

    @Override
    public void mooveRight(PacmanCharacter character) {
        character.setPosY(character.getPosY()-character.getSpeed());
    }

    @Override
    public void mooveLeft(PacmanCharacter character) {
        character.setPosY(character.getPosY()+character.getSpeed());
    }

    @Override
    public boolean canMoove(double x, double y, MapBuilder mapBuilder, PacmanCharacter pacmanCharacter) {
        return pacmanCharacter.getPosX() - y < mapBuilder.getWidth() && pacmanCharacter.getPosY() - x < mapBuilder.getHeight() &&
                (mapBuilder.get((int)(pacmanCharacter.getPosX() - y), (int)(pacmanCharacter.getPosY() - x)).isAccessible());
    }
}
