package model.effect;

import engine.ProjectException;
import model.PacmanCharacter;

/**
 * @author Adèle
 * 25/10/2020
 * Ralentit le personnage dans ses déplacements
 **/
public class Slow extends EffectTrap{

    @Override
    public void doEffect(final PacmanCharacter character) throws ProjectException {
    	if (character == null) {
    		throw new ProjectException("Character can't be null");
    	}
        new AsyncEffect(this, Speed.class, 5000, 0, 5000) {
            public void execute() {
                character.setSpeed(!this.isEnded() ? 0.5 : 1);
            }
        }.run();
    }

    @Override
    public String toString() {
        return "Effet Lent";
    }
}
