package model.effect;

import java.util.HashSet;

import model.PacmanCharacter;

/**
 * Classe représentant l'effet de portée d'attaque. Un Pacman peut posséder une portée d'attaque augmentée.
 * @author Raphaël
 *
 */
public class Bow extends EffectMagic {
	
	/**
	 * Appliquer l'effet de portée d'attaque augmentée sur le Pacman. L'effet est appliqué au début, puis retiré au bout de 5 secondes.
	 */
	@Override
    public void doEffect(final PacmanCharacter pacmanCharacter) {
		new AsyncEffect(this, Effect.class, 5000, 0, 5000) {
			public void execute() {
				pacmanCharacter.setRange(!this.isEnded() ? 2 : 1);
			}
		}.run();
    }
}
