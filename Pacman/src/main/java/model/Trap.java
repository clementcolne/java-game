package model;

import model.effect.EffectMagic;
import model.effect.EffectTrap;
import model.factory.ImageFactory;

import java.awt.*;

/**
 * Classe représentant une case piège
 * @author Adèle Barbier
 **/
public class Trap extends Ground{

    private EffectTrap effect;

    /**
     * Constructeur de Trap (piège)
     * @author Adèle
     * @param x, position en abscisse de la case du piège
     * @param y, position en ordonnée de la case du piège
     */
    public Trap(int x, int y, EffectTrap eff) {
        super(x, y);
        this.effect = eff;
        this.name = 't';
        this.color = Color.RED;
        this.image = ImageFactory.getInstance().get("Extra/treasure.png");
    }

    /**
     * Affecte un piège au personnage
     * @param character personnage auquel l'effet va être affecté
     */
    @Override
    public void doEffect(PacmanCharacter character) {
        effect.doEffect(character);
    }

    @Override
    public boolean isEffect() {
        return true;
    }
}
