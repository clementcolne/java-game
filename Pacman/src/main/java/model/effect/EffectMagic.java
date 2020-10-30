package model.effect;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.PacmanCharacter;

/**
 * Déclenche un effet au hasard parmi les effets magiques connus
 * @author Raphaël
 **/
public class EffectMagic extends Effect {
    private static List<EffectMagic> magicEffects = new LinkedList<EffectMagic>(Arrays.asList(new EffectMagic[] {
    		new Ghost(),
    		new Speed(),
    		new Bow(),
    }));

    /**
     * Exécute un effet piège au hasard
     * @param character personnage affecté par le piège
     */
    @Override
    public void doEffect(PacmanCharacter pacmanCharacter) {
        magicEffects.get((int)(Math.random()*magicEffects.size())).doEffect(pacmanCharacter);
    }
}
