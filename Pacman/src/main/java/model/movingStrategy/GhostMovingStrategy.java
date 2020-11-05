package model.movingStrategy;

import engine.MapBuilder;
import model.PacmanCharacter;

/**
 * @author Adèle Barbier
 * 05/11/2020
 **/
public class GhostMovingStrategy implements MovingStrategy{
    @Override
    public void mooveUp(PacmanCharacter character) {
        character.setPosY(character.getPosY()-character.getSpeed());
    }

    @Override
    public void mooveDown(PacmanCharacter character) {
        character.setPosY(character.getPosY()+character.getSpeed());
    }

    @Override
    public void mooveRight(PacmanCharacter character) {
        character.setPosX(character.getPosX()+character.getSpeed());
    }

    @Override
    public void mooveLeft(PacmanCharacter character) {
        character.setPosX(character.getPosX()-character.getSpeed());
    }

    @Override
    public boolean canMoove(double x, double y, MapBuilder mapBuilder, PacmanCharacter pacmanCharacter) {
        return pacmanCharacter.getPosX() + x < mapBuilder.getWidth() && pacmanCharacter.getPosY() + y < mapBuilder.getHeight() && pacmanCharacter.getPosX() + x >=0 && pacmanCharacter.getPosY() + y >=0;
    }
}