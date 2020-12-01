package model.effect;

import model.PacmanCharacter;

/**
 * Classe représentant l'effet de portée d'attaque. Un Pacman peut posséder une portée d'attaque augmentée.
 * @author Raphaël
 *
 */
public class Bow extends EffectMagic {
	
	/**
	 * Appliquer l'effet de portée d'attaque augmentée sur le Pacman. L'effet est appliqué au début, puis retiré au bout de 5 secondes
	 * @author Raphaël
	 * @param pacmanCharacter Pacman auquel doit être appliqué l'effet
	 */
	@Override
    public void doEffect(final PacmanCharacter pacmanCharacter) {
		new AsyncEffect(this, Effect.class, 5000, 0, 5000) {
			public void execute() {
				pacmanCharacter.setRange(!this.isEnded() ? 3 : 1);
			}
		}.run();
    }

	@Override
	public String toString() {
		return "Effet Arc";
	}
}
