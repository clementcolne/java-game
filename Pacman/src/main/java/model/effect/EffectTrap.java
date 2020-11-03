package model.effect;

import model.PacmanCharacter;

import java.util.*;

/**
 * @author Adèle
 * 25/10/2020
 * Déclenche un effet au hasard parmi les pièges connus
 **/
public class EffectTrap extends Effect {
    //TODO: afficher nom de l'effet (trap + magie)
    private static List<EffectTrap> traps = new LinkedList<EffectTrap>() {
        {
            add(new Stun());
            add(new Slow());
            add(new Stop());
        }
    };


    /**
     * Exécute un effet piège au hasard
     * @param character personnage affecté par le piège
     */
    @Override
    public void doEffect(PacmanCharacter character) {
        Random r = new Random();
        traps.get(r.nextInt(traps.size())).doEffect(character);
    }
}