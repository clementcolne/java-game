package model.effect;

import model.PacmanCharacter;

/**
 * Classe représentant l'effet fantôme. Un fantôme peut traverser les murs.
 * @author Raphaël
 *
 */
public class Ghost extends EffectMagic {
	
	/**
	 * Appliquer l'effet fantôme sur le Pacman. L'effet est appliqué au début, puis retiré au bout de 5 secondes.
	 */
	@Override
    public void doEffect(final PacmanCharacter pacmanCharacter) {	
		new AsyncEffect(this, 5000, 0, 5000) {
			public void execute() {
				pacmanCharacter.setGhost(this.getExecutionNumber() == 1);
			}
		}.run();
    }
}
