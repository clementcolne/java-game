package model.movingStrategy;

import model.PacmanCharacter;

/**
 * @author Adèle Barbier
 * 30/10/2020
 **/
public class DefaultMovingStrategy implements MovingStrategy {

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
}
