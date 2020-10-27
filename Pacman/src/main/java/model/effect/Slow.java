package model.effect;

import model.PacmanCharacter;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Adèle
 * 25/10/2020
 * Ralentit le personnage dans ses déplacements
 **/
public class Slow extends EffectTrap{

    @Override
    public void doEffect(final PacmanCharacter character) {
        character.setSpeed(0.5);
        TimerTask slowEnd = new TimerTask() {
            @Override
            public void run() {
                character.setSpeed(1);
            }
        };
        new Timer().schedule(slowEnd, 5000);
    }
}
