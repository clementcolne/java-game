package model.movingStrategy;

import model.PacmanCharacter;

/**
 * @author Adèle Barbier
 * 30/10/2020
 **/
public interface MovingStrategy {

    public void mooveUp(PacmanCharacter character);
    public void mooveDown(PacmanCharacter character);
    public void mooveRight(PacmanCharacter character);
    public void mooveLeft(PacmanCharacter character);
}
