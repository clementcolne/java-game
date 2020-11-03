package model.effect;

import java.awt.Color;

import model.PacmanCharacter;

/**
 * Classe représentant l'effet fantôme. Un fantôme peut traverser les murs.
 * @author Raphaël
 *
 */
public class Speed extends EffectMagic {
	
	/**
	 * Constructeur de l'effet Speed.
	 * @author Raphaël
	 */
	public Speed() {
		this.color = Color.GREEN;
	}
	
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

}
