package model;

import model.effect.EffectTrap;

import java.awt.*;

import engine.ImageFactory;

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
        this.image = ImageFactory.getInstance().loadImage("Extra/trap.png");
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
    
    /**
	 * Permet d'indiquer que le sol Trap effectue une action ayant une répercussion sur d'autres objets
	 * @author Raphaël
	 * @return false pour un une case piège
	 */
    @Override
	public boolean hasEmptyBehavior() {
		return false;
	}
}
