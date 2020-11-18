package model.effect;

import model.PacmanCharacter;
import model.movingStrategy.DefaultMovingStrategy;
import model.movingStrategy.RandomMovingStrategy;

/**
 * @author Adèle
 * 25/10/2020
 * "Etourdit" le personngae
 * Les touches pour déplacer le personnage sont interchangées
 **/
public class Stun extends EffectTrap{

    @Override
    public void doEffect(final PacmanCharacter character) {
        new AsyncEffect(this, Effect.class, 5000, 0, 5000) {
            public void execute() {
                character.setMovingStrategy(!this.isEnded() ? new RandomMovingStrategy(character) : new DefaultMovingStrategy(character));
            }
        }.run();
    }

    @Override
    public String toString() {
        return "Effet Etourdi";
    }

}
