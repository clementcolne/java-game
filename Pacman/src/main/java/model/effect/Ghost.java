package model.effect;

import java.awt.Color;

import model.PacmanCharacter;

/**
 * Classe représentant l'effet fantôme. Un fantôme peut traverser les murs.
 * @author Raphaël
 *
 */
public class Ghost extends EffectMagic {
	
	/**
	 * Constructeur de l'effet Ghost
	 * @author Raphaël
	 */
	public Ghost() {
		this.color = Color.BLACK;
	}
	
	/**
	 * Appliquer l'effet fantôme sur le Pacman. L'effet est appliqué au début, puis retiré au bout de 5 secondes
	 * @author Raphaël
	 * @param pacmanCharacter Pacman auquel doit être appliqué l'effet
	 */
	@Override
    public void doEffect(final PacmanCharacter pacmanCharacter) {	
		new AsyncEffect(this, Effect.class, 5000, 0, 5000) {
			public void execute() {
				pacmanCharacter.setGhost(!this.isEnded());
			}
		}.run();
    }

}
