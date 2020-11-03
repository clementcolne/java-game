package model.effect;

import model.PacmanCharacter;

/**
 * Déclenche un effet au hasard parmi les effets magiques connus
 * @author Raphaël
 **/
public class EffectMagic extends Effect {
    private static EffectMagic[] magicEffects = new EffectMagic[] {
    		new Ghost(),
    		new Speed(),
    		new Bow(),
    };

    /**
     * Exécute un effet piège au hasard
     * @param character personnage affecté par le piège
     */
    @Override
    public void doEffect(PacmanCharacter pacmanCharacter) {
        magicEffects[(int)(Math.random()*(magicEffects.length))].doEffect(pacmanCharacter);
    }
}
