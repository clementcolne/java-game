package model.effect;

import model.PacmanCharacter;

/**
 * @author Adèle
 * 25/10/2020
 * Arrête le personnage pendant 5 secondes
 **/
public class Stop extends EffectTrap{

    @Override
    public void doEffect(final PacmanCharacter character) {
        new AsyncEffect(this, Speed.class, 5000, 0, 5000) {
            public void execute() {
                character.setSpeed(!this.isEnded() ? 0 : 1);
            }
        }.run();
    }

    @Override
    public String toString() {
        return "Effet Arrêt";
    }
}