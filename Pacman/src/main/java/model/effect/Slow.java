package model.effect;

import model.PacmanCharacter;

/**
 * @author Adèle
 * 25/10/2020
 * Ralentit le personnage dans ses déplacements
 **/
public class Slow extends EffectTrap{

    @Override
    public void doEffect(final PacmanCharacter character) {
        new AsyncEffect(this, Speed.class, 5000, 0, 5000) {
            public void execute() {
                character.setSpeed(!this.isEnded() ? 1 : 2);
            }
        }.run();
    }
}
