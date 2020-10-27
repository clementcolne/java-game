package model.effect;

import model.PacmanCharacter;
import model.PacmanGame;

import java.util.*;

/**
 * @author Adèle
 * 25/10/2020
 * Déclenche un effet au hasard parmi les pièges connus
 **/
public class EffectTrap implements Effect {
    private List<EffectTrap> traps;

    /**
     * Constructeur du piège
     * Connait tous les pièges qui peuvent être déclenchés
     */
    public EffectTrap() {
        traps = new ArrayList<EffectTrap>();
        traps.add(new Slow());
        traps.add(new Stop());
        traps.add(new Stun());
    }


    /**
     * Exécute un effet piège au hasard
     * @param character personnage affecté par le piège
     */
    @Override
    public void doEffect(PacmanGame game) {
        Random r = new Random();
        traps.get(r.nextInt(traps.size())).doEffect(game);
    }
}
