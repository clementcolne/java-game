package model;

import model.effect.EffectMagic;

import java.awt.*;

/**
 * Classe qui représente un sol associé à un effet magique
 * @author Raphaël
 *
 */
public class Magic extends EffectGround {

	private EffectMagic effect;
	/**
	 * Constructeur de Magic (magie)
	 * @author Raphaël
	 * @param x, position en abscisse de la case de magie
	 * @param y, position en ordonnée de la case de magie
	 */
	public Magic(int x, int y, EffectMagic eff) {
		super(x, y, eff);
		this.name = 'm';
		effect = eff;
		this.color = Color.PINK;
	}

	/**
	 * Affecte un effet magique au personnage
	 * @param character personnage auquel l'effet va être affecté
	 */
	@Override
	public void doEffect(PacmanCharacter character) {
		effect.doEffect(character);
	}
}
