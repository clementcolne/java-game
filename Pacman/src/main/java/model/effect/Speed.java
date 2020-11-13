package model.effect;

import model.PacmanCharacter;

/**
 * Classe représentant l'effet fantôme. Un fantôme peut traverser les murs.
 * @author Raphaël
 *
 */
public class Speed extends EffectMagic {
	
	/**
	 * Appliquer l'effet fantôme sur le Pacman. L'effet est appliqué au début, puis retiré au bout de 5 secondes
	 * @author Raphaël
	 * @param pacmanCharacter Pacman auquel doit être appliqué l'effet
	 */
	@Override
    public void doEffect(final PacmanCharacter pacmanCharacter) {
		new AsyncEffect(this, Slow.class, 5000, 0, 5000) {
			public void execute() {
				pacmanCharacter.setSpeed(!this.isEnded() ? 2 : 1);
			}
		}.run();
    }

	@Override
	public String toString() {
		return "Effet Vitesse";
	}
}
