package model.effect;

import model.PacmanCharacter;

import java.util.*;

import engine.ProjectException;

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
     * @throws ProjectException 
     */
    @Override
    public void doEffect(PacmanCharacter character) throws ProjectException {
        EffectTrap temp = traps.get((int)(Math.random()*traps.size()));
        temp.doEffect(character);
    }
}